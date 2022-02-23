package robotgame.model.finishgamerule;

import java.lang.reflect.Field;

public class ExitWithKeysFinishGameRule implements FinishGameRule {

    private boolean isGameOver;
    private boolean isPlayerWin;

    @Override
    public void HandleGameState(Field field) {
        // TODO check field for keys and robot on exit
    }

    @Override
    public boolean isGameOver() {
        return isGameOver;
    }

    @Override
    public boolean isPlayerWin() {
        return isPlayerWin;
    }
}
