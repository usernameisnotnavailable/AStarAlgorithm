import javax.swing.*;
import java.util.*;

public class Main {


    public static void main(String[] args) {

        NoObstacleMap noObMap = new NoObstacleMap();
        ObstacleMap oBMap = new ObstacleMap();
        MazeSolver mazeSolver = new MazeSolver(oBMap);
        setUp(mazeSolver);
        mazeSolver.solveMaze();

    }

    public static void setUp(MazeSolver maze){
        JFrame frame = new JFrame("Simple JPanel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 900);
        frame.setLocationRelativeTo(null); // Center the window
        Board board = new Board(maze);
        maze.addBoard(board);
        frame.add(board);
        frame.setVisible(true);
    }

}
