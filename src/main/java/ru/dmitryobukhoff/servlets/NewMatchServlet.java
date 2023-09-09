package ru.dmitryobukhoff.servlets;

import ru.dmitryobukhoff.models.Match;
import ru.dmitryobukhoff.models.Player;
import ru.dmitryobukhoff.repositories.PlayerRepository;
import ru.dmitryobukhoff.services.OnGoingMatchesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "new_match", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private final OnGoingMatchesService matchesService = new OnGoingMatchesService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("new-match.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1Name =  request.getParameter("player1Name");
        String player2Name = request.getParameter("player2Name");

        Optional<Player> player1Optional = matchesService.findPlayerByName(player1Name);
        Optional<Player> player2Optional = matchesService.findPlayerByName(player2Name);
        try{
            Player player1 = convertFromOptional(player1Optional, player1Name);
            Player player2 = convertFromOptional(player2Optional, player2Name);

            String matchId = matchesService.startMatch(player1, player2);

            response.sendRedirect("/match-score?uuid=" + matchId);
        }catch (RuntimeException e){
            request.setAttribute("message", "Пользователи должны быть разные.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }



    }

    private Player convertFromOptional(Optional<Player> playerOptional, String name){
        Player player;
        if(playerOptional.isEmpty()){
            player = new Player(name);
            matchesService.createPlayer(player);
        }else
            player = playerOptional.get();
        return player;
    }
}
