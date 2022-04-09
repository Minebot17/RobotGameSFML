package robotgame.view;

import org.jsfml.graphics.Texture;

import java.io.IOException;
import java.nio.file.Paths;

public class Textures {

    public static Texture cell = new Texture();
    public static Texture destroyedCell = new Texture();
    public static Texture exitCell = new Texture();
    public static Texture key = new Texture();
    public static Texture robot = new Texture();

    public static void loadAll(){
        try {
            cell.loadFromFile(Paths.get("textures/cell.png"));
            destroyedCell.loadFromFile(Paths.get("textures/destroyed_cell.png"));
            exitCell.loadFromFile(Paths.get("textures/exit_cell.png"));
            key.loadFromFile(Paths.get("textures/key.png"));
            robot.loadFromFile(Paths.get("textures/robot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
