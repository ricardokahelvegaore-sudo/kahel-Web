/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author pc05l1
 */
public class Admin_Pedidos implements ctrl_Pedidos {

    @Override
    public int guardarPedido(Pedido Ped) {
        int filasAfectadas = 0;
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = Conexion.getConexion();
            String sql = "INSERT INTO ventas (id_cliente, fecha_venta, total) VALUES (?, NOW(), ?);";
            pst = con.prepareStatement(sql);
            pst.setInt(1, Ped.getIdCliente());
            pst.setDouble(2, Ped.getTotal());   
            
            filasAfectadas = pst.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Error al guardar pedido en BDKahel: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones en guardarPedido");
            }
        }
        return filasAfectadas; 
    }

    @Override
    public int UltimoPedido() {
        int Ultimo = 0;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet Reg = null;
        try {
            con = Conexion.getConexion();
            // Buscamos el ID máximo de la tabla 'ventas' (id_venta) en BDKahel
            String sql = "Select Max(id_venta) as Ultimo from ventas;";
            
            pst = con.prepareStatement(sql);
            Reg = pst.executeQuery();
            if (Reg.next()) {
                Ultimo = Reg.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error en la sentencia UltimoPedido: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones en UltimoPedido");
            }
        }
        return Ultimo;        
    }
    
}
