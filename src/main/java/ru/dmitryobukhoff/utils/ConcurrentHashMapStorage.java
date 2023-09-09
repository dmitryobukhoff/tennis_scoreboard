package ru.dmitryobukhoff.utils;

import ru.dmitryobukhoff.models.Match;

import java.util.AbstractMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapStorage {
    private static AbstractMap<String, Match> matches;

    public static AbstractMap<String, Match> getStorage(){
        if(matches == null)
            matches = new ConcurrentHashMap<>();
        return matches;
    }
}
