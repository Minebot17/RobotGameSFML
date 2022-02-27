package robotgame.model.finishgamerule.rules;

import robotgame.model.HexagonField;
import robotgame.model.finishgamerule.FinishGameRuleFactory;

public class KeysFinishGameRuleFactory implements FinishGameRuleFactory {

    public KeysFinishGameRule create(HexagonField field){
        return new KeysFinishGameRule(field);
    }
}
