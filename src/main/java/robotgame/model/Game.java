package robotgame.model;

import robotgame.model.finishgamerule.FinishGameRule;

public class Game {

    private HexagonField field;
    private FinishGameRule finishGameRule;

    public Game(FinishGameRule finishGameRule){
        this.finishGameRule = finishGameRule;

        field = new HexagonField(10, 10);
    }

    public void MoveRobot(){
        if (finishGameRule.isGameOver()){
            return;
        }

        // TODO
    }

    public boolean isGameOver(){
        return finishGameRule.isGameOver();
    }

    public boolean isPlayerWin(){
        return finishGameRule.isPlayerWin();
    }
}
