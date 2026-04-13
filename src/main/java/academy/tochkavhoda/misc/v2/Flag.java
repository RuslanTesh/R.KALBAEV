package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.figures.v2.Point;
import academy.tochkavhoda.iface.v2.Colored;
import academy.tochkavhoda.iface.v2.Movable;
import academy.tochkavhoda.iface.v2.Stretchable;
import java.util.Objects;

public class Flag implements Movable, Stretchable, Colored {
    private Point topLeft;
    private int width;
    private int height;
    private int color;

    public Flag(Point topLeft, int width, int height, int color) {
        this.topLeft = topLeft; this.width = width; this.height = height; this.color = color;
    }
    public Flag() { this(new Point(0, 0), 2, 1, 1); }

    public Point getTopLeft() { return topLeft; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public void setTopLeft(Point topLeft) { this.topLeft = topLeft; }

    @Override public int getColor() { return color; }
    @Override public void setColor(int color) { this.color = color; }
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