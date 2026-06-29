/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Producto;
import java.util.ArrayList;

/**
 *
 * @author pc05l1
 */
public interface ctrl_Productos {
    public Producto getProducto(int IdProd);
    public ArrayList<Producto> getProductos();
    public ArrayList<Producto> getProductos(int IdCategoria);
    public int añadirProducto(Producto Prdto);
    public int actualizarProducto(Producto Prdto);
    public int eliminarProducto(int IdProd);

    
}
