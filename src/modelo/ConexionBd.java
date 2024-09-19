/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 2dam
 */
public class ConexionBd {
    // Atributos
	private static ConexionBd conn;
	// Credenciales para conexión a la base de datos
	private static final String USERNAME = "root";
	private static final String PASSWORD = "abcd*1234";
	private static final String URL = "jdbc:mysql://localhost:3306/examendb?serverTimezone=Europe/Madrid&useSSL=false";

	/**
	 * Abre la conexión a la base de datos. utilizando las credenciales necesarias
	 * para acceder a ella
	 * 
	 * @return la conexión establecida.
	 */
	public static ConexionBd openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = (ConexionBd) DriverManager.getConnection(URL, USERNAME, PASSWORD);
			return conn;
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
			return null;
		} catch (ClassNotFoundException e) {
			return conn;
		}
	}

	/**
	 * Cierra la conexión a la base de datos.
	 *
	 * @return la conexión cerrada.
	 */
	public static ConexionBd closeConnection() {
            if (conn != null) {
                conn.close();
            }
		return conn;
	}

    private void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
