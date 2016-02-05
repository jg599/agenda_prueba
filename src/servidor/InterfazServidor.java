package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazServidor extends Remote {
	public void ver_contactos() throws RemoteException;

	public void modificar_contactos() throws RemoteException;

	public void borrar_contactos() throws RemoteException;

	
}