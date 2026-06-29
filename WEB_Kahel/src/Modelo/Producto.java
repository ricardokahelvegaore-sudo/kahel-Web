/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author pc05l1
 */
public class Producto {
    private int IdProducto;
    private int IdCategoria;
    private String Producto;
    private double Precio;
    private int Existencia;
    private String descripcion;

    public Producto() {
    }

    public Producto(int IdProducto, int IdCategoria, String Producto, String UndMed, double Precio, int Existencia, int IdProveedor,String descripcion) {
        this.IdProducto = IdProducto;
        this.IdCategoria = IdCategoria;
        this.Producto = Producto;
        this.Precio = Precio;
        this.Existencia = Existencia;
        this.descripcion = descripcion ;
        
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
     * @return the IdCategoria
     */
    public int getIdCategoria() {
        return IdCategoria;
    }

    /**
     * @param IdCategoria the IdCategoria to set
     */
    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
