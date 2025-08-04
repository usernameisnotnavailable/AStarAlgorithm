import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private MazeSolver maze;
    private int size;
    private int CELL_SIZE = 25;
    private int PATH = 0;
    private int WALL = 1;

    public Board(MazeSolver mazeSolver) {
        this.maze = mazeSolver;
        size = mazeSolver.map.size;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintMap(g);

/*        g.setColor(Color.RED);
        maze.explored.forEach(node -> {
            g.fillRect(node.posX * CELL_SIZE, node.posY * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        });*/

        maze.open.forEach(node -> {
            g.setColor(Color.GREEN);
            g.fillRect(node.posX * CELL_SIZE, node.posY * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Serif", Font.BOLD, 20));
            g.drawString(String.valueOf(node.f), node.posX * CELL_SIZE, node.posY * CELL_SIZE + CELL_SIZE);
        });
        maze.explored.forEach(node -> {
            g.setColor(Color.GREEN);
            g.fillRect(node.posX * CELL_SIZE, node.posY * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Serif", Font.BOLD, 20));
            g.drawString(String.valueOf(node.f), node.posX * CELL_SIZE, node.posY * CELL_SIZE + CELL_SIZE);
                });
        g.setColor(Color.BLUE);
        g.fillRect(maze.targetX * CELL_SIZE, maze.targetY * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        g.fillRect(maze.map.startX * CELL_SIZE, maze.map.startY * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        g.setColor(Color.CYAN);
        maze.path.forEach(node -> {
            g.fillRect(node.posX * CELL_SIZE, node.posY * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        });

    }

    private void paintMap(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (maze.map.map[i][j] == PATH) {
                    g.drawRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else if (maze.map.map[i][j] == WALL) {
                    g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }

            }
        }
    }





}
