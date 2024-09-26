/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 2dam
 */
public class ConexionBd {
    // Atributos
	private static Connection conn = null;
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
	public static Connection openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			return conn;
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return conn;
		}
	}

	/**
	 * Cierra la conexión a la base de datos.
	 *
	 * @return la conexión cerrada.
	 */
	public static Connection closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}