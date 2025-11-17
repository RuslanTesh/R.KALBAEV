package academy.tochkavhoda.figures.v1;

public class Square {
    private Point topLeft;
    private int size;

    public Square(Point leftTop, int size) {
        this.topLeft = leftTop;
        this.size = size;
    }

    public Square(int xLeft, int yTop, int size) {
        this.topLeft = new Point(xLeft, yTop);
        this.size = size;
    }

    public Square(int size) {
        this.topLeft = new Point(0, -size);
        this.size = size;
    }

    public Square() {
        this.topLeft = new Point(0, -1);
        this.size = 1;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return new Point(topLeft.getX() + size, topLeft.getY() + size);
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public int getLength() {
        return size;
    }

    public void moveTo(int x, int y) {
        this.topLeft = new Point(x, y);
    }

    public void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    public void moveRel(int dx, int dy) {
        this.topLeft = new Point(topLeft.getX() + dx, topLeft.getY() + dy);
    }

    public void resize(double ratio) {
        int oldSize = size;
        this.size = (int) (size * ratio);
        // При изменении размера координаты topLeft не меняются, как указано в задании
        // Однако, если бы требовалось изменение координат, это можно было бы сделать здесь.
        // Исходя из теста testResizeSquare1, координаты topLeft остаются неизменными.
    }

    public double getArea() {
        return size * size;
    }

    public double getPerimeter() {
        return 4 * size;
    }

    public boolean isInside(int x, int y) {
        return x >= topLeft.getX() && x <= topLeft.getX() + size && y >= topLeft.getY() && y <= topLeft.getY() + size;
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isIntersects(Square square) {
        Point otherTopLeft = square.getTopLeft();
        int otherSize = square.getLength();
        return !(otherTopLeft.getX() > this.topLeft.getX() + size ||
                this.topLeft.getX() > otherTopLeft.getX() + otherSize ||
                otherTopLeft.getY() > this.topLeft.getY() + size ||
                this.topLeft.getY() > otherTopLeft.getY() + otherSize);
    }

    public boolean isInside(Square square) {
        return isInside(square.getTopLeft()) && isInside(square.getBottomRight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Square square = (Square) o;

        if (size != square.size) return false;
        return topLeft != null ? topLeft.equals(square.topLeft) : square.topLeft == null;
    }

    @Override
    public int hashCode() {
        int result = topLeft != null ? topLeft.hashCode() : 0;
        result = 31 * result + size;
        return result;
    }
}