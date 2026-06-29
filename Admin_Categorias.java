/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pc05l1
 */
public class Admin_Categorias implements ctrl_Categorias {

    @Override
    public Categoria getCategoria(int IdCat) {
        Categoria Cat = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet Reg = null;
        try {
            con = Conexion.getConexion();
            // ADAPTADO: Apunta a la tabla 'categorias' y usa la columna correcta de BDKahel
            String sql = "SELECT IdCategoria, NombreCategoria FROM categorias WHERE IdCategoria = ?;";
            pst = con.prepareStatement(sql);
            pst.setInt(1, IdCat);
            Reg = pst.executeQuery();
            if (Reg.next()) {
                Cat = new Categoria();
                // ADAPTADO: Usamos los nombres de columna en lugar de números para evitar fallos
                Cat.setIdCategoria(Reg.getInt("IdCategoria"));
                Cat.setCategoria(Reg.getString("NombreCategoria"));
            }
        } catch (Exception e) {
            System.out.println("Error en la sentencia getCategoria: " + e.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar ");
            }
        }
        return Cat;
    }

    @Override
    public ArrayList<Categoria> getCategorias() {
        ArrayList<Categoria> lista = new ArrayList<>();
        Categoria Cat = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet Reg = null;
        try {
            con = Conexion.getConexion();
            // ADAPTADO: Trae las categorías de BDKahel
            String sql = "SELECT IdCategoria, NombreCategoria FROM categorias;";
            pst = con.prepareStatement(sql);
            Reg = pst.executeQuery();
            while (Reg.next()) {
                Cat = new Categoria();
                // ADAPTADO: Mapeo explícito por nombre de columna
                Cat.setIdCategoria(Reg.getInt("IdCategoria"));
                Cat.setCategoria(Reg.getString("NombreCategoria"));
                lista.add(Cat);
            }
        } catch (Exception e) {
            System.out.println("Error en la sentencia getCategorias: " + e.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar ");
            }
        }
        return lista;
    }

}