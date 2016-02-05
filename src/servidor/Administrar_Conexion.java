package servidor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Administrar_Conexion {

	private String bd = null;
	private String login = null;
	private String password = null;
	private String url;

	public static Connection conn;
	private Statement comando = null;

	/** Constructor de DbConnection */
	public Administrar_Conexion() {
		this.bd = "agenda_servidor";
		this.login = "root";
		this.password = "root";
		this.url = "jdbc:mysql://localhost/" + this.bd;
		this.bd = bd;
		this.login = login;
		this.password = password;
		this.url = url;

	}

	public void conexion() {
	
			try {
						Class.forName("com.mysql.jdbc.Driver");

						System.out.println("CONECTANDO A BASE DE DATOS...");
						conn = DriverManager.getConnection(url, login, password);

						System.out.println("CONEXION EXITOSA");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("NO SE HA PODIDO CONECTAR A LA BBDD " + e);
					} 

	}

	/** Permite retornar la conexión */
	public Connection getConnection() {
		return conn;
	}

	public void desconectar() {
		conn = null;
	}



}
