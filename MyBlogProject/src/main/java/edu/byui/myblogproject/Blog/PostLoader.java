/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.myblogproject.Blog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Piedad
 */
@WebServlet(name = "PostLoader", urlPatterns = {"/PostLoader"})
public class PostLoader extends HttpServlet {

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
        List<String> commentList = new ArrayList();
        String fileName = System.getenv("OPENSHIFT_DATA_DIR") + "discussion.txt"; //access openshift persistant file

        // This will reference one line at a time
        String line = null;

        try {
            File f = new File(fileName);
            if(!f.exists()) {       //If the file doesn't exist, create it.
                f.createNewFile();
            }
            
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            //While there's another line, put them in the appropriate format
            while((line = bufferedReader.readLine()) != null) {
                String tempUser = "<p>" + line + "</p>";
                line = bufferedReader.readLine();
                String tempTime = "<p class='timeStamp'>" + line + "</p>";
                line = bufferedReader.readLine();
                String tempComment = "<p>" + line + "</p>";
                
                //add them to the queue
                commentList.add(tempUser);
                commentList.add(tempTime);
                commentList.add(tempComment);
                commentList.add("<br /><br /><br />");
            }

            // Always close files.
            bufferedReader.close();  
            
            request.setAttribute("comments", commentList); //save it in the request
            request.getRequestDispatcher("ViewPosts.jsp").forward(request, response);  //forward the request with all data
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");
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
