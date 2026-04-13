package academy.tochkavhoda.figures.v2;

import academy.tochkavhoda.iface.v2.Stretchable;

public class Rectangle extends Figure implements Stretchable {
    private Point topLeft;
    private Point bottomRight;

    public Rectangle(Point leftTop, Point rightBottom) {
        this.topLeft = leftTop;
        this.bottomRight = rightBottom;
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {
        this.topLeft = new Point(xLeft, yTop);
        this.bottomRight = new Point(xRight, yBottom);
    }

    public Rectangle(int length, int width) {
        this.topLeft = new Point(0, -width);
        this.bottomRight = new Point(length, 0);
    }

    public Rectangle() {
        this.topLeft = new Point(0, -1);
        this.bottomRight = new Point(1, 0);
    }

    public Point getTopLeft() { return topLeft; }
    public Point getBottomRight() { return bottomRight; }
    public void setTopLeft(Point topLeft) { this.topLeft = topLeft; }
    public void setBottomRight(Point bottomRight) { this.bottomRight = bottomRight; }

    public int getLength() { return bottomRight.getX() - topLeft.getX(); }
    public int getWidth() { return bottomRight.getY() - topLeft.getY(); }

    @Override
    public void moveTo(int x, int y) {
        int length = getLength();
        int width = getWidth();
        this.topLeft = new Point(x, y);
        this.bottomRight = new Point(x + length, y + width);
    }

    @Override
    public void moveRel(int dx, int dy) {
        this.topLeft = new Point(topLeft.getX() + dx, topLeft.getY() + dy);
        this.bottomRight = new Point(bottomRight.getX() + dx, bottomRight.getY() + dy);
    }

    @Override
    public void resize(double ratio) {
        this.bottomRight = new Point(
                topLeft.getX() + (int)(getLength() * ratio),
                topLeft.getY() + (int)(getWidth() * ratio)
        );
    }

    @Override
    public void stretch(double xRatio, double yRatio) {
        this.bottomRight = new Point(
                topLeft.getX() + (int)(getLength() * xRatio),
                topLeft.getY() + (int)(getWidth() * yRatio)
        );
    }

    @Override
    public double getArea() { return getLength() * getWidth(); }

    @Override
    public double getPerimeter() { return 2 * (getLength() + getWidth()); }

    public boolean isInside(int x, int y) {
        return x >= topLeft.getX() && x <= bottomRight.getX() &&
                y >= topLeft.getY() && y <= bottomRight.getY();
    }

    public boolean isInside(Point point) { return isInside(point.getX(), point.getY()); }

    public boolean isIntersects(Rectangle rectangle) {
        return !(rectangle.getTopLeft().getX() > this.bottomRight.getX() ||
                rectangle.getBottomRight().getX() < this.topLeft.getX() ||
                rectangle.getTopLeft().getY() > this.bottomRight.getY() ||
                rectangle.getBottomRight().getY() < this.topLeft.getY());
    }

    public boolean isInside(Rectangle rectangle) {
        return isInside(rectangle.getTopLeft()) && isInside(rectangle.getBottomRight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle r = (Rectangle) o;
        return (topLeft != null ? topLeft.equals(r.topLeft) : r.topLeft == null) &&
                (bottomRight != null ? bottomRight.equals(r.bottomRight) : r.bottomRight == null);
    }

    @Override
    public int hashCode() {
        int result = topLeft != null ? topLeft.hashCode() : 0;
        return 31 * result + (bottomRight != null ? bottomRight.hashCode() : 0);
    }
}