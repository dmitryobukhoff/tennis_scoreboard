import org.junit.Test;
import ru.dmitryobukhoff.enums.Players;
import ru.dmitryobukhoff.enums.WinState;
import ru.dmitryobukhoff.models.Score;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WinStateTest {

    @Test
    public void winState(){
        Score score = new Score();

        // Первый игрок выигрывает 27 раз подряд 6:40
        for(int i = 0; i < 27; i++){
            score.winPoint(Players.PLAYER1);
            assertEquals(WinState.NONE, score.hasPlayersWinSet());
        }
        // Следующий выигрыш = победа.
        score.winPoint(Players.PLAYER1);
        assertEquals(WinState.PLAYER1, score.hasPlayersWinSet());
    }

    @Test
    public void isTieBreak(){
        Score score = new Score();

        for(int i = 0; i < 24; i++){
            score.winPoint(Players.PLAYER1);
            assertEquals(WinState.NONE, score.hasPlayersWinSet());
        }

        for(int i = 0; i < 24; i++){
            score.winPoint(Players.PLAYER2);
            assertEquals(WinState.NONE, score.hasPlayersWinSet());
        }

        assertTrue(score.isTieBreak());
    }

    @Test
    public void notWinWithTieBreak(){
        Score score = new Score();

        for(int i = 0; i < 24; i++){
            score.winPoint(Players.PLAYER1);
            assertEquals(WinState.NONE, score.hasPlayersWinSet());
        }

        for(int i = 0; i < 24; i++){
            score.winPoint(Players.PLAYER2);
            assertEquals(WinState.NONE, score.hasPlayersWinSet());
        }

        assertTrue(score.isTieBreak());

        for(int i = 0; i < 6; i++){
            score.winPoint(Players.PLAYER1);
            assertEquals(WinState.NONE, score.hasPlayersWinSet());
        }

        for(int i = 0; i < 7; i++){
            score.winPoint(Players.PLAYER2);
            assertEquals(WinState.NONE, score.hasPlayersWinSet());
        }

        assertEquals(WinState.NONE, score.hasPlayersWinSet());
        score.winPoint(Players.PLAYER2);
        assertEquals(WinState.PLAYER2, score.hasPlayersWinSet());
    }


}
