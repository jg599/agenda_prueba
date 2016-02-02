package servidor;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;

public class Servidor extends Thread implements InterfazServidor {
	private class InfoCliente {
		private int tiempo_cliente = 0;
		private int tiempo_heartbeat = 0;
		private boolean muerto = false;

		public InfoCliente(int tiempo_cliente, int tiempo_heartbeat) {
			this.tiempo_cliente = tiempo_cliente;
			this.tiempo_heartbeat = tiempo_heartbeat;
		}

		public int getTiempoCliente() {
			return tiempo_cliente;
		}

		public void setTiempoCliente(int tiempo_cliente) {
			this.tiempo_cliente = tiempo_cliente;
		}

		public void incrementarTiempoCliente(int incremento) {
			this.tiempo_cliente += incremento;
		}

		public int getTiempoHeartbeat() {
			return tiempo_heartbeat;
		}

		public boolean estaMuerto() {
			return muerto;
		}

		public void setMuerto(boolean muerto) {
			this.muerto = muerto;
		}
	}

	private HashMap<String, InfoCliente> tabla_clientes = new HashMap<String, InfoCliente>();
	private int tiempo_paso_espera = 1;

	@Override
	public synchronized void registro(String id_cliente, int intervalo_heartbeat)
			throws RemoteException {
		InfoCliente info = tabla_clientes.get(id_cliente);
		if (info == null) {
			System.out.println((new Date()) + " Cliente " + id_cliente
					+ " registrado");
			tabla_clientes.put(id_cliente, new InfoCliente(0,
					intervalo_heartbeat));
		} else if (info.estaMuerto()) {
			System.out.println((new Date()) + " El cliente " + id_cliente
					+ " ha resucitado");
			info.setTiempoCliente(0);
			info.setMuerto(false);
		}
	}

	@Override
	public synchronized void heartbeat(String id_cliente)
			throws RemoteException {
		InfoCliente info = tabla_clientes.get(id_cliente);
		if (info != null) {
			if (info.estaMuerto()) {
				System.out.println((new Date()) + " El cliente " + id_cliente
						+ " haresucitado");
				info.setMuerto(false);
			} else {
				System.out.println((new Date()) + " Heartbeat recibido de "
						+ id_cliente);
			}
			info.setTiempoCliente(0);
		}
	}

	public void run() {
		while (true) {
			try {
				sleep(tiempo_paso_espera * 1000);
				synchronized (this) {
					String[] ids = tabla_clientes.keySet().toArray(
							new String[0]);
					for (String id_cliente : ids) {
						InfoCliente info_cliente = tabla_clientes
								.get(id_cliente);
						if (!info_cliente.estaMuerto()) {
							info_cliente
									.incrementarTiempoCliente(tiempo_paso_espera);
							if (info_cliente.getTiempoCliente() > (info_cliente
									.getTiempoHeartbeat() * 2)) {
								System.out.println((new Date())
										+ " El cliente " + id_cliente
										+ " ha muerto");
								info_cliente.setMuerto(true);
							}
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Registry reg = null;
		try {
			reg = LocateRegistry.createRegistry(5555);
		} catch (Exception e) {
			System.out.println("ERROR: No se ha podido crear el registro");
			e.printStackTrace();
		}
		Servidor serverObject = new Servidor();
		try {
			reg.rebind("PSP", (InterfazServidor) UnicastRemoteObject
					.exportObject(serverObject, 0));
		} catch (Exception e) {
			System.out
					.println("ERROR: No se ha podido inscribir el objeto servidor.");
			e.printStackTrace();
		}
		serverObject.start();
	}
}