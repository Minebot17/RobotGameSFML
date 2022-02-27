package robotgame.model.finishgamerule;

import robotgame.model.HexagonField;
import robotgame.model.finishgamerule.FinishGameRule;

public abstract class BaseGameRule implements FinishGameRule {

    protected HexagonField field;
    private boolean isCompleteConditionsMet;
    private boolean isFailConditionsMet;

    public BaseGameRule(HexagonField field) {
        this.field = field;
    }

    @Override
    public void updateGameState() {
        isCompleteConditionsMet = isComplete();
        isFailConditionsMet = isFail();
    }

    @Override
    public boolean isCompleteConditionsMet() {
        return isCompleteConditionsMet;
    }

    @Override
    public boolean isFailConditionsMet() {
        return isFailConditionsMet;
    }

    protected abstract boolean isComplete();
    protected abstract boolean isFail();
}
