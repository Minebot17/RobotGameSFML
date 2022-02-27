package robotgame.model.finishgamerule;

public interface FinishGameRule {

    void updateGameState();
    boolean isCompleteConditionsMet();
    boolean isFailConditionsMet();
}
