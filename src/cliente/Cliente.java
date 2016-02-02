package cliente;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import servidor.InterfazServidor;

public class Cliente {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("argumentos: <id cliente> <tiempo heartbeat>");
			System.exit(1);
		}
		String id_cliente = args[0];
		int tiempo_heartbeat = Integer.parseInt(args[1]);
		InterfazServidor serv = null;
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 5555);
			serv = (InterfazServidor) registry.lookup("PSP");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (serv != null) {
			try {
				serv.registro(id_cliente, tiempo_heartbeat);
				while (true) {
					serv.heartbeat(id_cliente);
					Thread.sleep(tiempo_heartbeat * 1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}