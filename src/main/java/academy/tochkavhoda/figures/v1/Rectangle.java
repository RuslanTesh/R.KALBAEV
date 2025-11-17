package academy.tochkavhoda.figures.v1;

public class Rectangle {
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

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    public int getLength() {
        return bottomRight.getX() - topLeft.getX();
    }

    public int getWidth() {
        return bottomRight.getY() - topLeft.getY();
    }

    public void moveTo(int x, int y) {
        int length = getLength();
        int width = getWidth();
        this.topLeft = new Point(x, y);
        this.bottomRight = new Point(x + length, y + width);
    }

    public void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    public void moveRel(int dx, int dy) {
        this.topLeft = new Point(topLeft.getX() + dx, topLeft.getY() + dy);
        this.bottomRight = new Point(bottomRight.getX() + dx, bottomRight.getY() + dy);
    }

    public void resize(double ratio) {
        int newLength = (int) (getLength() * ratio);
        int newWidth = (int) (getWidth() * ratio);
        this.bottomRight = new Point(topLeft.getX() + newLength, topLeft.getY() + newWidth);
    }

    public void stretch(double xRatio, double yRatio) {
        int newLength = (int) (getLength() * xRatio);
        int newWidth = (int) (getWidth() * yRatio);
        this.bottomRight = new Point(topLeft.getX() + newLength, topLeft.getY() + newWidth);
    }

    public double getArea() {
        return getLength() * getWidth();
    }

    public double getPerimeter() {
        return 2 * (getLength() + getWidth());
    }

    public boolean isInside(int x, int y) {
        return x >= topLeft.getX() && x <= bottomRight.getX() && y >= topLeft.getY() && y <= bottomRight.getY();
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

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

        Rectangle rectangle = (Rectangle) o;

        if (topLeft != null ? !topLeft.equals(rectangle.topLeft) : rectangle.topLeft != null) return false;
        return bottomRight != null ? bottomRight.equals(rectangle.bottomRight) : rectangle.bottomRight == null;
    }

    @Override
    public int hashCode() {
        int result = topLeft != null ? topLeft.hashCode() : 0;
        result = 31 * result + (bottomRight != null ? bottomRight.hashCode() : 0);
        return result;
    }
}