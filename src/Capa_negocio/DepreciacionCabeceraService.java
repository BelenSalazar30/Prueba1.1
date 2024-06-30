/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_negocio;

import Capa_persistencia.DepreciacionCabeceraDAO;
import entidad.DepreciacionCabecera;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import Capa_conexion.Conexion;
/**
 *
 * @author belen
 */

public class DepreciacionCabeceraService {
    private DepreciacionCabeceraDAO depreciacionCabeceraDAO;

    public DepreciacionCabeceraService() {
        try {
            Connection conexion = Conexion.getConnection();
            this.depreciacionCabeceraDAO = new DepreciacionCabeceraDAO(conexion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarDepreciacionCabecera(DepreciacionCabecera depreciacion) {
        try {
            depreciacionCabeceraDAO.guardarDepreciacionCabecera(depreciacion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DepreciacionCabecera buscarDepreciacionCabecera(int id) {
        try {
            return depreciacionCabeceraDAO.buscarDepreciacionCabecera(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizarDepreciacionCabecera(DepreciacionCabecera depreciacion) {
        try {
            depreciacionCabeceraDAO.actualizarDepreciacionCabecera(depreciacion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarDepreciacionCabecera(int id) {
        try {
            depreciacionCabeceraDAO.eliminarDepreciacionCabecera(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DepreciacionCabecera> obtenerTodasDepreciaciones() {
        try {
            return depreciacionCabeceraDAO.obtenerTodasDepreciaciones();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
