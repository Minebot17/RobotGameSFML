package robotgame.model.finishgamerule;

import robotgame.model.HexagonField;

public interface FinishGameRule {

    void handleGameState(HexagonField field);

    boolean isGameOver();
    boolean isPlayerWin();
}
