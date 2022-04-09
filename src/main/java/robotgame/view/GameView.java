package robotgame.view;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;
import robotgame.model.Game;
import robotgame.model.HexagonDirection;
import robotgame.model.HexagonField;
import robotgame.model.Position;
import robotgame.model.cell.Cell;
import robotgame.model.cell.ColoredCell;
import robotgame.model.cell.ExitCell;
import robotgame.model.cellobject.CellObject;
import robotgame.model.cellobject.Robot;
import robotgame.model.fieldgeneration.RandomPositionsFieldFactory;
import robotgame.model.finishgamerule.FinishGameRulesHandler;
import robotgame.model.finishgamerule.FinishGameRulesHandlerFactory;
import robotgame.model.finishgamerule.RuleLinkType;
import robotgame.model.finishgamerule.rules.ExitFinishGameRuleFactory;
import robotgame.model.finishgamerule.rules.KeysFinishGameRuleFactory;
import robotgame.model.pathfinding.NeighborPathFinderFactory;
import robotgame.model.pathfinding.PathFinderFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameView {

    private final PathFinderFactory pathFinder = new NeighborPathFinderFactory();
    private final FinishGameRulesHandlerFactory ruleFactory =
        new FinishGameRulesHandlerFactory(new ArrayList<>() {{
            add(new FinishGameRulesHandler.RuleParameters(new KeysFinishGameRuleFactory(pathFinder), false, RuleLinkType.AND));
            add(new FinishGameRulesHandler.RuleParameters(new ExitFinishGameRuleFactory(pathFinder), false, RuleLinkType.AND));
        }});

    private final Game game;
    private final RenderWindow window;

    private final List<Sprite> gameFieldSprites = new ArrayList<>();
    private final Position margins = new Position(10, 10);
    private static final int CELL_SIZE = 170;
    private static final float CELL_WIDTH_MOD = 0.866025f;

    private final List<Sprite> cellObjectSprites = new ArrayList<>();

    public GameView(){
        game = new Game(ruleFactory, new RandomPositionsFieldFactory(8, 8, 3));
        HexagonField field = game.getField();
        window = new RenderWindow(new VideoMode(
                (int)(margins.x * 2 + CELL_SIZE * (field.getWidth() + 1f) * CELL_WIDTH_MOD),
                (int)(margins.y * 2 + CELL_SIZE * (field.getHeight() + 1) * 0.75f))
                , "SFML Game");
        window.setVerticalSyncEnabled(true);
        window.setFramerateLimit(60);

        Textures.loadAll();
        updateCells();
        updateObjects();
    }

    public void handleWindow(){
        while (window.isOpen()){
            handleInputs(window);
            draw(window);
        }
    }

    private void handleInputs(RenderWindow window){
        Iterable<Event> events = window.pollEvents();

        for (Event event : events){

            switch (event.type){

                case CLOSED:
                    window.close();
                    break;

                case KEY_PRESSED:
                    switch (event.asKeyEvent().key){
                        case Q:
                            game.doStep(HexagonDirection.LEFT_UP);
                            break;
                        case W:
                            game.doStep(HexagonDirection.RIGHT_UP);
                            break;
                        case A:
                            game.doStep(HexagonDirection.LEFT);
                            break;
                        case S:
                            game.doStep(HexagonDirection.RIGHT);
                            break;
                        case Z:
                            game.doStep(HexagonDirection.LEFT_DOWN);
                            break;
                        case X:
                            game.doStep(HexagonDirection.RIGHT_DOWN);
                            break;
                    }

                    updateCells();
                    updateObjects();
                    if (game.isGameOver()) {
                        draw(window);
                        JOptionPane.showMessageDialog(null, game.isPlayerWin() ? "Player is WIN" : "Player is LOSE");
                    }
                    break;
            }
        }
    }

    private void draw(RenderWindow window){
        window.clear(Color.WHITE);

        for (Sprite cellSprite : gameFieldSprites){
            window.draw(cellSprite);
        }

        for (Sprite objectSprite : cellObjectSprites){
            window.draw(objectSprite);
        }

        window.display();
    }

    private void updateCells(){
        HexagonField field = game.getField();
        gameFieldSprites.clear();

        Sprite exitCellSprite = null;
        for (int x = 0; x < field.getWidth(); x++){
            for (int y = 0; y < field.getHeight(); y++){
                int modelY = field.getHeight() - y - 1;
                boolean withOffset = modelY % 2 == 0;

                Cell cell = field.getCell(new Position(x, modelY));
                Sprite cellSprite = new Sprite(cell instanceof ExitCell
                        ? Textures.exitCell
                        : cell instanceof ColoredCell coloredCell
                        ? coloredCell.getCurrentFootprint().equals(ColoredCell.defaultFootprint)
                        ? Textures.cell
                        : Textures.destroyedCell
                        : null);

                cellSprite.setPosition(margins.x + CELL_SIZE * CELL_WIDTH_MOD * (x + (withOffset ? 0.5f : 0)), margins.y + CELL_SIZE * y * 0.75f);

                if (cell instanceof ExitCell){
                    exitCellSprite = cellSprite;
                }
                else {
                    gameFieldSprites.add(cellSprite);
                }
            }
        }

        gameFieldSprites.add(exitCellSprite);
    }

    private void updateObjects(){
        cellObjectSprites.clear();

        for (CellObject cellObject : game.getField().getSpawnedObjects()){
            if (cellObject == null){
                continue;
            }

            Sprite cellObjectSprite = new Sprite(cellObject instanceof Robot ? Textures.robot : Textures.key);

            int modelY = game.getField().getHeight() - cellObject.getCell().getPosition().y - 1;
            boolean withOffset = modelY % 2 == 1;
            cellObjectSprite.setPosition(margins.x + CELL_SIZE * CELL_WIDTH_MOD * (cellObject.getCell().getPosition().x + (withOffset ? 0.5f : 0)), margins.y + CELL_SIZE * modelY * 0.75f);
            cellObjectSprites.add(cellObjectSprite);
        }
    }
}
