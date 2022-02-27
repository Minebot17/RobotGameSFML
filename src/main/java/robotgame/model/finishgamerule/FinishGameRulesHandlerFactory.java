package robotgame.model.finishgamerule;

import robotgame.model.HexagonField;

import java.util.List;

public class FinishGameRulesHandlerFactory {

    private final List<FinishGameRuleFactory> gameRules;

    public FinishGameRulesHandlerFactory(List<FinishGameRuleFactory> gameRules){
        this.gameRules = gameRules;
    }

    public FinishGameRulesHandler create(HexagonField field){
        return new FinishGameRulesHandler(field, gameRules);
    }
}
