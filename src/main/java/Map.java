import java.util.Random;

public abstract class Map {
    int[][] map;
    int startX;
    int startY;
    int targetX;
    int targetY;
    int size;

    int MIN_SIZE = 20;
    int MAX_SIZE = 40;

    public Map(){
        createMap();
    }

    public int[][] getMap() {
        return map;
    }

    public abstract int[][] createMap();

    protected int[][] createEmptyMap() {
        Random random = new Random();
        size = random.nextInt(MIN_SIZE, MAX_SIZE);
        map = new int[size][size];
        startX = random.nextInt(0, size);
        startY = random.nextInt(0, size);
        targetX = random.nextInt(0, size);
        targetY = random.nextInt(0, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = 0;
            }
        }
        return map;

    }
}
