package robotgame.model.finishgamerule;

import robotgame.model.HexagonField;
import robotgame.model.Utils;

import java.util.List;

public class FinishGameRulesHandlerFactory {

    private final List<FinishGameRulesHandler.RuleParameters> gameRules;

    public FinishGameRulesHandlerFactory(List<FinishGameRulesHandler.RuleParameters> gameRules){
        this.gameRules = gameRules;
    }

    public FinishGameRulesHandler create(HexagonField field){
        return new FinishGameRulesHandler(field, gameRules);
    }

    @Override
    public String toString() {
        return Utils.toString(gameRules);
    }
}
