package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.figures.v3.Point;
import academy.tochkavhoda.iface.v3.Colored;
import academy.tochkavhoda.iface.v3.Movable;

import java.util.Objects;

public class Robot implements Movable, Colored {
    private String name;
    private Point position;
    private Color color;

    public Robot(String name, Point position, Color color) throws ColorException {
        if (color == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.name = name;
        this.position = position;
        this.color = color;
    }

    public Robot(String name, Point position, String colorString) throws ColorException {
        this.name = name;
        this.position = position;
        this.color = Color.colorFromString(colorString);
    }

    public Robot() {
        this.name = "Robot";
        this.position = new Point(0, 0);
        this.color = Color.RED;
    }

    public String getName() { return name; }
    public Point getPosition() { return position; }
    public void setName(String name) { this.name = name; }
    public void setPosition(Point position) { this.position = position; }

    @Override public Color getColor() { return color; }
    @Override
    public void setColor(Color color) throws ColorException {
        if (color == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = color;
    }    @Override public void setColor(String colorString) throws ColorException {
        this.color = Color.colorFromString(colorString);
    }
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