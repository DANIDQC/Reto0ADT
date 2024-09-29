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
public class ImplementacionBd implements Dao {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet resultSet;

    //Este metodo crea un objeto UnidadDidactica y lo añade a la base de datos.
    @Override
    public boolean crearUnidadDidactica(UnidadDidactica unidadDidactica) {
        con = ConexionBd.openConnection();
        String CreacionUnidadDidactica = "INSERT INTO unidadDidactica (acronimo, titulo, evaluacion, descripcion) VALUES (?, ?, ?, ?)";
        try {
            stmt = con.prepareStatement(CreacionUnidadDidactica);
            stmt.setString(1, unidadDidactica.getAcronimo());
            stmt.setString(2, unidadDidactica.getTitulo());
            stmt.setString(3, unidadDidactica.getEvaluacion());
            stmt.setString(4, unidadDidactica.getDescripcion());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
        	ConexionBd.closeConnection();
        }
    }

    //Este metodo crea un objeto UnidadDidactica y lo añade a la base de datos.
    @Override
    public boolean crearConvocatoria(Convocatoria convocatoria, String descripcionEnunciado) {
        int idEnunciado = buscarIdEnunciadoPorDescripcion(descripcionEnunciado);
        if (idEnunciado == -1) {
            System.out.println("Enunciado no encontrado.");
            return false;
        }

        con = ConexionBd.openConnection();
        String CreacionConvocatoria = "INSERT INTO convocatoria (descripcion, fecha, curso, id_Enunciado) VALUES (?, ?, ?, ?)";
        try {
            stmt = con.prepareStatement(CreacionConvocatoria);
            stmt.setString(1, convocatoria.getDescripcion());
            stmt.setString(2, convocatoria.getFecha().toString());
            stmt.setString(3, convocatoria.getCurso());
            stmt.setInt(4, idEnunciado); 
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
        	ConexionBd.closeConnection();
        }
    }

    //Este metodo busca el ID de un objeto enunciado en base a su descripcion.
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
        	ConexionBd.closeConnection();
        }

        return id;
    }

    @Override
    public boolean crearEnunciado(Enunciado enunciado) {
        // Implementar este método según tus requisitos
        throw new UnsupportedOperationException("Not supported yet.");
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
        	ConexionBd.closeConnection();
        }
        return listaUnidades;
    }

    //Este metodo busca y recoge las convocatorias en base al nombre de un enunciado.
    @Override
    public List<Convocatoria> buscarConvocatoriasPorEnunciado(String enunciadoDescripcion) {
        List<Convocatoria> convocatorias = new ArrayList<>();
        String sql = "SELECT c.* FROM convocatoria c " +
                     "JOIN enunciado e ON c.id_Enunciado = e.id " +
                     "WHERE e.descripcion = ?";

        try {
            con = ConexionBd.openConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, enunciadoDescripcion);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Convocatoria convocatoria = new Convocatoria();
                convocatoria.setConvocatoria(rs.getString("convocatoria"));
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
        } finally {
        	ConexionBd.closeConnection();
        }

        return convocatorias;
    }

    //Este metodo recoge un objeto convocatoria en base a un nombre que inserta el usuario.
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

    //Este metodo busca el nombre de un enunciado en base a al id_enunciado de una convocatoria.
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

    //Este metodo cambia el id_Enunciado de una convocatoria.
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
}
