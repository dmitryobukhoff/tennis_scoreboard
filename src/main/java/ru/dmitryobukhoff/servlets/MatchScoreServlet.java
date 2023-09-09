package ru.dmitryobukhoff.servlets;

import ru.dmitryobukhoff.enums.Players;
import ru.dmitryobukhoff.enums.WinState;
import ru.dmitryobukhoff.exceptions.MatchNotFoundException;
import ru.dmitryobukhoff.models.Match;
import ru.dmitryobukhoff.models.Player;
import ru.dmitryobukhoff.services.FinishedMatchesPersistenceService;
import ru.dmitryobukhoff.services.MatchScoreCalculationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "matchScore", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final MatchScoreCalculationService calculationService = new MatchScoreCalculationService();
    private final FinishedMatchesPersistenceService persistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matchId = request.getParameter("uuid");
        if(matchId == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Параметр UUID отсутствует в запросе.");
            return;
        }
        try{
            Match match = calculationService.getMatch(matchId);
            request.setAttribute("match", match);
            request.getRequestDispatcher("score-match.jsp").forward(request, response);
        } catch (MatchNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matchId = request.getParameter("uuid");
        String player = request.getParameter("playerWon");
        if(matchId == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Параметр UUID отсутствует в запросе.");
            return;
        }
        try{
            Match match = calculationService.getMatch(matchId);
            Players players = calculationService.distributePoint(player);
            calculationService.winPoint(match, players);
            WinState winState = calculationService.getWinState(match);
            if(!winState.equals(WinState.NONE)){
                Player winner = calculationService.getWinner(match, winState);
                match.setWinner(winner);
                persistenceService.save(match);
                calculationService.removeMatch(matchId);
            }
            request.setAttribute("match", match);
            request.getRequestDispatcher("score-match.jsp").forward(request, response);
        }catch (MatchNotFoundException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }
}
