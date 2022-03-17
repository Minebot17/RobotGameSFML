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
        StringBuilder result = new StringBuilder();
        for (FinishGameRulesHandler.RuleParameters parameters : gameRules) {
            if (parameters.ruleLinkType == RuleLinkType.OR){
                continue;
            }

            if (result.length() != 0) {
                result.append(" И ");
            }

            if (parameters.completeConditionsIsNegative){
                result.append("НЕ ");
            }

            result.append(parameters.toStringRule());
        }

        for (FinishGameRulesHandler.RuleParameters parameters : gameRules) {
            if (parameters.ruleLinkType != RuleLinkType.OR) {
                continue;
            }

            if (result.length() != 0) {
                result.append(" ИЛИ ");
            }

            if (parameters.completeConditionsIsNegative){
                result.append("НЕ ");
            }

            result.append(parameters.toStringRule());
        }

        return result.toString();
    }
}
