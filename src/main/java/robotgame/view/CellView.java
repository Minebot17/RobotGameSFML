package robotgame.view;

import robotgame.model.cell.Cell;
import robotgame.model.cell.ColoredCell;
import robotgame.model.cell.ExitCell;
import robotgame.model.cellobject.CellObject;
import robotgame.model.cellobject.Key;
import robotgame.model.cellobject.Robot;

import javax.swing.*;
import java.awt.*;

public class CellView extends JPanel {

    public static final int CELL_SIZE = 65;
    public static final float CELL_WIDTH_MOD = 0.866025f;

    private static final String ROBOT_LETTER = "R";
    private static final String EXIT_LETTER = "ET";
    private static final String KEY_LETTER = "K";

    private static final float[] xHexagonPoints = new float[]{
            0f, CELL_WIDTH_MOD, CELL_WIDTH_MOD, 0f, -CELL_WIDTH_MOD, -CELL_WIDTH_MOD, 0f
    };
    private static final float[] yHexagonPoints = new float[]{
            1f, 0.5f, -0.5f, -1f, -0.5f, 0.5f, 1f
    };

    private final Cell cell;

    public CellView(Cell cell) {
        this.cell = cell;

        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        setBackground(new Color(0, 0, 0, 0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D gr2d = (Graphics2D) g;
        drawHexagon(gr2d);

        CellObject cellObject = cell.getContainedObject();
        if (cellObject != null){
            drawString(gr2d, cellObject instanceof Robot ? ROBOT_LETTER : cellObject instanceof Key ? KEY_LETTER : "");
        }
        else if (cell instanceof ExitCell){
            drawString(gr2d, EXIT_LETTER);
        }
    }

    private void drawHexagon(Graphics2D gr2d){
        gr2d.setPaint(getCellColor());
        gr2d.fillPolygon(
                getPolygonPoints(true, CELL_SIZE/2f),
                getPolygonPoints(false, CELL_SIZE/2f),
                xHexagonPoints.length
        );

        gr2d.setStroke(new BasicStroke(2));
        gr2d.setPaint(Color.black);
        gr2d.drawPolygon(
                getPolygonPoints(true, CELL_SIZE/2f),
                getPolygonPoints(false, CELL_SIZE/2f),
                xHexagonPoints.length
        );
    }

    private void drawString(Graphics2D gr2d, String letter){
        gr2d.setColor(Color.black);
        gr2d.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 20));

        FontMetrics fm = gr2d.getFontMetrics();
        int msgWidth = fm.stringWidth(letter);
        int msgHeight = fm.getHeight();

        gr2d.drawString(letter, (CELL_SIZE - msgWidth)/2, CELL_SIZE / 2 + msgHeight/4);
    }

    private int[] getPolygonPoints(boolean isX, float scale){
        float[] points = isX ? xHexagonPoints : yHexagonPoints;
        int[] result = new int[points.length];

        for (int i = 0; i < points.length; i++){
            result[i] = Math.round((points[i] + 1f) * scale);
        }

        return result;
    }

    private Color getCellColor(){
        return cell instanceof ColoredCell ? ((ColoredCell)cell).getCurrentColor() : Color.white;
    }
}
