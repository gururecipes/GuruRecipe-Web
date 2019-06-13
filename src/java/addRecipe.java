/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yukselkaradeniz
 */
@WebServlet(urlPatterns = {"/addRecipe"})
public class addRecipe extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String _recipeName =request.getParameter("recipeName");
            String _content =request.getParameter("content");
            ///
            if(_recipeName != null && _content != null){
                DBLayer l = new DBLayer();
                l.addRecipe(_content, 0, "", null ,_recipeName);
                response.sendRedirect("home.jsp");
            }else
                response.sendRedirect("addRecipe.jsp");
            
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}