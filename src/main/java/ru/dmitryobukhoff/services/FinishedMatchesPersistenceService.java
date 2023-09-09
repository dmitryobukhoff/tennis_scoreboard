package ru.dmitryobukhoff.services;

import ru.dmitryobukhoff.models.Match;
import ru.dmitryobukhoff.repositories.MatchRepository;

public class FinishedMatchesPersistenceService {
    private final MatchRepository matchRepository = new MatchRepository();

    public void save(Match match){
        matchRepository.create(match);
    }
}
