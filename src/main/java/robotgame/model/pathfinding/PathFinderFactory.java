package robotgame.model.pathfinding;

import robotgame.model.HexagonField;

public interface PathFinderFactory {

    NeighborPathFinder create(HexagonField field);
}
