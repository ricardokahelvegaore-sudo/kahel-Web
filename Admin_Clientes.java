/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author pc05l1
 */
public class Admin_Clientes implements ctrl_Clientes {

    @Override
    public ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> lstClientes = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet Reg = null;
        
        try {
            con = Conexion.getConexion();
            // ADAPTADO: Sentencia SQL para tu tabla de BDKahel
            String sql = "SELECT id_cliente, nombre FROM clientes;";
            pst = con.prepareStatement(sql);
            Reg = pst.executeQuery();
            
            while (Reg.next()) {
                Cliente cli = new Cliente();
                cli.setIdCliente(Reg.getInt("IdCliente"));
                cli.setNombre(Reg.getString("Nombre"));
                lstClientes.add(cli);
            }
            
        } catch (Exception e) {
            System.out.println("Error al listar clientes en Admin_Clientes: " + e.getMessage());
        } finally {
            try {
                if (Reg != null) Reg.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones en Admin_Clientes");
            }
        }
        
        return lstClientes;
    }
}