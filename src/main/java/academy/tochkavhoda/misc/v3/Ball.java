package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.figures.v3.Point;
import academy.tochkavhoda.iface.v3.Colored;
import academy.tochkavhoda.iface.v3.Movable;
import academy.tochkavhoda.iface.v3.Resizable;

import java.util.Objects;

public class Ball implements Movable, Resizable, Colored {
    private Point center;
    private int radius;
    private Color color;

    public Ball(Point center, int radius, Color color) throws ColorException {
        if (color == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public Ball(Point center, int radius, String colorString) throws ColorException {
        this.center = center;
        this.radius = radius;
        this.color = Color.colorFromString(colorString);
    }

    public Ball() {
        this.center = new Point(0, 0);
        this.radius = 1;
        this.color = Color.RED;
    }

    public Point getCenter() { return center; }
    public int getRadius() { return radius; }
    public void setCenter(Point center) { this.center = center; }
    public void setRadius(int radius) { this.radius = radius; }

    @Override public Color getColor() { return color; }
    @Override
    public void setColor(Color color) throws ColorException {
        if (color == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = color;
    }    @Override public void setColor(String colorString) throws ColorException {
        this.color = Color.colorFromString(colorString);
    }
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