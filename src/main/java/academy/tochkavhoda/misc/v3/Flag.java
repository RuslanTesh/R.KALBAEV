package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.figures.v3.Point;
import academy.tochkavhoda.iface.v3.Colored;
import academy.tochkavhoda.iface.v3.Movable;
import academy.tochkavhoda.iface.v3.Stretchable;

import java.util.Objects;

public class Flag implements Movable, Stretchable, Colored {
    private Point topLeft;
    private int width;
    private int height;
    private Color color;

    public Flag(Point topLeft, int width, int height, Color color) throws ColorException {
        if (color == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Flag(Point topLeft, int width, int height, String colorString) throws ColorException {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = Color.colorFromString(colorString);
    }

    public Flag() {
        this.topLeft = new Point(0, 0);
        this.width = 2;
        this.height = 1;
        this.color = Color.RED;
    }

    public Point getTopLeft() { return topLeft; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public void setTopLeft(Point topLeft) { this.topLeft = topLeft; }

    @Override public Color getColor() { return color; }
    @Override
    public void setColor(Color color) throws ColorException {
        if (color == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = color;
    }    @Override public void setColor(String colorString) throws ColorException {
        this.color = Color.colorFromString(colorString);
    }
    @Override public void moveTo(int x, int y) { this.topLeft = new Point(x, y); }
    @Override public void moveRel(int dx, int dy) { this.topLeft = new Point(topLeft.getX() + dx, topLeft.getY() + dy); }
    @Override public void resize(double ratio) { this.width = (int)(width * ratio); this.height = (int)(height * ratio); }
    @Override public void stretch(double xRatio, double yRatio) { this.width = (int)(width * xRatio); this.height = (int)(height * yRatio); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flag f = (Flag) o;
        return width == f.width && height == f.height && color == f.color && Objects.equals(topLeft, f.topLeft);
    }

    @Override
    public int hashCode() { return Objects.hash(topLeft, width, height, color); }
}