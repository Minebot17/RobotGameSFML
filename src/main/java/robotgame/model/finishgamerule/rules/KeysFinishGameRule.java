package robotgame.model.finishgamerule.rules;

import robotgame.model.HexagonField;
import robotgame.model.Utils;
import robotgame.model.cellobject.CellObject;
import robotgame.model.cellobject.Key;
import robotgame.model.finishgamerule.BaseGameRule;

import java.util.List;

public class KeysFinishGameRule extends BaseGameRule {

    public KeysFinishGameRule(HexagonField field) {
        super(field);
    }

    @Override
    protected boolean isComplete() {
        List<CellObject> spawnedObjects = field.getSpawnedObjects();
        int keysCount = 0;

        for (CellObject cellObject : spawnedObjects){
            if (cellObject instanceof Key){
                keysCount++;
            }
        }
        return keysCount == 0;
    }

    @Override
    protected boolean isFail() {
        return !Utils.isRobotCanReach(field, false);
    }

    @Override
    public String toString() {
        return "собрать все ключи (метка K)";
    }
}
