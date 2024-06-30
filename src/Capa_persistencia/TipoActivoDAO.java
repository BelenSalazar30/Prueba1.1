package Capa_persistencia;

import Capa_conexion.Conexion;
import entidad.TipoActivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoActivoDAO {
 public void guardarTipoActivo(TipoActivo tipoActivo) {
        String sql = "INSERT INTO tipoactivo (codigo, nombre) VALUES (?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, tipoActivo.getCodigo());
            statement.setString(2, tipoActivo.getNombre());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarTipoActivo(int codigo) {
        String sql = "DELETE FROM tipoactivo WHERE codigo = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, codigo);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TipoActivo buscarTipoActivo(int codigo) {
        TipoActivo tipoActivo = null;
        String sql = "SELECT * FROM tipoactivo WHERE codigo = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, codigo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tipoActivo = new TipoActivo();
                tipoActivo.setCodigo(resultSet.getInt("codigo"));
                tipoActivo.setNombre(resultSet.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipoActivo;
    }

    public List<TipoActivo> obtenerTodosLosTiposDeActivos() {
        List<TipoActivo> tiposActivos = new ArrayList<>();
        String sql = "SELECT * FROM tipoactivo";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                TipoActivo tipoActivo = new TipoActivo();
                tipoActivo.setCodigo(resultSet.getInt("codigo"));
                tipoActivo.setNombre(resultSet.getString("nombre"));
                tiposActivos.add(tipoActivo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tiposActivos;
    }
}
