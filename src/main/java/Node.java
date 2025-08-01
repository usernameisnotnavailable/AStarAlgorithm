import java.util.Objects;

public class Node implements Comparable{
    int posX;
    int posY;
    int g;
    int h;
    int f;
    Node parent;

    public Node() {

    }

    public Node(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void calculateAndSetG(int startX, int startY) {
        g = calculateDistance(startX, startY);
        calculateAndSetF();
    }
    public void calculateAndSetH(int targetX, int targetY) {
        h = calculateDistance(targetX, targetY);
        calculateAndSetF();
    }

    public void calculateAndSetF(){
        f = g + h;
    }

    private int calculateDistance(int targetX, int targetY) {
        return (Math.abs(posX - targetX) + Math.abs(posY - targetY));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return posX == node.posX && posY == node.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || getClass() != o.getClass()) return -1;
        Node node = (Node) o;
        if (this.f == node.f) {
            return Integer.compare(this.h, node.h);
        }

        if (this.f < node.f) {
            return -1;
        } else {
            return 1;
        }

    }
}
