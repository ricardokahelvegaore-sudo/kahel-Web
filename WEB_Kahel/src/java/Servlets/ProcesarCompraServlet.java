/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Controlador.Admin_Pedidos; // Tu controlador para base de datos
import Modelo.Pedido;             // Tu clase entidad o molde
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProcesarCompraServlet", urlPatterns = {"/ProcesarCompraServlet"})
public class ProcesarCompraServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            // 1. Leer todo el JSON que manda el carrito (Trae el total y la lista de productos)
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String datosJSON = sb.toString();
            
            // 2. Extraer de forma nativa el campo "total"
            int indexTotal = datosJSON.indexOf("\"total\":") + 8;
            int indexFinTotal = datosJSON.indexOf(",", indexTotal); 
            if(indexFinTotal == -1) indexFinTotal = datosJSON.indexOf("}", indexTotal);
            String totalStr = datosJSON.substring(indexTotal, indexFinTotal).trim();
            double totalFinal = Double.parseDouble(totalStr);

            // 3. REGISTRAR EL PEDIDO USANDO TUS CLASES
            Admin_Pedidos admin = new Admin_Pedidos();
            Pedido miPedido = new Pedido();
            
            miPedido.setIdCliente(1);      // ID del cliente fijo o de sesión
            miPedido.setTotal(totalFinal); // Monto acumulado exacto
            
            int resultadoPedido = admin.guardarPedido(miPedido);
            
            // 4. SI EL PEDIDO SE GUARDÓ BIEN, PROCESAMOS EL DESCUENTO DE STOCK
            if (resultadoPedido > 0) {
                
                // Conexión limpia a tu Base de Datos BDKahel
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDKahel", "root", "1605"); // Pon tu clave real de MySQL
                
                // Sentencia SQL para restar las porciones o queques vendidos de tu stock actual
                String sqlStock = "UPDATE productos SET stock = stock - ? WHERE id = ?";
                PreparedStatement psStock = cn.prepareStatement(sqlStock);
                
                // Buscamos dentro del texto JSON los productos para procesarlos uno por uno
                int pos = 0;
                while ((pos = datosJSON.indexOf("{\"id\":", pos)) != -1) {
                    // Extraer ID del producto
                    int startId = pos + 6;
                    int endId = datosJSON.indexOf(",", startId);
                    int idProd = Integer.parseInt(datosJSON.substring(startId, endId).trim());
                    
                    // Extraer la Cantidad comprada de ese producto
                    int posCant = datosJSON.indexOf("\"cantidad\":", pos);
                    int startCant = posCant + 11;
                    int endCant = datosJSON.indexOf(",", startCant);
                    if(endCant == -1 || endCant > datosJSON.indexOf("}", startCant)) {
                        endCant = datosJSON.indexOf("}", startCant);
                    }
                    int cantidadProd = Integer.parseInt(datosJSON.substring(startCant, endCant).trim());
                    
                    // Mandar los valores al UPDATE del Stock
                    psStock.setInt(1, cantidadProd);
                    psStock.setInt(2, idProd);
                    psStock.executeUpdate(); // Ejecuta la resta en tu MySQL
                    
                    pos = startCant; // Avanza al siguiente elemento del carrito
                }
                
                psStock.close();
                cn.close();
                
                out.print("OK"); // Le avisa al script2.js que la venta y el stock se procesaron con éxito
            } else {
                out.print("ERROR");
            }
            
        } catch (Exception e) {
            System.out.println("Error procesando pedido y stock en backend: " + e.getMessage());
            out.print("ERROR");
        }
    }
}
