package com.example.file.demo3;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


public class CalculateServlete extends HttpServlet {

  /*  @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println(req.getMethod());
        PrintWriter printWriter=resp.getWriter();
        printWriter.println("Hello from Servlet");
        printWriter.println("It is time"+ new Date());
         String username = req.getParameter("username");
         printWriter.println("username "  + username);
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        System.out.println(req.getMethod());
        PrintWriter printWriter=resp.getWriter();
        printWriter.println("Hello from Servlet");
        printWriter.println("It is time"+ new Date());
        String username = req.getParameter("username");
        printWriter.println("username "  + username);
    }
}
