
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
    public boolean crearConvocatoria(ConvocatoriaExamen convocatoria) {
       
        return false;
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
    
}
