package com.example.file.demo3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "come.example.file.demo3.AddServlet",
        urlPatterns = "/add"
)
public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer=resp.getWriter();

        writer.println("Hello world");
        String a = req.getParameter("a");
        String b = req.getParameter("b");
        writer.println(a+b+"=result");
    }
}
