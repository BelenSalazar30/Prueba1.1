/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_negocio;
import entidad.DepreciacionDetalle;
import Capa_persistencia.DepreciacionDetalleDAO;
import Capa_conexion.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author belen
 */
public class DepreciacionDetalleService {
      private DepreciacionDetalleDAO detalleDAO;

    public DepreciacionDetalleService() {
        try {
            Connection conexion = Conexion.getConnection();
            detalleDAO = new DepreciacionDetalleDAO(conexion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarDepreciacionDetalle(DepreciacionDetalle detalle) {
        try {
            detalleDAO.guardarDepreciacionDetalle(detalle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DepreciacionDetalle buscarDepreciacionDetalle(int id) {
        try {
            return detalleDAO.buscarDepreciacionDetalle(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizarDepreciacionDetalle(DepreciacionDetalle detalle) {
        try {
            detalleDAO.actualizarDepreciacionDetalle(detalle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarDepreciacionDetalle(int id) {
        try {
            detalleDAO.eliminarDepreciacionDetalle(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DepreciacionDetalle> obtenerTodosLosDetalles() {
        try {
            return detalleDAO.obtenerTodosLosDetalles();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
