package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.figures.v2.Point;
import academy.tochkavhoda.iface.v2.Colored;
import academy.tochkavhoda.iface.v2.Movable;
import academy.tochkavhoda.iface.v2.Resizable;
import java.util.Objects;

public class Ball implements Movable, Resizable, Colored {
    private Point center;
    private int radius;
    private int color;

    public Ball(Point center, int radius, int color) {
        this.center = center; this.radius = radius; this.color = color;
    }
    public Ball() { this(new Point(0, 0), 1, 1); }

    public Point getCenter() { return center; }
    public int getRadius() { return radius; }
    public void setCenter(Point center) { this.center = center; }
    public void setRadius(int radius) { this.radius = radius; }

    @Override public int getColor() { return color; }
    @Override public void setColor(int color) { this.color = color; }
    @Override public void moveTo(int x, int y) { this.center = new Point(x, y); }
    @Override public void moveRel(int dx, int dy) { this.center = new Point(center.getX() + dx, center.getY() + dy); }
    @Override public void resize(double ratio) { this.radius = (int)(radius * ratio); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball b = (Ball) o;
        return radius == b.radius && color == b.color && Objects.equals(center, b.center);
    }

    @Override
    public int hashCode() { return Objects.hash(center, radius, color); }
}