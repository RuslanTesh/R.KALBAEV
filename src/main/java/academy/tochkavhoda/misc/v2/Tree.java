package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.figures.v2.Point;
import academy.tochkavhoda.iface.v2.Movable;
import academy.tochkavhoda.iface.v2.Resizable;
import java.util.Objects;

public class Tree implements Movable, Resizable {
    private Point base;
    private int height;

    public Tree(Point base, int height) { this.base = base; this.height = height; }
    public Tree() { this(new Point(0, 0), 1); }

    public Point getBase() { return base; }
    public int getHeight() { return height; }
    public void setBase(Point base) { this.base = base; }
    public void setHeight(int height) { this.height = height; }

    @Override public void moveTo(int x, int y) { this.base = new Point(x, y); }
    @Override public void moveRel(int dx, int dy) { this.base = new Point(base.getX() + dx, base.getY() + dy); }
    @Override public void resize(double ratio) { this.height = (int)(height * ratio); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree t = (Tree) o;
        return height == t.height && Objects.equals(base, t.base);
    }

    @Override
    public int hashCode() { return Objects.hash(base, height); }
}