/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_persistencia;

import entidad.DepreciacionCabecera;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author belen
 */
public class DepreciacionCabeceraDAO {
    private Connection conexion;

    public DepreciacionCabeceraDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarDepreciacionCabecera(DepreciacionCabecera depreciacion) throws SQLException {
        String sql = "INSERT INTO DepreciacionCabecera (fecha, totalDepreciacion) VALUES (?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, depreciacion.getFecha());
            statement.setDouble(2, depreciacion.getTotalDepreciacion());
            statement.executeUpdate();
        }
    }

    public DepreciacionCabecera buscarDepreciacionCabecera(int id) throws SQLException {
        String sql = "SELECT * FROM DepreciacionCabecera WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    DepreciacionCabecera depreciacion = new DepreciacionCabecera();
                    depreciacion.setId(resultSet.getInt("id"));
                    depreciacion.setFecha(resultSet.getString("fecha"));
                    depreciacion.setTotalDepreciacion(resultSet.getDouble("totalDepreciacion"));
                    return depreciacion;
                } else {
                    return null;
                }
            }
        }
    }

    public void actualizarDepreciacionCabecera(DepreciacionCabecera depreciacion) throws SQLException {
        String sql = "UPDATE DepreciacionCabecera SET fecha = ?, totalDepreciacion = ? WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, depreciacion.getFecha());
            statement.setDouble(2, depreciacion.getTotalDepreciacion());
            statement.setInt(3, depreciacion.getId());
            statement.executeUpdate();
        }
    }

    public void eliminarDepreciacionCabecera(int id) throws SQLException {
        String sql = "DELETE FROM DepreciacionCabecera WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<DepreciacionCabecera> obtenerTodasDepreciaciones() throws SQLException {
        String sql = "SELECT * FROM DepreciacionCabecera";
        List<DepreciacionCabecera> depreciaciones = new ArrayList<>();
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                DepreciacionCabecera depreciacion = new DepreciacionCabecera();
                depreciacion.setId(resultSet.getInt("id"));
                depreciacion.setFecha(resultSet.getString("fecha"));
                depreciacion.setTotalDepreciacion(resultSet.getDouble("totalDepreciacion"));
                depreciaciones.add(depreciacion);
            }
        }
        return depreciaciones;
    }
    
}
