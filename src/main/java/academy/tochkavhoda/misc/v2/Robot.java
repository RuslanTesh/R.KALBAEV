package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.figures.v2.Point;
import academy.tochkavhoda.iface.v2.Colored;
import academy.tochkavhoda.iface.v2.Movable;
import java.util.Objects;

public class Robot implements Movable, Colored {
    private String name;
    private Point position;
    private int color;

    public Robot(String name, Point position, int color) {
        this.name = name; this.position = position; this.color = color;
    }
    public Robot() { this("Robot", new Point(0, 0), 1); }

    public String getName() { return name; }
    public Point getPosition() { return position; }
    public void setName(String name) { this.name = name; }
    public void setPosition(Point position) { this.position = position; }

    @Override public int getColor() { return color; }
    @Override public void setColor(int color) { this.color = color; }
    @Override public void moveTo(int x, int y) { this.position = new Point(x, y); }
    @Override public void moveRel(int dx, int dy) { this.position = new Point(position.getX() + dx, position.getY() + dy); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot r = (Robot) o;
        return color == r.color && Objects.equals(name, r.name) && Objects.equals(position, r.position);
    }

    @Override
    public int hashCode() { return Objects.hash(name, position, color); }
}