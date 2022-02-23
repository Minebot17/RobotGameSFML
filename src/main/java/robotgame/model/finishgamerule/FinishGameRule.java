package robotgame.model.finishgamerule;

import java.lang.reflect.Field;

public interface FinishGameRule {

    void HandleGameState(Field field);

    boolean isGameOver();
    boolean isPlayerWin();
}
