package robotgame.model.finishgamerule.rules;

import robotgame.model.HexagonField;
import robotgame.model.finishgamerule.FinishGameRuleFactory;

public class ExitFinishGameRuleFactory implements FinishGameRuleFactory {

    public ExitFinishGameRule create(HexagonField field){
        return new ExitFinishGameRule(field);
    }
}
