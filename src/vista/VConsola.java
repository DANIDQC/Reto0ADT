/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import controlador.Controlador;
import modelo.Dificultad;
import modelo.Enunciado;
/**
 *
 * @author 2dam
 */
public class VConsola {

    private static void crearUnidadDidactica(Controlador con) {
    }
    /**
     * @param args the command line arguments
     */
   
   
    public static void main(String[] args) {
        Integer opc = 0;
        Controlador con = new Controlador();
        do{
            
            System.out.println("1. Crear una unidad didáctica y convocatoria.\n"+
            "2. Crear enunciado de examen.\n"+
            "3. Consultar enunciado.\n"+
            "4. Consultar convocatorias.\n"+
            "5. Visualizar el documento de enunciado.\n"+
            "6. Asignar enunciado a convocatoria.\n"+
            "0. Salir\n"+
            "Elige una opcion: \n");

            opc = utilidades.Utilidades.leerInt(0, 6);

            switch (opc) {
                case 1:
                    //opcion1();
                crearUnidadDidactica(con);
                    break;
                case 2:
                    //opcion2();
               crearEnunciado(con);
                    break;
                case 3:
                    //opcion3();
                    
                    break;
                case 4:
                    //opcion4();
                    break;
                case 5:
                    //opcion5();
                    break;
                case 6:
                    //opcion6();
                    break;
                case 0:
                    System.out.println("Hasta la vista.");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;
            }
        }while(opc!=0);
    }
      private static void crearEnunciado(Controlador con) {
                
          Enunciado nuevo= new Enunciado();
          
          nuevo.setDescripcion(utilidades.Utilidades.introducirCadena("Introduce la descripcion del enunciado"));
          nuevo.setDificultad(Dificultad.validarDificultad("Introduce la dificultad del enunciado"));
          nuevo.setDisponible(utilidades.Utilidades.leerRespuesta("¿El enuciado esta disponible? si/no"));
          nuevo.setRuta(utilidades.Utilidades.introducirCadena("Introduce la ruta del enunciado"));
          // 
          System.out.println(nuevo.toString());
          boolean existe= con.crearEnunciado(nuevo);
          
    }
      
}
