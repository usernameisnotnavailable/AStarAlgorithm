import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private MazeSolver maze;
    private int size;
    private int CELL_SIZE = 20;

    private int PATH = 0;
    private int WALL = 1;

    public Board(MazeSolver mazeSolver) {
        this.maze = mazeSolver;
        size = mazeSolver.map.size;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j ++) {
                if (maze.map.map[i][j] == PATH) {
                    g.drawRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else if (maze.map.map[i][j] == WALL) {
                    g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }

            }
        }
        g.fillRect(maze.targetX * CELL_SIZE, maze.targetY * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        maze.open.forEach(node -> {
            g.setColor(Color.green);
            g.fillRect(node.posX * CELL_SIZE, node.posY * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(node.f),node.posX * CELL_SIZE, node.posY * CELL_SIZE);
        });


    }


}
