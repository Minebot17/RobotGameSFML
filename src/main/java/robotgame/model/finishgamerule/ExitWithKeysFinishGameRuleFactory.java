package robotgame.model.finishgamerule;

import robotgame.model.HexagonField;

public class ExitWithKeysFinishGameRuleFactory implements FinishGameRuleFactory {

    @Override
    public FinishGameRule create(HexagonField field) {
        return new ExitWithKeysFinishGameRule(field);
    }
}
