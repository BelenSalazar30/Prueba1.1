package Capa_persistencia;

import Capa_conexion.Conexion;
import entidad.Activo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivoDAO {

    // Método para guardar un activo en la base de datos
    public void guardarActivo(Activo activo) {
        String sql = "INSERT INTO activos (nombre, periodos_depreciacion_total, valor_compra, tipo_activo) " +
                     "VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, activo.getNombre());
            stmt.setInt(2, activo.getPeriodosDepreciacionTotal());
            stmt.setDouble(3, activo.getValorCompra());
            stmt.setString(4, activo.getTipoActivo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No se pudo guardar el activo.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    activo.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Fallo al obtener el ID generado para el activo.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar el activo: " + e.getMessage());
        }
    }

    // Método para buscar un activo por su ID
    public Activo buscarActivo(int id) {
        Activo activo = null;
        String sql = "SELECT * FROM activos WHERE id = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                activo = new Activo();
                activo.setId(rs.getInt("id"));
                activo.setNombre(rs.getString("nombre"));
                activo.setPeriodosDepreciacionTotal(rs.getInt("periodos_depreciacion_total"));
                activo.setValorCompra(rs.getDouble("valor_compra"));
                activo.setTipoActivo(rs.getString("tipo_activo"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el activo: " + e.getMessage());
        }

        return activo;
    }

    // Método para actualizar los datos de un activo
    public void actualizarActivo(Activo activo) {
        String sql = "UPDATE activos SET nombre = ?, periodos_depreciacion_total = ?, " +
                     "valor_compra = ?, tipo_activo = ? WHERE id = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, activo.getNombre());
            stmt.setInt(2, activo.getPeriodosDepreciacionTotal());
            stmt.setDouble(3, activo.getValorCompra());
            stmt.setString(4, activo.getTipoActivo());
            stmt.setInt(5, activo.getId());

            stmt.executeUpdate();

            System.out.println("Activo actualizado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar el activo: " + e.getMessage());
        }
    }

    // Método para eliminar un activo por su ID
    public void eliminarActivo(int id) {
        String sql = "DELETE FROM activos WHERE id = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Activo eliminado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar el activo: " + e.getMessage());
        }
    }

    // Método para obtener todos los activos
    public List<Activo> obtenerTodosActivos() {
        List<Activo> listaActivos = new ArrayList<>();
        String sql = "SELECT * FROM activos";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Activo activo = new Activo();
                activo.setId(rs.getInt("id"));
                activo.setNombre(rs.getString("nombre"));
                activo.setPeriodosDepreciacionTotal(rs.getInt("periodos_depreciacion_total"));
                activo.setValorCompra(rs.getDouble("valor_compra"));
                activo.setTipoActivo(rs.getString("tipo_activo"));
                listaActivos.add(activo);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los activos: " + e.getMessage());
        }

        return listaActivos;
    }
}
