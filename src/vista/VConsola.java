package vista;

import controlador.Controlador;
import java.util.List;
import modelo.Convocatoria;
import modelo.Dificultad;
import modelo.Enunciado;
import modelo.UnidadDidactica;

public class VConsola {
   
    public static void main(String[] args) {
        Integer opc = 0;
        Controlador con = new Controlador();
        do {
            System.out.println("1. Crear una unidad didáctica y convocatoria.\n" +
            "2. Crear enunciado de examen.\n" +
            "3. Consultar enunciado.\n" +
            "4. Consultar convocatorias.\n" +
            "5. Visualizar el documento de enunciado.\n" +
            "6. Asignar enunciado a convocatoria.\n" +
            "0. Salir\n" +
            "Elige una opcion: \n");

            opc = utilidades.Utilidades.leerInt(0, 6);

            switch (opc) {
                case 1:
                    crearUnidadDidacticaConvocatoria(con);
                    break;
                case 2:
                    crearEnunciado(con);
                    break;
                case 3:
                    // Implementar opción 3
                    break;
                case 4:
                    consultarConvocatorias(con);
                    break;
                case 5:
                    // Implementar opción 5
                    break;
                case 6:
                	asignarEnunciadoAConvocatoria(con);
                    break;
                case 0:
                    System.out.println("Hasta la vista.");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;
            }
        } while (opc != 0);
    }
    
    private static void crearUnidadDidacticaConvocatoria(Controlador con) {
        UnidadDidactica nuevo = new UnidadDidactica();
          
        nuevo.setAcronimo(utilidades.Utilidades.introducirCadena("Introduce el acronimo de la Unidad Didactica"));
        nuevo.setTitulo(utilidades.Utilidades.introducirCadena("Introduce el titulo de la Unidad Didactica"));
        nuevo.setEvaluacion(utilidades.Utilidades.introducirCadena("Introduce la evaluacion de la Unidad Didactica"));
        nuevo.setDescripcion(utilidades.Utilidades.introducirCadena("Introduce la descripcion de la Unidad Didactica"));
          
        boolean existe = con.crearUnidadDidactica(nuevo);
        
        crearConvocatoria(con);
    }
        
    private static void crearConvocatoria(Controlador con) {
        Convocatoria nuevo = new Convocatoria();
          
        System.out.println("Ahora introduce los datos de la convocatoria");
        nuevo.setDescripcion(utilidades.Utilidades.introducirCadena("Introduce la descripcion de la convocatoria"));
        nuevo.setFecha(utilidades.Utilidades.pidoFechaDMA("Introduce la fecha de la Convocatoria"));
        nuevo.setCurso(utilidades.Utilidades.introducirCadena("Introduce el curso de la convocatoria"));

        String descripcionEnunciado = utilidades.Utilidades.introducirCadena("Introduce la descripcion del enunciado que quieres asociar:");
        
        boolean existe = con.crearConvocatoria(nuevo, descripcionEnunciado); 
    }

    private static void crearEnunciado(Controlador con) {
        Enunciado nuevo = new Enunciado();
          
        nuevo.setDescripcion(utilidades.Utilidades.introducirCadena("Introduce la descripcion del enunciado"));
        nuevo.setDificultad(Dificultad.validarDificultad("Introduce la dificultad del enunciado"));
        nuevo.setDisponible(utilidades.Utilidades.leerRespuesta("¿El enunciado está disponible? si/no"));
        nuevo.setRuta(utilidades.Utilidades.introducirCadena("Introduce la ruta del enunciado"));
          
        boolean existe = con.crearEnunciado(nuevo);
    }

    private static void consultarConvocatorias(Controlador con) {
        String enunciado = utilidades.Utilidades.introducirCadena("Introduce el enunciado de la convocatoria a consultar:");
        
        List<Convocatoria> convocatorias = con.consultarConvocatoriasPorEnunciado(enunciado); 

        if (convocatorias.isEmpty()) {
            System.out.println("No se encontraron convocatorias para el enunciado especificado.");
        } else {
            System.out.println("Convocatorias encontradas:");
            for (Convocatoria convocatoria : convocatorias) {
            	System.out.println("Convocatoria: " + convocatoria.getConvocatoria());
                System.out.println("Descripción: " + convocatoria.getDescripcion());
                System.out.println("Fecha: " + convocatoria.getFecha());
                System.out.println("Curso: " + convocatoria.getCurso());
                System.out.println("-----------------------");
            }
        }
    }
    
    private static void asignarEnunciadoAConvocatoria(Controlador con) {
     
        String nombreConvocatoria = utilidades.Utilidades.introducirCadena("Introduce el nombre de la convocatoria que deseas modificar:");
        
        Convocatoria convocatoria = con.buscarConvocatoriaPorNombre(nombreConvocatoria);
        
        if (convocatoria == null) {
            System.out.println("No se encontró una convocatoria con ese nombre.");
            return;
        }
        
        String nombreEnunciadoActual = con.obtenerNombreEnunciadoPorConvocatoria(nombreConvocatoria);
        System.out.println("El enunciado asociado actualmente es: " + nombreEnunciadoActual);
        
        boolean cambiarEnunciado = utilidades.Utilidades.leerRespuesta("¿Deseas cambiar el enunciado asociado a esta convocatoria? (si/no)");
        
        if (cambiarEnunciado) {
            String nuevoNombreEnunciado = utilidades.Utilidades.introducirCadena("Introduce el nombre del nuevo enunciado:");
            
            boolean exito = con.actualizarEnunciadoConvocatoria(nombreConvocatoria, nuevoNombreEnunciado);
            
            if (exito) {
                System.out.println("Enunciado actualizado correctamente.");
            } else {
                System.out.println("Hubo un problema al actualizar el enunciado.");
            }
        }
    }
}
