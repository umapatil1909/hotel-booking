package com.example.hotel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.google.gson.Gson;

public class RoomStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Set response content type and encoding
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        // Get the PrintWriter to send response data
        try (PrintWriter out = res.getWriter()) {
            // Retrieve room status data from backend
            Map<Integer, Boolean> statusMap = RoomStatusStore.getStatus();

            // Convert the map to JSON using Gson
            String jsonResponse = new Gson().toJson(statusMap);

            // Send JSON response to client
            out.print(jsonResponse);
            out.flush();
        }
        // PrintWriter auto-closed by try-with-resources
    }
}


