package ru.dmitryobukhoff.services;

import ru.dmitryobukhoff.models.Match;
import ru.dmitryobukhoff.models.Player;
import ru.dmitryobukhoff.repositories.PlayerRepository;
import ru.dmitryobukhoff.utils.ConcurrentHashMapStorage;

import java.util.AbstractMap;
import java.util.Optional;
import java.util.UUID;

public class OnGoingMatchesService {

    private static AbstractMap<String, Match> matches = ConcurrentHashMapStorage.getStorage();
    private final PlayerRepository playerRepository = new PlayerRepository();
    public String startMatch(Player player1, Player player2){
        Match match = new Match(player1, player2);
        String matchId = UUID.randomUUID().toString();
        matches.put(matchId, match);
        return matchId;
    }

    public Optional<Player> findPlayerByName(String name){
        return playerRepository.findByName(name);
    }
    public void createPlayer(Player player){
        playerRepository.create(player);
    }
}
