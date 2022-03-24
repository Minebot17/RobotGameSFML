package robotgame.model.pathfinding;

import robotgame.model.HexagonField;

public class NeighborPathFinderFactory implements PathFinderFactory {

    public NeighborPathFinder create(HexagonField field){
        return new NeighborPathFinder(field);
    }
}
