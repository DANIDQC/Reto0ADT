
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 2dam
 */
public class ImplementacionBd implements Dao{
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet resultSet;
    
    
    @Override
    public boolean crearUnidadDidactica(UnidadDidactica unidadDidactica) {
         con = ConexionBd.openConnection();
        String CreacionUnidadDidactica ="Insert into unidadDidactica (acronimo, titulo, evaluacion, descripcion)values(?,?,?,?)";
        try{
            stmt= con.prepareStatement(CreacionUnidadDidactica);
			stmt.setString(1, unidadDidactica.getAcronimo());
			stmt.setString(2, unidadDidactica.getTitulo());
                        stmt.setString(3, unidadDidactica.getEvaluacion());
			stmt.setString (4, unidadDidactica.getDescripcion());			
			stmt.executeUpdate();
                        return true;
        }catch(SQLException e){
            e.printStackTrace();
			return false;
        } finally {
            closeResources();
		}
    }
   

    @Override
    public boolean crearEnunciado(Enunciado enunciado, String acronimo) {
       
           int idUnidadDidactica = obtenerUnidadDidacticaPorId(acronimo);
        if (idUnidadDidactica == -1) {
            System.out.println("Enunciado no encontrado.");
            return false;
        }
        con = ConexionBd.openConnection();
        String CreacionEnunciado ="Insert into enunciado (descripcion, nivel, disponible, ruta)values(?,?,?,?)";
        try{
            stmt= con.prepareStatement(CreacionEnunciado);
			stmt.setString(1, enunciado.getDescripcion());
			stmt.setString(2, enunciado.getDificultad());
                        stmt.setBoolean(3, enunciado.isDisponible());
			stmt.setString (4, enunciado.getRuta());			

                  int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
            
        } finally {			
		closeResources();
	}
        
    }
  
  
      public int obtenerUnidadDidacticaPorId(String acronimo) {
    int id=-1;
        String consulta = "SELECT id FROM unidaddidactica WHERE acronimo = ?"; 

        try {
            con = ConexionBd.openConnection();
            stmt = con.prepareStatement(consulta);
            stmt.setString(1, acronimo);
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return id;
}

    @Override
    public boolean crearConvocatoria(Convocatoria convocatoria, String descripcionEnunciado) {
        
        
            int idEnunciado = buscarIdEnunciadoPorDescripcion(descripcionEnunciado);

        if (idEnunciado == -1) {
            System.out.println("Enunciado no encontrado.");
            return false;
        }
        
       con = ConexionBd.openConnection();
        String CreacionCoonvocatoria ="Insert into convocatoria ( convocatoria, descripcion,fecha, curso, id_Enunciado)values(?,?,?,?,?)";
        try{
            stmt= con.prepareStatement(CreacionCoonvocatoria);
			stmt.setString(1, convocatoria.getConvocatoria());
			stmt.setString(2, convocatoria.getDescripcion());
                        stmt.setString(3,convocatoria.getFecha().toString());
                        stmt.setString(4,convocatoria.getCurso()); 
                        stmt.setInt(5, idEnunciado); 		
			stmt.executeUpdate();
             return true;
        }catch(SQLException e){
            e.printStackTrace();
			return false;
        } finally {
			closeResources();
		}
        
        
    }
    private int buscarIdEnunciadoPorDescripcion(String descripcion) {
        int id = -1;
        String consulta = "SELECT id FROM enunciado WHERE descripcion = ?"; 

        try {
            con = ConexionBd.openConnection();
            stmt = con.prepareStatement(consulta);
            stmt.setString(1, descripcion);
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return id;
    }
    
    @Override
    public List<Convocatoria> buscarConvocatoriasPorEnunciado(String enunciadoDescripcion) {
        

        List<Convocatoria> convocatorias = new ArrayList<>();
    
    String sql = "SELECT c.* FROM convocatoria c " +
                 "JOIN enunciado e ON c.id_Enunciado = e.id " + 
                 "WHERE e.descripcion = ?";
    try {
        stmt = con.prepareStatement(sql);
        stmt.setString(1, enunciadoDescripcion);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Convocatoria convocatoria = new Convocatoria();
            convocatoria.setDescripcion(rs.getString("descripcion"));
            java.sql.Date sqlDate = rs.getDate("fecha");
            if (sqlDate != null) {
                convocatoria.setFecha(sqlDate.toLocalDate());
            }
            convocatoria.setCurso(rs.getString("curso"));
            convocatorias.add(convocatoria);
        }
        rs.close(); 
    } catch (SQLException e) {
        e.printStackTrace(); 
    }
    
    return convocatorias;
    }
    

    @Override
    public List<UnidadDidactica> listaUnidaades(UnidadDidactica unidades) {
        List<UnidadDidactica> listaUnidades = new ArrayList<>();
        con = ConexionBd.openConnection();
        String consultaUnidades = "SELECT acronimo, titulo, evaluacion, descripcion FROM unidadDidactica";

        try {
            stmt = con.prepareStatement(consultaUnidades);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                UnidadDidactica unidadDidactica = new UnidadDidactica();
                unidadDidactica.setAcronimo(resultSet.getString("acronimo"));
                unidadDidactica.setTitulo(resultSet.getString("titulo"));
                unidadDidactica.setEvaluacion(resultSet.getString("evaluacion"));
                unidadDidactica.setDescripcion(resultSet.getString("descripcion"));
                listaUnidades.add(unidadDidactica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al listar unidades didácticas");
        } finally {
            closeResources();
        }
        return listaUnidades;
    }


    private void closeResources() {

        try {
            if (stmt != null) {
                stmt.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (con != null) {
            ConexionBd.closeConnection();
        }
    }

    @Override
    public List<Enunciado> listaEnunciados(String acronimo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<UnidadDidactica> listaUnidades(UnidadDidactica unidades) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       public boolean actualizarEnunciadoConvocatoria(String nombreConvocatoria, String nuevoNombreEnunciado) {
        String sql = "UPDATE convocatoria SET id_Enunciado = (SELECT id FROM enunciado WHERE descripcion = ?) WHERE convocatoria = ?";
        
        try {
            con = ConexionBd.openConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nuevoNombreEnunciado);
            stmt.setString(2, nombreConvocatoria);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
        	ConexionBd.closeConnection();
        }
    }
           public String obtenerNombreEnunciadoPorConvocatoria(String nombreConvocatoria) {
        String nombreEnunciado = null;
        String sql = "SELECT e.descripcion FROM convocatoria c " +
                     "JOIN enunciado e ON c.id_Enunciado = e.id " +
                     "WHERE c.convocatoria = ?";
        
        try {
            con = ConexionBd.openConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nombreConvocatoria);
            resultSet = stmt.executeQuery();
            
            if (resultSet.next()) {
                nombreEnunciado = resultSet.getString("descripcion");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	ConexionBd.closeConnection();
        }
        
        return nombreEnunciado;
    }
            public Convocatoria buscarConvocatoriaPorNombre(String nombreConvocatoria) {
        Convocatoria convocatoria = null;
        String sql = "SELECT * FROM convocatoria WHERE convocatoria = ?";
        
        try {
            con = ConexionBd.openConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nombreConvocatoria);
            resultSet = stmt.executeQuery();
            
            if (resultSet.next()) {
                convocatoria = new Convocatoria();
                convocatoria.setConvocatoria(resultSet.getString("convocatoria"));
                convocatoria.setDescripcion(resultSet.getString("descripcion"));
                convocatoria.setFecha(resultSet.getDate("fecha").toLocalDate());
                convocatoria.setCurso(resultSet.getString("curso"));
                convocatoria.setIdEnunciado(resultSet.getInt("id_Enunciado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	ConexionBd.closeConnection();
        }
        
        return convocatoria;
    }
       
}