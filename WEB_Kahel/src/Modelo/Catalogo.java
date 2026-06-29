/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
/**
 * @author pc05l1
 */
public class Catalogo {
    private int IdProducto;
    private String Categoria;
    private String Producto;
    private String UndMed;
    private double Precio;
    private int Existencia; // ADAPTADO: Cambiado a int para contar unidades de postres reales
    public Catalogo() {
    }

    public Catalogo(int IdProducto, String Categoria, String Producto, String UndMed, double Precio,
            int Existencia) {
        this.IdProducto = IdProducto;
        this.Categoria = Categoria;
        this.Producto = Producto;
        this.UndMed = UndMed;
        this.Precio = Precio;
        this.Existencia = Existencia;
       
    }

    /**
     * @return the IdProducto
     */
    public int getIdProducto() {
        return IdProducto;
    }

    /**
     * @param IdProducto the IdProducto to set
     */
    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    /**
     * @return the Categoria
     */
    public String getCategoria() {
        return Categoria;
    }

    /**
     * @param Categoria the Categoria to set
     */
    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    /**
     * @return the Producto
     */
    public String getProducto() {
        return Producto;
    }

    /**
     * @param Producto the Producto to set
     */
    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    /**
     * @return the UndMed
     */
    public String getUndMed() {
        return UndMed;
    }

    /**
     * @param UndMed the UndMed to set
     */
    public void setUndMed(String UndMed) {
        this.UndMed = UndMed;
    }

    /**
     * @return the Precio
     */
    public double getPrecio() {
        return Precio;
    }

    /**
     * @param Precio the Precio to set
     */
    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    /**
     * @return the Existencia
     */
    public int getExistencia() {
        return Existencia;
    }

    /**
     * @param Existencia the Existencia to set
     */
    public void setExistencia(int Existencia) {
        this.Existencia = Existencia;
    }

    
}
