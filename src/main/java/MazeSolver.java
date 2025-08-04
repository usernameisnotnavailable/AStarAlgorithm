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
    private static int THREAD_SLEEP_TIME = 100;
    Board board;
    Stack<Node> path;

    public MazeSolver(Map map) {
        this.map = map;
        targetX = map.targetX;
        targetY = map.targetY;
        startX = map.startX;
        startY = map.startY;
        open = new PriorityQueue<>();
        explored = new HashSet<>();
        path = new Stack<>();
        System.out.printf("Starting position: %d x, %d y. Target Position: %d, x, %d y.", startX, startY, targetX, targetY);
    }


    public void solveMaze() {
        // set staring position Node
        Node startNode = new Node(map.startX, map.startY);
        // get distance from start
        startNode.calculateAndSetG(startX, startY);
        // get distance from end
        startNode.calculateAndSetH(targetX, targetY);
        Node current = null;
        open.add(startNode);
        path.push(startNode);
        int currentX = Integer.MAX_VALUE, currentY = Integer.MAX_VALUE;
        while (currentX != targetX || currentY != targetY) {
            current = open.poll();
            currentX = current.posX;
            currentY = current.posY;

            if(path.peek().f > current.f) {
                while (path.peek().f > current.f) {
                    path.pop();
                }
                path.push(current);
            }

            System.out.printf("PosX: %d, PosY: %d, F(sum): %d, G(dist from start): %d, H(dist from target): %s \n", currentX, currentY, current.f, current.g, current.h);
            exploreNeighbours(current);

        }
        while(current.parent != null) {
            path.push(current);
            current = current.parent;
        }
        board.repaint();

    }

    private void exploreNeighbours(Node current) {
        int[] xMod = {-1, 0, 1, 0}; // left top right down
        int[] yMod = {0, -1, 0, 1};
        int[][] mapRef = map.getMap();
        for (int i = 0; i < 4; i++) {
            // neighbour position
            int x = current.posX + xMod[i];
            int y = current.posY + yMod[i];
            // validate position not out of map ->  it is skip this iteration
            if (x < 0 || x >= map.size || y < 0 || y >= map.size || y == current.posY && x == current.posX) continue;

            // if path is clear
            if (mapRef[x][y] == PATH_CLEAR) {
                Node node = new Node(x, y);
                if (!explored.contains(node)) {
                    // create node and add to open list
                    node.calculateAndSetG(startX, startY);
                    node.calculateAndSetH(targetX, targetY);
                    node.parent = current;
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
