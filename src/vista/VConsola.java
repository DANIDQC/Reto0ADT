/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Dao;
import modelo.Enunciado;

/**
 *
 * @author 2dam
 */
public class VConsola {


  
    /**
     * @param args the command line arguments
     */
   private Dao dao;
   private Enunciado enunciado;
    public static void main(String[] args, Enunciado Enunciado) {
        Integer opc = 0;
        
        do{
            
            System.out.println("1. Crear una unidad didáctica y convocatoria."+
            "2. Crear enunciado de examen."+
            "3. Consultar enunciado."+
            "4. Consultar convocatorias."+
            "5. Visualizar el documento de enunciado."+
            "6. Asignar enunciado a convocatoria."+
            "0. Salir"+
            "Elige una opcion: ");

            opc = utilidades.Utilidades.leerInt(0, 6);

            switch (opc) {
                case 1:
                    //opcion1();
                    
                    break;
                case 2:
                    //opcion2();
                    crearEnunciadoDeExamen(enunciado);
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
      private static void crearEnunciadoDeExamen(Enunciado enunciado) {
          
          
   
    }

}
