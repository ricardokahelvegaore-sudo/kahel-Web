package Controlador;


import Modelo.Categoria;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc05l1
 */
public interface ctrl_Categorias {
    public Categoria getCategoria(int IdCategoria);
    public ArrayList<Categoria> getCategorias();
    // Metodos CRUD
}
