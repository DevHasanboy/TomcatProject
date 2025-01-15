package com.example.file.demo3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private final Map<Integer, String> productDatabase = new HashMap<>();
    private int idCounter = 1;

    // CREATE (POST): Yangi mahsulot qo'shish
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productName = req.getParameter("name");
        if (productName == null || productName.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Product name is required!");
            return;
        }
        productDatabase.put(idCounter, productName);
        resp.getWriter().println("Product added with ID: " + idCounter);
        idCounter++;
    }

    // READ (GET): Mahsulotni ko'rish
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        PrintWriter writer = resp.getWriter();

        if (idParam == null) {
            writer.println("All Products:");
            productDatabase.forEach((id, name) -> writer.println(id + ": " + name));
        } else {
            try {
                int id = Integer.parseInt(idParam);
                String product = productDatabase.get(id);
                if (product == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    writer.println("Product not found!");
                } else {
                    writer.println("Product ID: " + id + ", Name: " + product);
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.println("Invalid ID format!");
            }
        }
    }

    // UPDATE (PUT): Mahsulotni yangilash
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        String newName = req.getParameter("name");

        if (idParam == null || newName == null || newName.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("ID and new name are required!");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            if (!productDatabase.containsKey(id)) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("Product not found!");
                return;
            }
            productDatabase.put(id, newName);
            resp.getWriter().println("Product updated with ID: " + id);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Invalid ID format!");
        }
    }

    // DELETE (DELETE): Mahsulotni o'chirish
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");

        if (idParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("ID is required!");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            if (productDatabase.remove(id) == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("Product not found!");
            } else {
                resp.getWriter().println("Product deleted with ID: " + id);
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Invalid ID format!");
        }
    }
}

