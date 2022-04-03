import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robotgame.model.*;
import robotgame.model.cell.*;
import robotgame.model.cellobject.*;
import robotgame.model.fieldgeneration.*;
import robotgame.model.finishgamerule.*;
import robotgame.model.finishgamerule.rules.*;
import robotgame.model.pathfinding.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommonTests {

    private final int fieldWidth = 7;
    private final int fieldHeight = 11;
    private final PathFinderFactory pathFinder = new NeighborPathFinderFactory();
    private final FinishGameRulesHandlerFactory finishGameRulesHandlerFactory = new FinishGameRulesHandlerFactory(
            new ArrayList<>() {{
                add(new FinishGameRulesHandler.RuleParameters(new ExitFinishGameRuleFactory(pathFinder), false, RuleLinkType.AND));
                add(new FinishGameRulesHandler.RuleParameters(new KeysFinishGameRuleFactory(pathFinder), false, RuleLinkType.AND));
            }}
    );

    private Game game;
    private HexagonField field;
    private Robot robot;

    @BeforeEach
    public void Setup(){
        game = new Game(finishGameRulesHandlerFactory, new TestsFieldFactory(fieldWidth, fieldHeight));
        field = game.getField();
        robot = (Robot) field.getSpawnedObjects().stream().filter(obj -> obj instanceof Robot).findFirst().get();
    }

    @Test
    // заспавнить поле размером width и height > проверить, что получившиеся размеры совпадают
    public void FieldSizeTest(){
        assertEquals(fieldWidth, field.getWidth());
        assertEquals(fieldHeight, field.getHeight());

        for (int x = 0; x < fieldWidth; x++){
            for (int y = 0; y < fieldHeight; y++){
                assertNotNull(field.getCell(new Position(x, y)), "x: " + x + " y: " + y + " is null");
            }
        }

        assertNull(field.getCell(new Position(fieldWidth + 1, fieldHeight + 1)));
    }

    @Test
    // пройтись кругом по соседям ячейки и вернутся обратно > должны прийти к той же ячейке
    public void CircleMoveTest(){
        ColoredCell initialCell = (ColoredCell) robot.getCell();

        robot.move(HexagonDirection.LEFT_DOWN);
        robot.move(HexagonDirection.RIGHT);
        initialCell.setCurrentFootprint(ColoredCell.defaultFootprint);
        robot.move(HexagonDirection.LEFT_UP);

        assertEquals(initialCell, robot.getCell());
    }

    @Test
    // заспавнить объект в ячейке > объект в ячейке
    public void SpawnObjectTest(){
        CellObject spawnedObject = new Key();
        Cell cell = field.getCell(new Position(0, 0));

        field.spawnObject(spawnedObject, cell);

        assertNotNull(field.getSpawnedObjects().stream().filter(obj -> obj == spawnedObject).findFirst().orElse(null));
        assertEquals(spawnedObject, cell.getContainedObject());
        assertEquals(cell, spawnedObject.getCell());
    }

    @Test
    // задеспавнить объект в ячейке > объекта нет в ячейке
    public void DespawnObjectTest(){
        CellObject despawnedObject = robot;
        Cell cell = robot.getCell();

        field.despawnObject(despawnedObject);

        assertNull(field.getSpawnedObjects().stream().filter(obj -> obj == despawnedObject).findFirst().orElse(null));
        assertNull(cell.getContainedObject());
        assertNull(despawnedObject.getCell());
    }

    @Test
    // движения робота в ячейку без следа > перемещение совершилось, ячейка со следом
    public void MoveToWithoutFootprintTest(){
        Cell initialCell = robot.getCell();

        robot.move(HexagonDirection.RIGHT);

        assertNull(initialCell.getContainedObject());
        assertEquals(initialCell.getNeighbor(HexagonDirection.RIGHT), robot.getCell());
        assertEquals(robot.getFootprintColor(), ((ColoredCell) robot.getCell()).getCurrentFootprint());
    }

    @Test
    // движение робота в ячейку со следом > движения не произошло
    public void MoveToWithFootprintTest(){
        Cell initialCell = robot.getCell();
        ColoredCell targetCell = (ColoredCell) initialCell.getNeighbor(HexagonDirection.RIGHT);

        targetCell.setCurrentFootprint(robot.getFootprintColor());
        robot.move(HexagonDirection.RIGHT);

        assertEquals(robot, initialCell.getContainedObject());
        assertNull(targetCell.getContainedObject());
    }

    @Test
    // движение робота к выходу, ключей нет > игра выиграна
    public void MoveToExitWithoutKeysTest(){
        List<CellObject> keys = field.getSpawnedObjects().stream().filter(obj -> obj instanceof Key).toList();

        for (CellObject key : keys){
            field.despawnObject(key);
        }

        game.doStep(HexagonDirection.RIGHT);
        game.doStep(HexagonDirection.RIGHT);

        assertTrue(game.isGameOver());
        assertTrue(game.isPlayerWin());
    }

    @Test
    // движение робота к выходу, ключи есть > игра продолжается
    public void MoveToExitWithKeysTest(){
        game.doStep(HexagonDirection.RIGHT);
        game.doStep(HexagonDirection.RIGHT);

        assertFalse(game.isGameOver());
    }

    @Test
    // все ячейки вокруг робота со следом > игра проиграна
    public void AllAroundWithFootprintTest(){
        game.doStep(HexagonDirection.LEFT_DOWN);
        game.doStep(HexagonDirection.RIGHT_DOWN);
        game.doStep(HexagonDirection.RIGHT);
        game.doStep(HexagonDirection.RIGHT_UP);
        game.doStep(HexagonDirection.LEFT_UP);
        game.doStep(HexagonDirection.LEFT_DOWN);

        assertTrue(game.isGameOver());
        assertFalse(game.isPlayerWin());
    }

    @Test
    // нет прохода к выходу > игра проиграна
    public void CantReachExit(){
        game.doStep(HexagonDirection.RIGHT);
        game.doStep(HexagonDirection.RIGHT_DOWN);
        game.doStep(HexagonDirection.RIGHT);
        game.doStep(HexagonDirection.RIGHT_UP);
        game.doStep(HexagonDirection.LEFT_UP);
        game.doStep(HexagonDirection.LEFT);
        game.doStep(HexagonDirection.LEFT);

        assertTrue(game.isGameOver());
        assertFalse(game.isPlayerWin());
    }

    @Test
    // подбор ключа роботом > ключа на карте нет
    public void KeyCollectTest(){
        int initKeysCount = field.getSpawnedObjects().stream().filter(obj -> obj instanceof Key).toList().size();
        robot.move(HexagonDirection.LEFT);
        robot.move(HexagonDirection.LEFT);

        List<CellObject> keys = field.getSpawnedObjects().stream().filter(obj -> obj instanceof Key).toList();
        assertEquals(initKeysCount - 1, keys.size());
        assertEquals(robot, robot.getCell().getContainedObject());
    }
}
