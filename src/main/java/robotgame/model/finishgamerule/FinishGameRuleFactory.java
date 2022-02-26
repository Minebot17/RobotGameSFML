package robotgame.model.finishgamerule;

import robotgame.model.HexagonField;

public interface FinishGameRuleFactory {

    FinishGameRule create(HexagonField field);
}
