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
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String _username =request.getParameter("username");
            String _password =request.getParameter("password");
            if(_username != null && _password != null){
                DBLayer l = new DBLayer();
                if(l.loginUser(_username, _password)){
                    response.sendRedirect("home.jsp");
                }else
                    response.sendRedirect("loginAgain.jsp");
            }else
                response.sendRedirect("loginAgain.jsp");
            
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
