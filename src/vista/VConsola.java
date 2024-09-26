/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador;
import modelo.Convocatoria;
import modelo.Dificultad;
import modelo.Enunciado;
import modelo.UnidadDidactica;

/**
 *
 * @author 2dam
 */
public class VConsola {
   
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
                crearUnidadDidacticaConvocatoria(con);
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
    
        private static void crearUnidadDidacticaConvocatoria(Controlador con) {
                
          UnidadDidactica nuevo= new UnidadDidactica();
          
          nuevo.setAcronimo(utilidades.Utilidades.introducirCadena("Introduce el acronimo de la Unidad Didactica"));
          nuevo.setTitulo(utilidades.Utilidades.introducirCadena("Introduce el titulo de la Unidad Didactica"));
          nuevo.setEvaluacion(utilidades.Utilidades.introducirCadena("Introduce la evaluacion de la Unidad Didactica"));
          nuevo.setDescripcion(utilidades.Utilidades.introducirCadena("Introduce la descripcion de la Unidad Didactica"));
          // 
          
          boolean existe= con.crearUnidadDidactica(nuevo);
          
        
          crearConvocatoria(con);
    }
        
        private static void crearConvocatoria(Controlador con) {
          
          Convocatoria nuevo= new Convocatoria();
          
          System.out.println("Ahora introduce los datos de la convocatoria");
          nuevo.setDescripcion(utilidades.Utilidades.introducirCadena("Introduce la descripcion de la convocatoria"));
          nuevo.setFecha(utilidades.Utilidades.pidoFechaDMA("Introduce la fecha de la Convocatoria"));
          nuevo.setCurso(utilidades.Utilidades.introducirCadena("Introduce el curso de la convocatoria"));

          boolean existe= con.crearConvocatoria(nuevo);    }

    
      private static void crearEnunciado(Controlador con) {
                
          Enunciado nuevo= new Enunciado();
          
          nuevo.setDescripcion(utilidades.Utilidades.introducirCadena("Introduce la descripcion del enunciado"));
          nuevo.setDificultad(Dificultad.validarDificultad("Introduce la dificultad del enunciado"));
          nuevo.setDisponible(utilidades.Utilidades.leerRespuesta("¿El enuciado esta disponible? si/no"));
          nuevo.setRuta(utilidades.Utilidades.introducirCadena("Introduce la ruta del enunciado"));
          // 
          boolean existe= con.crearEnunciado(nuevo);
          
    }

}
