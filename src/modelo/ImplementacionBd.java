
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
    public boolean crearUnidadDidactica(UnidadDidactica unidad) {
        con= ConexionBd.openConnection();
        
        
        
        return false;
        
    }
   

    @Override
    public boolean crearEnunciado(Enunciado enunciado) {
        con = ConexionBd.openConnection();
        String CreacionEnunciado ="Insert into enunciado (descripcion, nivel, disponible, ruta)values(?,?,?,?)";
        try{
            stmt= con.prepareStatement(CreacionEnunciado);
			stmt.setString(1, enunciado.getDescripcion());
			stmt.setString(2, enunciado.getDificultad());
                        stmt.setBoolean(3, enunciado.isDisponible());
			stmt.setString (4, enunciado.getRuta());			
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
        String CreacionCoonvocatoria ="Insert into convocatoria (convocatoria, descripcion,fecha, curso0)values(?,?,?,?)";
        try{
            stmt= con.prepareStatement(CreacionCoonvocatoria);
			stmt.setString(1, convocatoria.getConvocatoria());
			stmt.setString(2, convocatoria.getDescripcion());
                        stmt.setString(3,convocatoria.getLocalDate().toString());
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
    public List <UnidadDidactica> listaUnidades(UnidadDidactica unidades) {
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
    public List<Enunciado> listaEnunciados(String acronimo) {
    List <Enunciado> lista = new ArrayList<>();
		con = ConexionBd.openConnection();
               String consultaEnunciado="Select descripcion, nivel, disponible, ruta from where id= (select id intermedia where id_unidadDidactica =(select id from unidaddidactica where titulo=?";
    try {
			stmt = con.prepareStatement(consultaEnunciado);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Enunciado enunciado = new Enunciado();
				enunciado.setDescripcion(resultSet.getString("descripcion"));
				enunciado.setDificultad(resultSet.getString("nivel"));
				enunciado.setDisponible(resultSet.getBoolean("disponible"));
                                enunciado.setRuta(resultSet.getString("ruta"));
                                
				lista.add(enunciado);
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
    
}
