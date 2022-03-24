package robotgame.view;

import robotgame.model.Game;
import robotgame.model.HexagonDirection;
import robotgame.model.Utils;
import robotgame.model.fieldgeneration.RandomPositionsFieldFactory;
import robotgame.model.finishgamerule.FinishGameRulesHandler;
import robotgame.model.finishgamerule.FinishGameRulesHandlerFactory;
import robotgame.model.finishgamerule.RuleLinkType;
import robotgame.model.finishgamerule.rules.ExitFinishGameRuleFactory;
import robotgame.model.finishgamerule.rules.KeysFinishGameRuleFactory;
import robotgame.model.finishgamerule.rules.RobotStepsFinishGameRule;
import robotgame.model.finishgamerule.rules.RobotStepsFinishGameRuleFactory;
import robotgame.model.pathfinding.NeighborPathFinderFactory;
import robotgame.model.pathfinding.PathFinderFactory;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {

    private final Game game;
    private final PathFinderFactory pathFinder = new NeighborPathFinderFactory();
    private final List<FinishGameRulesHandlerFactory> rulesList = new ArrayList<>(){{
        add(new FinishGameRulesHandlerFactory(new ArrayList<>() {{
            add(new FinishGameRulesHandler.RuleParameters(new KeysFinishGameRuleFactory(pathFinder), false, RuleLinkType.AND));
            add(new FinishGameRulesHandler.RuleParameters(new ExitFinishGameRuleFactory(pathFinder), false, RuleLinkType.AND));
        }}));
        add(new FinishGameRulesHandlerFactory(new ArrayList<>() {{
            add(new FinishGameRulesHandler.RuleParameters(new KeysFinishGameRuleFactory(pathFinder), true, RuleLinkType.AND));
            add(new FinishGameRulesHandler.RuleParameters(new ExitFinishGameRuleFactory(pathFinder), false, RuleLinkType.AND));
        }}));
        add(new FinishGameRulesHandlerFactory(new ArrayList<>() {{
            add(new FinishGameRulesHandler.RuleParameters(new KeysFinishGameRuleFactory(pathFinder), false, RuleLinkType.OR));
            add(new FinishGameRulesHandler.RuleParameters(new ExitFinishGameRuleFactory(pathFinder), false, RuleLinkType.OR));
        }}));
        add(new FinishGameRulesHandlerFactory(new ArrayList<>() {{
            add(new FinishGameRulesHandler.RuleParameters(new RobotStepsFinishGameRuleFactory(RobotStepsFinishGameRule.RuleMode.MORE, 10), false, RuleLinkType.AND));
            add(new FinishGameRulesHandler.RuleParameters(new ExitFinishGameRuleFactory(pathFinder), false, RuleLinkType.AND));
        }}));
    }};

    public GameFrame() {
        FinishGameRulesHandlerFactory selectedHandlerFactory = rulesList.get(Utils.rnd.nextInt(rulesList.size()));
        JOptionPane.showMessageDialog(null, selectedHandlerFactory.toString());
        game = new Game(selectedHandlerFactory, new RandomPositionsFieldFactory(8, 8, 3));
        HexagonFieldView hexagonFieldView = new HexagonFieldView(game.getField());

        setContentPane(hexagonFieldView);
        setSize(hexagonFieldView.getSize());
        setResizable(false);
        setFocusable(true);

        addKeyListener(new KeyController());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class KeyController implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();

            if(code == KeyEvent.VK_Q) {
                game.doStep(HexagonDirection.LEFT_UP);
            }
            else if(code == KeyEvent.VK_W) {
                game.doStep(HexagonDirection.RIGHT_UP);
            }
            else if(code == KeyEvent.VK_A) {
                game.doStep(HexagonDirection.LEFT);
            }
            else if(code == KeyEvent.VK_S) {
                game.doStep(HexagonDirection.RIGHT);
            }
            else if(code == KeyEvent.VK_Z) {
                game.doStep(HexagonDirection.LEFT_DOWN);
            }
            else if(code == KeyEvent.VK_X) {
                game.doStep(HexagonDirection.RIGHT_DOWN);
            }

            repaint();

            if (game.isGameOver()){
                JOptionPane.showMessageDialog(null, game.isPlayerWin() ? "Player is WIN" : "Player is LOSE");
                System.exit(0);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
