package robotgame.model.finishgamerule.rules;

import robotgame.model.HexagonField;
import robotgame.model.finishgamerule.FinishGameRuleFactory;
import robotgame.model.pathfinding.PathFinderFactory;

public class KeysFinishGameRuleFactory implements FinishGameRuleFactory {

    private final PathFinderFactory pathFinderFactory;

    public KeysFinishGameRuleFactory(PathFinderFactory pathFinderFactory) {
        this.pathFinderFactory = pathFinderFactory;
    }

    public KeysFinishGameRule create(HexagonField field){
        return new KeysFinishGameRule(field, pathFinderFactory.create(field));
    }
}
