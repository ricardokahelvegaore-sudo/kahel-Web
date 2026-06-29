/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author docente
 */
public class Pedido {
private int IdPedido;
private int IdCliente; 
private int IdEmpleado; 
private Date FechaPedido; 
private double Total;

    public Pedido() {
    }

    public Pedido(int IdPedido, int IdCliente, int IdEmpleado, Date FechaPedido,double Total) {
        this.IdPedido = IdPedido;
        this.IdCliente = IdCliente;
        this.IdEmpleado = IdEmpleado;
        this.FechaPedido = FechaPedido;
        this.Total=Total;
        
    }

    /**
     * @return the IdPedido
     */
    public int getIdPedido() {
        return IdPedido;
    }

    /**
     * @param IdPedido the IdPedido to set
     */
    public void setIdPedido(int IdPedido) {
        this.IdPedido = IdPedido;
    }

    /**
     * @return the IdEmpleado
     */
    public int getIdEmpleado() {
        return IdEmpleado;
    }

    /**
     * @param IdEmpleado the IdEmpleado to set
     */
    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    /**
     * @return the FechaPedido
     */
    public Date getFechaPedido() {
        return FechaPedido;
    }

    /**
     * @param FechaPedido the FechaPedido to set
     */
    public void setFechaPedido(Date FechaPedido) {
        this.FechaPedido = FechaPedido;
    }

    /**
     * @return the Total
     */
    public double getTotal() {
        return Total;
    }

    /**
     * @param Total the Total to set
     */
    public void setTotal(double Total) {
        this.Total = Total;
    }

    /**
     * @param IdCliente the IdCliente to set
     */
    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    /**
     * @return the IdCliente
     */
    public int getIdCliente() {
        return IdCliente;
    }

  
}
