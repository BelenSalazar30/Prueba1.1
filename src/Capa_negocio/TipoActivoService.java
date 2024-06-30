package Capa_negocio;

import Capa_persistencia.TipoActivoDAO;
import entidad.TipoActivo;

import java.util.List;

public class TipoActivoService {
 private TipoActivoDAO tipoActivoDAO;

    public TipoActivoService() {
        this.tipoActivoDAO = new TipoActivoDAO();
    }

    public void guardarTipoActivo(TipoActivo tipoActivo) {
        tipoActivoDAO.guardarTipoActivo(tipoActivo);
    }

    public void eliminarTipoActivo(int codigo) {
        tipoActivoDAO.eliminarTipoActivo(codigo);
    }

    public TipoActivo buscarTipoActivo(int codigo) {
        return tipoActivoDAO.buscarTipoActivo(codigo);
    }

    public List<TipoActivo> obtenerTodosLosTiposDeActivos() {
        return tipoActivoDAO.obtenerTodosLosTiposDeActivos();
    }
}
