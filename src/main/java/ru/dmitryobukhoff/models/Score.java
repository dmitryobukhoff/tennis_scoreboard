package ru.dmitryobukhoff.models;

import ru.dmitryobukhoff.enums.Players;
import ru.dmitryobukhoff.enums.WinState;

public class Score {
    private PersonalScore personalScore1;
    private PersonalScore personalScore2;
    private boolean isGameEnd = false;

    public Score() {
        personalScore1 = new PersonalScore();
        personalScore2 = new PersonalScore();
    }

    public WinState hasPlayersWinSet(){
        if(hasSetWon(personalScore1))
            return WinState.PLAYER1;
        else if(hasSetWon(personalScore2))
            return WinState.PLAYER2;
        else
            return WinState.NONE;
    }

    public void winPoint(Players player){
        if(!isTieBreak())
            winPointWithoutTieBreak(player);
        else
            winPointWithTieBreak(player);
    }

    private void winPointWithoutTieBreak(Players player){
        if(player.equals(Players.PLAYER1))
            setPoint(personalScore1, personalScore2);
        else
            setPoint(personalScore2, personalScore1);
    }

    private void winPointWithTieBreak(Players player){
        if(player.equals(Players.PLAYER1))
            personalScore1.winPointWithTieBreak();
        else
            personalScore2.winPointWithTieBreak();
        if((personalScore1.getScore() >= 7 || personalScore2.getScore() >= 7) && (Math.abs(personalScore1.getScore() - personalScore2.getScore()) >= 2)) {
            if (personalScore1.getScore() > personalScore2.getScore()) {
                personalScore1.winTieBreak();
                personalScore2.setScore(0);
                personalScore2.setGame(0);
            }
            else{
                personalScore2.winTieBreak();
                personalScore1.setScore(0);
                personalScore1.setGame(0);
            }
            isGameEnd = true;
        }
    }

    private void setPoint(PersonalScore winner, PersonalScore looser){
        if(winner.getScore() == 0)
            winner.setScore(15);
        else if(winner.getScore() == 15)
            winner.setScore(30);
        else if(winner.getScore() == 30)
            winner.setScore(40);
        else if(winner.getScore() == 40){
            winner.winGame();
            looser.setScore(0);
            winner.setScore(0);
            if(winner.getGame() >= 7){
                winner.winSet();
                winner.setGame(0);
                looser.setGame(0);
                isGameEnd = true;
            }
        }
    }

    public PersonalScore getPersonalScore1() {
        return personalScore1;
    }

    public PersonalScore getPersonalScore2() {
        return personalScore2;
    }

    public boolean isTieBreak(){
        return (personalScore1.getGame() == personalScore2.getGame()) && (personalScore1.getGame() == 6);
    }

    private boolean hasSetWon(PersonalScore personalScore){
        return (personalScore.getSet() == 1);
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }
}
