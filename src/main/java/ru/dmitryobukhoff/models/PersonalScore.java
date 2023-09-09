package ru.dmitryobukhoff.models;

public class PersonalScore {
    private int score;
    private int game;
    private int set;

    public void winPointWithTieBreak(){
        score += 1;
    }

    public void winTieBreak(){
        score = 0;
        game = 0;
        set = 1;
    }

    public void winGame(){
        game++;
    }

    public void winSet(){
        set++;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }


}
