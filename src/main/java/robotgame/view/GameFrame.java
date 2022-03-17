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

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {

    private final Game game;
    private final List<FinishGameRulesHandlerFactory> rulesList = new ArrayList<>(){{
        add(new FinishGameRulesHandlerFactory(new ArrayList<>() {{
            add(new FinishGameRulesHandler.RuleParameters(new KeysFinishGameRuleFactory(), false, RuleLinkType.AND));
            add(new FinishGameRulesHandler.RuleParameters(new ExitFinishGameRuleFactory(), false, RuleLinkType.AND));
        }}));
        add(new FinishGameRulesHandlerFactory(new ArrayList<>() {{
            add(new FinishGameRulesHandler.RuleParameters(new KeysFinishGameRuleFactory(), true, RuleLinkType.AND));
            add(new FinishGameRulesHandler.RuleParameters(new ExitFinishGameRuleFactory(), false, RuleLinkType.AND));
        }}));
        add(new FinishGameRulesHandlerFactory(new ArrayList<>() {{
            add(new FinishGameRulesHandler.RuleParameters(new KeysFinishGameRuleFactory(), false, RuleLinkType.OR));
            add(new FinishGameRulesHandler.RuleParameters(new ExitFinishGameRuleFactory(), false, RuleLinkType.OR));
        }}));
        add(new FinishGameRulesHandlerFactory(new ArrayList<>() {{
            add(new FinishGameRulesHandler.RuleParameters(new RobotStepsFinishGameRuleFactory(RobotStepsFinishGameRule.RuleMode.MORE, 10), false, RuleLinkType.AND));
            add(new FinishGameRulesHandler.RuleParameters(new ExitFinishGameRuleFactory(), false, RuleLinkType.AND));
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
                game.moveRobot(HexagonDirection.LEFT_UP);
            }
            else if(code == KeyEvent.VK_W) {
                game.moveRobot(HexagonDirection.RIGHT_UP);
            }
            else if(code == KeyEvent.VK_A) {
                game.moveRobot(HexagonDirection.LEFT);
            }
            else if(code == KeyEvent.VK_S) {
                game.moveRobot(HexagonDirection.RIGHT);
            }
            else if(code == KeyEvent.VK_Z) {
                game.moveRobot(HexagonDirection.LEFT_DOWN);
            }
            else if(code == KeyEvent.VK_X) {
                game.moveRobot(HexagonDirection.RIGHT_DOWN);
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
