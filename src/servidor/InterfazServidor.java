package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazServidor extends Remote {
	public void registro(String id_cliente, int intervalo_heartbeat)
			throws RemoteException;

	public void heartbeat(String id_cliente) throws RemoteException;
}
