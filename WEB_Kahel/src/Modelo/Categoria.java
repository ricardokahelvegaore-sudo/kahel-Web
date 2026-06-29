/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class Categoria {
    private int IdCategoria;
    private String Categoria;

    public Categoria() {
    }

    public Categoria(int IdCategoria, String Categoria) {
        this.IdCategoria = IdCategoria;
        this.Categoria = Categoria;
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

}
