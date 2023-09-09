package ru.dmitryobukhoff.servlets;

import ru.dmitryobukhoff.models.Match;
import ru.dmitryobukhoff.services.MatchDisplayingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "matchDisplay", value = "/matches")
public class MatchDisplayServlet extends HttpServlet {

    private static  MatchDisplayingService displayingService = new MatchDisplayingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageString = request.getParameter("page");
        String playerName = request.getParameter("filter_by_player_name");
        int page = 1;
        if(pageString != null)
            page = Integer.parseInt(pageString);
        List<Match> matches = displayingService.findMatches(page, playerName);
        request.setAttribute("page", page);
        request.setAttribute("matches",matches);
        request.getRequestDispatcher("display.jsp").forward(request, response);
    }

}
