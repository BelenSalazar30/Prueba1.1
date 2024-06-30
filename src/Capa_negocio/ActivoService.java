package Capa_negocio;

import Capa_persistencia.ActivoDAO;
import entidad.Activo;

import java.util.List;

public class ActivoService {
    private ActivoDAO activoDAO;

    public ActivoService() {
        this.activoDAO = new ActivoDAO();
    }

    public void guardarActivo(Activo activo) {
        activoDAO.guardarActivo(activo);
    }

    public Activo buscarActivo(int id) {
        return activoDAO.buscarActivo(id);
    }

    public void actualizarActivo(Activo activo) {
        activoDAO.actualizarActivo(activo);
    }

    public void eliminarActivo(int id) {
        activoDAO.eliminarActivo(id);
    }

    public List<Activo> obtenerTodosActivos() {
        return activoDAO.obtenerTodosActivos();
    }
}
