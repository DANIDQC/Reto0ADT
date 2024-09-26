/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author lucia_puwj3zw
 */

    public enum Dificultad {
    ALTA, MEDIA, BAJA;
 
  
       public static void mostrar() {

		for (Dificultad dificultad : Dificultad.values()) {// mostrara la lista de generos que tenemos
			System.out.printf("%s \t", dificultad.toString(), "\n");

		}

	}
       
    	public static String introducirDificultad(String texto) throws DificultadExcepcion {
		String cadena = null;
		boolean correcto = false;

		cadena = utilidades.Utilidades.introducirCadena(texto).toUpperCase();
		cadena = cadena.replace(" ", "_"); // se reemplazaran las barras bajas por un espacio asi el usuario podra
											// introducir el nombre del genero con espacios
		for (Dificultad tipo : Dificultad.values()) {
			if (cadena.equalsIgnoreCase(tipo.name())) { // se controla que el nombre introducido del genero sea el
														// correcto
				correcto = true;
			}
		}
		if (correcto == false) {// si es incorrecto pasara a la excepcion donde se validara el genero
			throw new DificultadExcepcion();
		}

		return cadena;
	}

	public static String validarDificultad(String texto) {
		String cadena = " ";
		boolean ok = false;

		do {
			try {
				cadena = introducirDificultad(texto);
				ok = true;
			} catch (DificultadExcepcion e) {
				texto = "\nVuelve a introducir la dificultad";
				e.printError();// se printea un mensaje de error que esta en la excepcion
				mostrar();// le mostramos la lista de genero que puede elegir

				ok = false;
			}
		} while (!ok);

		return cadena;
	}

    private static class DificultadExcepcion extends Exception {

        public DificultadExcepcion() {
        }
            
	public void printError() {
		System.out.println("\nEl dificultad introducido es incorrecto");
	}
        
    }
    


}
