/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.myblogproject.Blog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Piedad
 */
@WebServlet(name = "Blog", urlPatterns = {"/Blog"})
public class Blog extends HttpServlet {

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
        String searchTerm = request.getParameter("searchTerm");     //Get the query
        searchTerm = "https://www.googleapis.com/blogger/v2/blogs/blogId=" + searchTerm;     //Create a string for the url
        URL url = new URL(searchTerm);                              //Create the new url

        ObjectMapper mapper = new ObjectMapper();                   //Create an objectMapper
        PrintWriter out = response.getWriter();
        String comment = request.getParameter("commentInput");
        HttpSession session = request.getSession(true);
        String user = session.getAttribute("user").toString();
        ServletContext servletContext = request.getServletContext();
        String fileName = System.getenv("OPENSHIFT_DATA_DIR") + "discussion.txt";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));

            String time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()); 
            out.write(user + "\n" + time + "\n" + comment + "\n"); 
            out.close();   
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
        try {
            response.sendRedirect("PostLoader");
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
