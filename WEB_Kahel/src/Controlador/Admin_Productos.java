/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pc05l1
 */
public class Admin_Productos implements ctrl_Productos {

   @Override
    public Producto getProducto(int IdProd) {
        Producto Prod = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet Reg = null;
        try {
            con = Conexion.getConexion();
            String sql = "SELECT IdProducto, IdCategoria, Producto, descripcion, Precio, Existencia FROM productos WHERE IdProducto=?;";
            pst = con.prepareStatement(sql);
            pst.setInt(1, IdProd);
            Reg = pst.executeQuery();
            if (Reg.next()) {
                Prod = new Producto();
                Prod.setIdProducto(Reg.getInt("IdProducto"));
            Prod.setIdCategoria(Reg.getInt("IdCategoria"));
            Prod.setProducto(Reg.getString("Producto"));
            Prod.setDescripcion(Reg.getString("descripcion"));
            Prod.setPrecio(Reg.getDouble("Precio"));
            Prod.setExistencia(Reg.getInt("Existencia")); 
            }
        } catch (SQLException e) {
            System.out.println("Error en getProducto: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar ");
            }
        }
        return Prod;
    }

    @Override
    public ArrayList<Producto> getProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        Producto Prod = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet Reg = null;
        try {
            con = Conexion.getConexion();
            // CORRECCIÓN: Forzamos el orden de los índices del 1 al 6
            String sql = "SELECT IdProducto, IdCategoria, Producto, descripcion, Precio, Existencia FROM productos;";
            pst = con.prepareStatement(sql);
            Reg = pst.executeQuery();
            while (Reg.next()) {
                Prod = new Producto();
                Prod.setIdProducto(Reg.getInt("IdProducto"));
            Prod.setIdCategoria(Reg.getInt("IdCategoria"));
            Prod.setProducto(Reg.getString("Producto"));
            Prod.setDescripcion(Reg.getString("descripcion"));
            Prod.setPrecio(Reg.getDouble("Precio"));
            Prod.setExistencia(Reg.getInt("Existencia")); 
                lista.add(Prod);
            }
        } catch (Exception e) {
            System.out.println("Error en getProductos general: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar ");
            }
        }
        return lista;
    }

 @Override
public ArrayList<Producto> getProductos(int IdCategoria) {

    ArrayList<Producto> lista = new ArrayList<>();

    try {
        Connection con = Conexion.getConexion();

        String sql = "SELECT IdProducto, IdCategoria, Producto, descripcion, Precio, Existencia "
                   + "FROM productos WHERE IdCategoria=" + IdCategoria;

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Producto p = new Producto();

            p.setIdProducto(rs.getInt("IdProducto"));
            p.setIdCategoria(rs.getInt("IdCategoria"));
            p.setProducto(rs.getString("Producto"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setPrecio(rs.getDouble("Precio"));
            p.setExistencia(rs.getInt("Existencia"));

            lista.add(p);
        }

        rs.close();
        pst.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}
    

    

    @Override
    public int añadirProducto(Producto Prdto) {
        int r = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = Conexion.getConexion();
            String sql = "insert into productos values (?, ?, ?, ?, ?, ?, ?, 0, 0, 0, 0);";
            pst = con.prepareStatement(sql);
            pst.setInt(1, Prdto.getIdProducto());
            pst.setInt(2, Prdto.getIdCategoria());
            pst.setString(3, Prdto.getProducto());
            pst.setString(4, Prdto.getDescripcion());
            pst.setDouble(5, Prdto.getPrecio());
            pst.setDouble(6, Prdto.getExistencia());
            r = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en la sentencia " + e.getMessage());
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
        return r;
    }

    @Override
    public int actualizarProducto(Producto Prdto) {
        int r = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = Conexion.getConexion();
            String sql = "update productos set IdProducto = ?, IdCategoria = ?, Producto = ?,descripcion = ?,Precio = ?, Existencia = ? where IdProducto = ?;";
            pst = con.prepareStatement(sql);
            pst.setInt(1, Prdto.getIdProducto());
            pst.setInt(2, Prdto.getIdCategoria());
            pst.setString(3, Prdto.getProducto());
            pst.setString(4, Prdto.getDescripcion());
            pst.setDouble(5, Prdto.getPrecio());
            pst.setDouble(6, Prdto.getExistencia());
            
            r = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en la sentencia " + e.getMessage());
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
        return r;
    }

    @Override
    public int eliminarProducto(int IdProd) {
        int r = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = Conexion.getConexion();
            String sql = "delete from productos where IdProducto = ?;";
            pst = con.prepareStatement(sql);
            pst.setInt(1, IdProd);
            r = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en la sentencia " + e.getMessage());
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
        return r;
    }

  
}
