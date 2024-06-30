/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

/**
 *
 * @author belen
 */
public class DepreciacionCabecera {
    private int id;
    private String fecha;
    private double totalDepreciacion;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotalDepreciacion() {
        return totalDepreciacion;
    }

    public void setTotalDepreciacion(double totalDepreciacion) {
        this.totalDepreciacion = totalDepreciacion;
    }
}
