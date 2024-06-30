/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

/**
 *
 * @author belen
 */
public class Activo {
     private int id;
    private String nombre;
    private int periodosDepreciacionTotal;
    private double valorCompra;
    private String tipoActivo;

    // Constructor vacío (default)
    public Activo() {
    }

    // Constructor con parámetros
    public Activo(int id, String nombre, int periodosDepreciacionTotal, double valorCompra, String tipoActivo) {
        this.id = id;
        this.nombre = nombre;
        this.periodosDepreciacionTotal = periodosDepreciacionTotal;
        this.valorCompra = valorCompra;
        this.tipoActivo = tipoActivo;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeriodosDepreciacionTotal() {
        return periodosDepreciacionTotal;
    }

    public void setPeriodosDepreciacionTotal(int periodosDepreciacionTotal) {
        this.periodosDepreciacionTotal = periodosDepreciacionTotal;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getTipoActivo() {
        return tipoActivo;
    }

    public void setTipoActivo(String tipoActivo) {
        this.tipoActivo = tipoActivo;
    }

    // Método toString para imprimir los datos del activo
    @Override
    public String toString() {
        return "Activo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", periodosDepreciacionTotal=" + periodosDepreciacionTotal +
                ", valorCompra=" + valorCompra +
                ", tipoActivo='" + tipoActivo + '\'' +
                '}';
    }
}
