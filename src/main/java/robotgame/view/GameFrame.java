package robotgame.view;

import robotgame.model.Game;
import robotgame.model.HexagonDirection;
import robotgame.model.finishgamerule.ExitWithKeysFinishGameRuleFactory;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {

    private final Game game;

    public GameFrame() {
        game = new Game(new ExitWithKeysFinishGameRuleFactory(), 8, 8, 3);
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
