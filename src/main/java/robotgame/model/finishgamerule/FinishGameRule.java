package robotgame.model.finishgamerule;

public interface FinishGameRule {

    void updateGameState();

    boolean isGameOver();
    boolean isPlayerWin();
}
