package servidor;

import java.io.IOException;
import java.sql.SQLException;





public class Main_servidor {
	static Administrar_Conexion auxConexion; 
	
	
	public static void main(String[] args) throws IOException, SQLException {

		auxConexion = new Administrar_Conexion("agenda_server", "root", "",
				"jdbc:mysql://localhost/maquina_refrescos");
		
		auxConexion.conexion();
		
		
		
	
}
	public void login(){
		
		
	}
}
