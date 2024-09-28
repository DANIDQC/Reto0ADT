
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
    public boolean crearUnidadDidactic(UnidadDidactica unidad) {
        con= ConexionBd.openConnection();
        
        
        
        return false;
        
    }
   
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
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			if (con != null) {
				ConexionBd.closeConnection();
			}
		}
        
    }
    
    @Override
    public boolean crearConvocatoria(Convocatoria convocatoria) {
        con = ConexionBd.openConnection();
        String CreacionEnunciado ="Insert into convocatoria (convocatoria, descripcion, fecha, curso)values(?,?,?,?)";
        try{
            stmt= con.prepareStatement(CreacionEnunciado);
			stmt.setString(1, convocatoria.getConvocatoria());
			stmt.setString(2, convocatoria.getDescripcion());
                        stmt.setString(3, convocatoria.getFecha().toString());
			stmt.setString (4, convocatoria.getCurso());			
			stmt.executeUpdate();
                        return true;
        }catch(SQLException e){
            e.printStackTrace();
			return false;
        } finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			if (con != null) {
				ConexionBd.closeConnection();
			}
		}
        
    }

    @Override
    public List <UnidadDidactica> listaUnidaades(UnidadDidactica unidades) {
        List <UnidadDidactica> listaUnidades = new ArrayList<>();
		con = ConexionBd.openConnection();
               String consultaUnidades="Select acronimo, titulo, evaluacion,descripcion from unidadDidactica";
                    
		try {
			stmt = con.prepareStatement(consultaUnidades);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				UnidadDidactica unidadDidacticas = new UnidadDidactica();
				unidadDidacticas.setAcronimo(resultSet.getString("acronimo"));
				unidadDidacticas.setTitulo(resultSet.getString("titulo"));
				unidadDidacticas.setEvaluacion(resultSet.getString("evaluacion"));
                                unidadDidacticas.setDescripcion(resultSet.getString("descripcion"));
                                
				listaUnidades.add(unidadDidacticas);
			}

		} catch (SQLException e) {
			e.printStackTrace();

			System.out.println("Error al listar unidades didacticas");
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			ConexionBd.closeConnection();
		}
        return null;
    }

    @Override
    public boolean crearEnunciado(Enunciado enunciado) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Convocatoria> buscarConvocatoriasPorEnunciado(String enunciadoDescripcion) {
        List<Convocatoria> convocatorias = new ArrayList<>();
    
    String sql = "SELECT c.* FROM convocatoria c " +
                 "JOIN enunciado e ON c.id_Enunciado = e.id " + 
                 "WHERE e.descripcion = ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
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
    
}
