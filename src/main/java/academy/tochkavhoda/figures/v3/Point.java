package academy.tochkavhoda.figures.v3;

import academy.tochkavhoda.iface.v3.Movable;

public class Point implements Movable {
    private int x, y;

    public Point(int x, int y) { this.x = x; this.y = y; }
    public Point() { this(0, 0); }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    @Override
    public void moveTo(int newX, int newY) { x = newX; y = newY; }

    @Override
    public void moveRel(int dx, int dy) { x += dx; y += dy; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}