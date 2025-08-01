import java.util.*;

public class MazeSolver {

    final Map map;
    int targetY;
    int targetX;
    private int startX;
    private int startY;
    Queue<Node> open;
    Set<Node> explored;
    private static int PATH_CLEAR = 0;
    private static int THREAD_SLEEP_TIME = 10;
    Board board;

    public MazeSolver(Map map) {
        this.map = map;
        targetX = map.endX;
        targetY = map.endY;
        startX = map.startX;
        startY = map.startY;
        open = new PriorityQueue<>();
        explored = new HashSet<>();
        System.out.printf("Starting position: %d x, %d y. Target Position: %d, x, %d y.", startX, startY, targetX, targetY);
    }


    public void solveMaze() {
        // set staring position Node
        Node startNode = new Node(map.startX, map.startY);
        // get distance from start
        startNode.calculateAndSetG(startX, startY);
        // get distance from end
        startNode.calculateAndSetH(targetX, targetY);

        open.add(startNode);

        int currentX = Integer.MAX_VALUE, currentY = Integer.MAX_VALUE;
        while (currentX != targetX || currentY != targetY) {
            Node current = open.poll();
            currentX = current.posX;
            currentY = current.posY;
            System.out.printf("PosX: %d, PosY: %d, F(sum): %d, G(dist from start): %d, H(dist from target): %s \n", currentX, currentY, current.f, current.g, current.h);
            exploreNeighbours(currentX, currentY);

        }
    }

    private void exploreNeighbours(int currentX, int currentY) {
        int[] xMod = {-1, 0, 1, 0}; // left top right down
        int[] yMod = {0, -1, 0, 1};
        int[][] mapRef = map.getMap();
        for (int i = 0; i < 4; i++) {
            // neighbour position
            int x = currentX + xMod[i];
            int y = currentY + yMod[i];
            // validate position not out of map ->  it is skip this iteration
            if (x < 0 || x >= map.size || y < 0 || y >= map.size || y == currentY && x == currentX) continue;

            // if path is clear
            if (mapRef[x][y] == PATH_CLEAR) {
                Node node = new Node(x, y);
                if (!explored.contains(node)) {
                    // create node and add to open list
                    node.calculateAndSetG(startX, startY);
                    node.calculateAndSetH(targetX, targetY);
                    open.add(node);
                    explored.add(node);
                    try {
                        board.repaint();
                        Thread.sleep(THREAD_SLEEP_TIME);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void addBoard(Board board) {
        this.board = board;
    }
}
