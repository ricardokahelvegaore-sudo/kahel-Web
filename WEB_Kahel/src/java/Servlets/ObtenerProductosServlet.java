/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Controlador.Admin_Productos;
import Modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KaHeL
 */
@WebServlet(name = "ObtenerProductosServlet", urlPatterns = {"/ObtenerProductosServlet"})
public class ObtenerProductosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ObtenerProductosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ObtenerProductosServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");
    PrintWriter out = response.getWriter();

    String paramCat = request.getParameter("cat");

    if (paramCat == null || paramCat.trim().isEmpty()) {
        out.print("[]");
        return;
    }
System.out.println("Entró al servlet");
System.out.println("Cat = " + request.getParameter("cat"));
    try {
        int IdCategoria = Integer.parseInt(paramCat.trim());

        Controlador.Admin_Productos admin = new Controlador.Admin_Productos();
        ArrayList<Modelo.Producto> lista = admin.getProductos(IdCategoria);
System.out.println("Tamaño lista = " + lista.size());
        out.print("[");
        System.out.println("Tamaño lista = " + lista.size());
        for (int i = 0; i < lista.size(); i++) {
            Modelo.Producto p = lista.get(i);

            out.print("{");
            out.print("\"id\":" + p.getIdProducto() + ",");
            out.print("\"nombre\":\"" + p.getProducto().replace("\"", "\\\"") + "\",");
            out.print("\"precio\":" + p.getPrecio() + ",");
            out.print("\"stock\":" + p.getExistencia());
            out.print("}");

            if (i < lista.size() - 1) {
                out.print(",");
            }
        }
        out.print("]");

    }catch (Exception e) {
    e.printStackTrace();
    out.print("[]");
}
}
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
  @Override
    public String getServletInfo() {
        return "Buscador de Productos Kahel";
    }

}
