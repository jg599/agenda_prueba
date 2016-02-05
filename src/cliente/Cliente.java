package cliente;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import servidor.InterfazServidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		InterfazServidor agenda = null;
		try {
			System.out.println("Localizando el registro de objetos remitos");
			Registry registry = LocateRegistry.getRegistry("localhost", 5555);
			System.out.println("Obteniendo el stab del objeto remoto");
			agenda = (InterfazServidor) registry.lookup("Calculadora");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (agenda != null) {
			System.out.println("Realizando operaciones con el objeto remoto");
			try {
				System.out.println("mostrando contactos");
				agenda.ver_contactos();
				System.out.println("desea borrar algun contacto?");
				String pru =scan.nextLine();
				if (pru.equals("si")){
					agenda.borrar_contactos();
					
				}
				else{
					System.out.println("continuando");
				}
			
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}