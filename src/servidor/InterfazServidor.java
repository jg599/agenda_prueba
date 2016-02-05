package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazServidor extends Remote {
	public int ver_contactos() throws RemoteException;

	public int modificar_contactos(int a, int b) throws RemoteException;

	public int borrar_contactos() throws RemoteException;

	
}