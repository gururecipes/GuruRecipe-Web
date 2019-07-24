    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%
             
       Cookie username = new Cookie("username",
     			  request.getParameter("username"));
       Cookie password = new Cookie("password",
    			  request.getParameter("password"));
     
       
       username.setMaxAge(60*60*10); 
       email.setMaxAge(60*60*10); 
     
       // Add both the cookies in the response header.
       response.addCookie( username );
       response.addCookie( password );
    %>
     
     
    <html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Guru Cookie JSP</title>
    </head>
    <body>
     
    <b>Username:</b>
       <%= request.getParameter("username")%>
    <b>Password:</b>
       <%= request.getParameter("password")%>
     
    </body>
    </html>