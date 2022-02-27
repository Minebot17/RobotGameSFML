package robotgame.model.finishgamerule.rules;

import robotgame.model.HexagonField;
import robotgame.model.finishgamerule.FinishGameRuleFactory;

public class RobotStepsFinishGameRuleFactory implements FinishGameRuleFactory {

    private final RobotStepsFinishGameRule.RuleMode ruleMode;
    private final int value;

    public RobotStepsFinishGameRuleFactory(RobotStepsFinishGameRule.RuleMode ruleMode, int value) {
        this.ruleMode = ruleMode;
        this.value = value;
    }

    public RobotStepsFinishGameRule create(HexagonField field){
        return new RobotStepsFinishGameRule(field, ruleMode, value);
    }
}
