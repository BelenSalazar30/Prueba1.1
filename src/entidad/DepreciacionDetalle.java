/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

/**
 *
 * @author belen
 */
public class DepreciacionDetalle {
    private int id;
    private int idDepreciacionCabecera;
    private int idActivo;
    private double valorDepreciado;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDepreciacionCabecera() {
        return idDepreciacionCabecera;
    }

    public void setIdDepreciacionCabecera(int idDepreciacionCabecera) {
        this.idDepreciacionCabecera = idDepreciacionCabecera;
    }

    public int getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(int idActivo) {
        this.idActivo = idActivo;
    }

    public double getValorDepreciado() {
        return valorDepreciado;
    }

    public void setValorDepreciado(double valorDepreciado) {
        this.valorDepreciado = valorDepreciado;
    }   
}
