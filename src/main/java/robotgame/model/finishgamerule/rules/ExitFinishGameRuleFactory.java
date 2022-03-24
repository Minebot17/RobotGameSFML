package robotgame.model.finishgamerule.rules;

import robotgame.model.HexagonField;
import robotgame.model.finishgamerule.FinishGameRuleFactory;
import robotgame.model.pathfinding.PathFinderFactory;

public class ExitFinishGameRuleFactory implements FinishGameRuleFactory {

    private final PathFinderFactory pathFinderFactory;

    public ExitFinishGameRuleFactory(PathFinderFactory pathFinderFactory) {
        this.pathFinderFactory = pathFinderFactory;
    }

    public ExitFinishGameRule create(HexagonField field){
        return new ExitFinishGameRule(field, pathFinderFactory.create(field));
    }
}
