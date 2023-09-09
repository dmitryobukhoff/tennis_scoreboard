package ru.dmitryobukhoff.services;

import ru.dmitryobukhoff.enums.Players;
import ru.dmitryobukhoff.enums.WinState;
import ru.dmitryobukhoff.exceptions.MatchNotFoundException;
import ru.dmitryobukhoff.models.Match;
import ru.dmitryobukhoff.models.Player;
import ru.dmitryobukhoff.utils.ConcurrentHashMapStorage;

import java.util.AbstractMap;

public class MatchScoreCalculationService {
    private static AbstractMap<String, Match> matches = ConcurrentHashMapStorage.getStorage();

    public Match getMatch(String matchId) throws MatchNotFoundException{
        Match match = matches.get(matchId);
        if(match == null){
            throw new MatchNotFoundException("Матча с UUID: " + matchId + " не найдено.");
        }
        return match;
    }

    public Player getWinner(Match match, WinState winState){
        if(winState.equals(WinState.PLAYER1))
            return match.getPlayer1();
        else
            return match.getPlayer2();
    }

    public Players distributePoint(String player){
        if(player.equals(Players.PLAYER1.toString()))
            return Players.PLAYER1;
        else
            return Players.PLAYER2;
    }

    public void removeMatch(String matchId){
        matches.remove(matchId);
    }

    public void winPoint(Match match, Players players){
        match.getScore().winPoint(players);
    }

    public WinState getWinState(Match match){
        return match.getScore().hasPlayersWinSet();
    }
}
