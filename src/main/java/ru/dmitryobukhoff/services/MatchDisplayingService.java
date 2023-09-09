package ru.dmitryobukhoff.services;

import ru.dmitryobukhoff.models.Match;
import ru.dmitryobukhoff.repositories.MatchRepository;

import java.util.List;

public class MatchDisplayingService {
    private static MatchRepository matchRepository = new MatchRepository();

    public List<Match> findMatches(int page, String name){
        if(name == null)
            return matchRepository.findMatches(page);
        return matchRepository.findMatches(page, name);
    }


}
