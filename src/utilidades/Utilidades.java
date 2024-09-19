package utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utilidades {

	public static String introducirCadena(String texto) {
		Scanner teclado = new Scanner(System.in);
		String cadena = null;
		try {
			System.out.println(texto);
			cadena = teclado.nextLine();
		} catch (Exception e) {
			System.out.println("Error al introducir la cadena");
		}
		return cadena;
	}

	public static LocalDate introducirFecha(String texto) {
		// displayname nos da el nombre del mes y textStyle.full, new locale("es",
		// "ES")para que nos de el nombre en español
		LocalDate fecha = null;
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaCadena = null;
		boolean ok;
		do {
			ok = true;
			try {
				fechaCadena = introducirCadena(texto);
				fecha = LocalDate.parse(fechaCadena, formato);
			} catch (Exception e) {
				System.out.println("Formato de fecha incorrecta");
				ok = false;
			}

		} while (!ok);

		return fecha;

	}

	public static boolean siNo(String texto) {

		String respuesta;
		boolean verdadero = false;
		do {
			respuesta = Utilidades.introducirCadena(texto);
			if (respuesta.equalsIgnoreCase("si")) {
				verdadero = true;
			} else if (respuesta.equalsIgnoreCase("no")) {
				verdadero = false;
			} else {
				System.out.println("Error: Escribe si o no. ");
			}
		} while ((!(respuesta.equalsIgnoreCase("si")) && (!respuesta.equalsIgnoreCase("no"))));
		return verdadero;

	}

	public static float leerFloat(String texto) {
		float num = 0;
		boolean ok;
		do {

			try {
				num = Float.parseFloat(introducirCadena(texto));
				ok = true;
			} catch (NumberFormatException e) {
				System.out.println("Error al introducir el numero");
				ok = false;
			}
		} while (!ok);

		return num;

	}

	public static int leerInt(String texto) {
		int num = 0;
		boolean ok;
		do {

			try {
				num = Integer.parseInt(introducirCadena(texto));
				ok = true;
			} catch (NumberFormatException e) {
				System.out.println("Error al introducir un entero");
				ok = false;
			}
		} while (!ok);

		return num;

	}

}