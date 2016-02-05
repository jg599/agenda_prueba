package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Servidor implements InterfazServidor {
	public static Administrar_Conexion conn;
	public static boolean exito = false;
	public static boolean dentro = false;
	private static int contador = 0;
	private Statement comando = null;
	static Scanner scan = new Scanner(System.in);

	public void ver_contactos() throws RemoteException {

		conn.conexion();

		try {
			String instruccion = "SELECT * FROM contactos";
			comando = ((Connection) conn).createStatement();
			ResultSet rs = comando.executeQuery(instruccion);
			rs = comando.executeQuery(instruccion);
			rs.close();
			comando.close();
			((Connection) conn).close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			System.out.println("consulta fallida");
		}
	}

	public void modificar_contactos() throws RemoteException {
		
	}

	public void borrar_contactos() throws RemoteException {
		System.out.println("introduzca el nombre que desea eliminar");
		String nombre = scan.nextLine();
		conn.conexion();

		try {
			String instruccion = "DELETE FROM contactos WHERE nombre= "
					+ nombre;
			comando = ((Connection) conn).createStatement();
			ResultSet rs = comando.executeQuery(instruccion);
			rs = comando.executeQuery(instruccion);
			rs.close();
			comando.close();
			((Connection) conn).close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			System.out.println("consulta fallida");
		}
	}

	public int div(int a, int b) throws RemoteException {
		return (a / b);
	}

	public static void register(String user, String pass) throws SQLException {
		exito = false;
		String name = user;
		String password = pass;

		Administrar_Conexion conn = new Administrar_Conexion();
		PreparedStatement pstmt = Administrar_Conexion.conn
				.prepareStatement("SELECT COUNT(*) AS resultado FROM agenda_server.usuarios WHERE usuario = ?");
		pstmt.setString(1, user);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		if (rs.getBoolean("resultado")) {
			System.out.println("Ya existe!");
			JOptionPane.showMessageDialog(null, "El usuario ya existe",
					"Error", JOptionPane.INFORMATION_MESSAGE);
		} else {
			PreparedStatement pstmt2 = Administrar_Conexion.conn
					.prepareStatement("INSERT INTO agenda_server.usuarios(usuario, password) values (?, ?)");
			pstmt2.setString(1, user);
			pstmt2.setString(2, pass);

			pstmt2.executeUpdate();
			System.out.println("insertado!");
			JOptionPane.showMessageDialog(null, "¡Usuario registrado!",
					"Registrado", JOptionPane.INFORMATION_MESSAGE);
			exito = true;
		}
	}

	public void login(String user, String pass) throws SQLException {

		String name = user;
		String password = pass;
		Administrar_Conexion conn = new Administrar_Conexion();
		PreparedStatement pstmt = Administrar_Conexion.conn
				.prepareStatement("SELECT COUNT(*) AS resultado FROM agenda_server.usuarios WHERE usuario = ? && password = ? ");
		pstmt.setString(1, user);
		pstmt.setString(2, pass);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		if (rs.getBoolean("resultado")) {
			System.out.println("Successful Login!\n----");
			JOptionPane.showMessageDialog(null,
					"Está usted dentro del sistema, bienvenido",
					"Login correcto", JOptionPane.INFORMATION_MESSAGE);
			dentro = true;
		} else {
			System.out.println("Incorrect Password\n----");
			contador++;
			JOptionPane.showMessageDialog(null, "Error. Inténtelo de nuevo",
					"Error", JOptionPane.INFORMATION_MESSAGE);
			if (contador >= 3) {
				JOptionPane
						.showMessageDialog(
								null,
								"Has superado el máximo número de intentos. La aplicación saldrá",
								"Error", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}

	}

	public static void main(String[] args) {
		Registry reg = null;
		try {
			System.out
					.println("Crea el registro de objetos, escuchando en el puerto 555");
			reg = LocateRegistry.createRegistry(5555);
		} catch (Exception e) {
			System.out.println("ERROR: No se ha podido crear el registro");
			e.printStackTrace();
		}
		System.out.println("Creando el objeto servidor");
		Servidor serverObject = new Servidor();
		try {
			System.out
					.println("Inscribiendo el objeto servidor en el registro");
			System.out.println("Se le da un nombre único: agenda");
			reg.rebind("agenda", (Remote) UnicastRemoteObject.exportObject(
					(Remote) serverObject, 0));
		} catch (Exception e) {
			System.out
					.println("ERROR: No se ha podido inscribir el objeto servidor.");
			e.printStackTrace();
		}
	}
}
