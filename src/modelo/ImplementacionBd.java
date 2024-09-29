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
            closeResources();
        }
    }

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
            closeResources();
        }
        return listaUnidades;
    }

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
            closeResources();
        }

        return convocatorias;
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
}
