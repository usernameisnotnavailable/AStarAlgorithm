public class ObstacleMap extends Map{

    @Override
    public int[][] createMap() {
        super.map = super.createEmptyMap();

        int halfX = (super.startX + super.targetX) / 2;
        int halfY = (super.startY + super.targetY) / 2;
        int size = super.size;



            for (int j = 3; j < size -3; j++) {
                int potentialX = j;
                int potentialY = halfY;
                if(potentialX < 0 || potentialX >= size || potentialY < 0 || potentialY >= size || potentialX == super.startX && potentialY == super.startY || potentialX == super.startX && potentialY == super.targetY) continue;
                super.map[potentialX][potentialY] = 1;
            }

        for (int j = 3; j < size -3; j++) {
            int potentialX = halfX;
            int potentialY = j;
            if(potentialX < 0 || potentialX >= size || potentialY < 0 || potentialY >= size || potentialX == super.startX && potentialY == super.startY || potentialX == super.targetX && potentialY == super.targetY) continue;
            super.map[potentialX][potentialY] = 1;
        }







        return super.map;

    }
}
