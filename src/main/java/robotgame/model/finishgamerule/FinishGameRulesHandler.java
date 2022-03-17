package robotgame.model.finishgamerule;

import robotgame.model.HexagonField;

import java.util.List;

public class FinishGameRulesHandler {

    private final List<RuleParameters> gameRulesParameters;
    private boolean isGameOver;
    private boolean isPlayerWin;

    public FinishGameRulesHandler(HexagonField field, List<RuleParameters> gameRulesParameters){
        this.gameRulesParameters = gameRulesParameters;

        for (RuleParameters gameRulesParameter : gameRulesParameters){
            gameRulesParameter.initialize(field);
        }
    }

    public void updateGameState(){
        if (isGameOver){
            return;
        }

        boolean haveNotIndependent = false;
        boolean notIndependentComplete = true;
        boolean notIndependentFail = false;

        int independentCount = 0;
        int failIndependentCount = 0;

        for (RuleParameters parameters : gameRulesParameters) {
            parameters.rule.updateGameState();

            boolean isCompleteConditionsMet = parameters.rule.isCompleteConditionsMet();
            boolean isFailConditionsMet = parameters.rule.isFailConditionsMet();

            if (parameters.completeConditionsIsNegative){
                isCompleteConditionsMet = !isCompleteConditionsMet;
            }

            if (parameters.ruleLinkType != RuleLinkType.OR){
                haveNotIndependent = true;
                notIndependentComplete &= isCompleteConditionsMet;

                if (!parameters.completeConditionsIsNegative) {
                    notIndependentFail |= isFailConditionsMet;
                }
            }
            else {
                if (isCompleteConditionsMet){
                    setGameOver(true);
                    return;
                }

                independentCount++;

                if (!parameters.completeConditionsIsNegative && isFailConditionsMet){
                    failIndependentCount++;
                }
            }
        }

        if (haveNotIndependent){
            if (notIndependentComplete){
                setGameOver(true);
                return;
            }

            if (notIndependentFail){
                if (independentCount == 0){
                    setGameOver(false);
                    return;
                }

                failIndependentCount++;
            }

            independentCount++;
        }

        if (independentCount == failIndependentCount){
            setGameOver(false);
        }
    }

    public boolean isGameOver(){
        return isGameOver;
    }

    public boolean isPlayerWin(){
        return isPlayerWin;
    }

    private void setGameOver(boolean isPlayerWin){
        isGameOver = true;
        this.isPlayerWin = isPlayerWin;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (RuleParameters parameters : gameRulesParameters) {
            if (parameters.ruleLinkType == RuleLinkType.OR){
                continue;
            }

            if (result.length() != 0) {
                result.append(" И ");
            }

            if (parameters.completeConditionsIsNegative){
                result.append("НЕ ");
            }

            result.append(parameters.rule.toString());
        }

        for (RuleParameters parameters : gameRulesParameters) {
            if (parameters.ruleLinkType != RuleLinkType.OR) {
                continue;
            }

            if (result.length() != 0) {
                result.append(" ИЛИ ");
            }

            if (parameters.completeConditionsIsNegative){
                result.append("НЕ ");
            }

            result.append(parameters.rule.toString());
        }

        return result.toString();
    }

    public static class RuleParameters {

        private FinishGameRuleFactory ruleFactory;
        private FinishGameRule rule;

        public final boolean completeConditionsIsNegative;
        public final RuleLinkType ruleLinkType;

        public RuleParameters(FinishGameRuleFactory ruleFactory, boolean completeConditionsIsNegative, RuleLinkType ruleLinkType) {
            this.ruleFactory = ruleFactory;
            this.completeConditionsIsNegative = completeConditionsIsNegative;
            this.ruleLinkType = ruleLinkType;
        }

        public void initialize(HexagonField field){
            rule = ruleFactory.create(field);
            ruleFactory = null;
        }

        public String toStringRule(){
            return rule == null ? ruleFactory.create(null).toString() : rule.toString();
        }
    }
}
