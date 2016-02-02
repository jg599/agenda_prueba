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

	public Connection conn;
	private Statement comando = null;

	/** Constructor de DbConnection */
	public Administrar_Conexion(String bd, String login, String password,
			String url) {
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

//	public ArrayList<Dispensador> leer_bbdd() {
//		ArrayList<Dispensador> productos = new ArrayList<Dispensador>();
//		conexion();
//		try {
//
//			String instruccion = "SELECT id,nombre,precio,cantidad FROM productos";
//			comando = conn.createStatement();
//			ResultSet rs = comando.executeQuery(instruccion);
//			rs = comando.executeQuery(instruccion);
//
//			while (rs.next()) {
//				int id = rs.getInt("id");
//				String nombre = rs.getString("nombre");
//				double precio = rs.getDouble("precio");
//				int cantidad = rs.getInt("cantidad");
//				Dispensador auxDisp = new Dispensador(id, nombre, precio,
//						cantidad);
//
//				// System.out.println(auxDisp.getId() + auxDisp.getNombre() +
//				// auxDisp.getPrecio() + auxDisp.getCantidad());
//
//				productos.add(auxDisp);
//
//			}
//			rs.close();
//			comando.close();
//			conn.close();
//	
//
//		} catch (SQLException e) {
//			System.out.println(e);
//			System.out.println("consulta fallida");
//
//		}
//		return productos;
//
//	}

//	public ArrayList<Dispensador> update_bbdd(String auxProducto_upd) {
//		ArrayList<Dispensador> arlist2 = new ArrayList<Dispensador>();
//
//		try {
//			
//			String sql = "UPDATE productos "
//					+ "SET cantidad = cantidad-1 WHERE nombre='"
//					+ auxProducto_upd+"'";
//			comando = conn.createStatement();
//			comando.executeUpdate(sql);
//			
//
//		} catch (SQLException e) {
//			System.out.println(e);
//			System.out.println("update fallido");
//
//		}
//		return arlist2;
//
//	}

	/*
	 * public void imprimirDatos() throws SQLException { int sistema; int
	 * obtencion; String nombre;
	 * 
	 * while (resultados.next()) { try { sistema = resultados.getInt("consola");
	 * obtencion = resultados.getInt("obtenido"); nombre =
	 * resultados.getString("nombre"); } catch (SQLException e) {
	 * e.printStackTrace(); throw e; }}
	 */

}
