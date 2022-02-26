package robotgame.view;

import robotgame.model.HexagonField;
import robotgame.model.cell.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HexagonFieldView extends JPanel {

    private final Point margins = new Point(10, 10);

    public HexagonFieldView(HexagonField field) {
        setLayout(null);
        setSize(
                (int)(margins.x * 2 + CellView.CELL_SIZE * (field.getWidth() + 1f) * CellView.CELL_WIDTH_MOD),
                (int)(margins.y * 2 + CellView.CELL_SIZE * (field.getHeight() + 1) * 0.75f)
        );

        for (int x = 0; x < field.getWidth(); x++){
            for (int y = 0; y < field.getHeight(); y++){
                int modelY = field.getHeight() - y - 1;
                boolean withOffset = modelY % 2 == 0;

                CellView cellView = new CellView(field.getCell(new Point(x, modelY)));
                cellView.setBounds(
                        (int)(margins.x + CellView.CELL_SIZE * CellView.CELL_WIDTH_MOD * (x + (withOffset ? 0.5f : 0))),
                        (int)(margins.y + CellView.CELL_SIZE * y * 0.75f),
                        CellView.CELL_SIZE, CellView.CELL_SIZE
                );
                add(cellView);
            }
        }

        setFocusable(true);
    }
}
