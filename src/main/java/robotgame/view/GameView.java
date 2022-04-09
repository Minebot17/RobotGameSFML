package robotgame.view;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;
import robotgame.model.Game;
import robotgame.model.fieldgeneration.RandomPositionsFieldFactory;
import robotgame.model.finishgamerule.FinishGameRulesHandler;
import robotgame.model.finishgamerule.FinishGameRulesHandlerFactory;
import robotgame.model.finishgamerule.RuleLinkType;
import robotgame.model.finishgamerule.rules.ExitFinishGameRuleFactory;
import robotgame.model.finishgamerule.rules.KeysFinishGameRuleFactory;
import robotgame.model.pathfinding.NeighborPathFinderFactory;
import robotgame.model.pathfinding.PathFinderFactory;

import java.util.ArrayList;

public class GameView {

    private final PathFinderFactory pathFinder = new NeighborPathFinderFactory();
    private final FinishGameRulesHandlerFactory ruleFactory =
        new FinishGameRulesHandlerFactory(new ArrayList<>() {{
            add(new FinishGameRulesHandler.RuleParameters(new KeysFinishGameRuleFactory(pathFinder), false, RuleLinkType.AND));
            add(new FinishGameRulesHandler.RuleParameters(new ExitFinishGameRuleFactory(pathFinder), false, RuleLinkType.AND));
        }});

    private final Game game;
    private final RenderWindow window;

    public GameView(){
        game = new Game(ruleFactory, new RandomPositionsFieldFactory(8, 8, 3));
        window = new RenderWindow(new VideoMode(800, 600), "SFML Game");
        window.setVerticalSyncEnabled(true);
        window.setFramerateLimit(60);

        Textures.loadAll();
    }

    public void handleWindow(){
        while (window.isOpen()){
            handleInputs(window);
            draw(window);
        }
    }

    private void handleInputs(Window window){
        Iterable<Event> events = window.pollEvents();

        for (Event event : events){

            switch (event.type){

                case CLOSED:
                    window.close();
                    break;

                case KEY_PRESSED:

                    break;
            }
        }
    }

    private void draw(RenderWindow window){
        window.clear(Color.WHITE);

        //window.draw(test);

        window.display();
    }
}
