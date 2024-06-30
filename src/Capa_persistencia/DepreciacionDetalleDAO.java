/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_persistencia;

import entidad.DepreciacionDetalle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author belen
 */
public class DepreciacionDetalleDAO {
 private Connection conexion;

    public DepreciacionDetalleDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarDepreciacionDetalle(DepreciacionDetalle detalle) throws SQLException {
        String sql = "INSERT INTO DepreciacionDetalle (idDepreciacionCabecera, idActivo, valorDepreciado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, detalle.getIdDepreciacionCabecera());
            stmt.setInt(2, detalle.getIdActivo());
            stmt.setDouble(3, detalle.getValorDepreciado());
            stmt.executeUpdate();
        }
    }

    public DepreciacionDetalle buscarDepreciacionDetalle(int id) throws SQLException {
        String sql = "SELECT * FROM DepreciacionDetalle WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    DepreciacionDetalle detalle = new DepreciacionDetalle();
                    detalle.setId(rs.getInt("id"));
                    detalle.setIdDepreciacionCabecera(rs.getInt("idDepreciacionCabecera"));
                    detalle.setIdActivo(rs.getInt("idActivo"));
                    detalle.setValorDepreciado(rs.getDouble("valorDepreciado"));
                    return detalle;
                }
            }
        }
        return null;
    }

    public void actualizarDepreciacionDetalle(DepreciacionDetalle detalle) throws SQLException {
        String sql = "UPDATE DepreciacionDetalle SET idDepreciacionCabecera = ?, idActivo = ?, valorDepreciado = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, detalle.getIdDepreciacionCabecera());
            stmt.setInt(2, detalle.getIdActivo());
            stmt.setDouble(3, detalle.getValorDepreciado());
            stmt.setInt(4, detalle.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminarDepreciacionDetalle(int id) throws SQLException {
        String sql = "DELETE FROM DepreciacionDetalle WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<DepreciacionDetalle> obtenerTodosLosDetalles() throws SQLException {
        List<DepreciacionDetalle> detalles = new ArrayList<>();
        String sql = "SELECT * FROM DepreciacionDetalle";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DepreciacionDetalle detalle = new DepreciacionDetalle();
                detalle.setId(rs.getInt("id"));
                detalle.setIdDepreciacionCabecera(rs.getInt("idDepreciacionCabecera"));
                detalle.setIdActivo(rs.getInt("idActivo"));
                detalle.setValorDepreciado(rs.getDouble("valorDepreciado"));
                detalles.add(detalle);
            }
        }
        return detalles;
    }    
}
