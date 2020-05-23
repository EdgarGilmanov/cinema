package ru.job4j.cinema.servlets;

import ru.job4j.cinema.model.Cinema;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HallServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cinema cinema = Cinema.instOf();
        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        int row = Integer.parseInt(req.getParameter("row"));
        int place = Integer.parseInt(req.getParameter("place"));
        boolean res = cinema.isPlaceTaken(row, place);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.print(res);
        writer.flush();
    }
}
