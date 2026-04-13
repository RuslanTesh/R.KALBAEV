package academy.tochkavhoda.figures.v2;

public class Square extends Figure {
    private Point topLeft;
    private int side;

    public Point getBottomRight() {
        return new Point(topLeft.getX() + side, topLeft.getY() + side);
    }

    public int getLength() { return side; }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isIntersects(Square square) {
        Point br = getBottomRight();
        Point otherBr = square.getBottomRight();
        return !(square.getTopLeft().getX() > br.getX() ||
                otherBr.getX() < topLeft.getX() ||
                square.getTopLeft().getY() > br.getY() ||
                otherBr.getY() < topLeft.getY());
    }

    public boolean isInside(Square square) {
        return isInside(square.getTopLeft()) && isInside(square.getBottomRight());
    }

    public Square(Point topLeft, int side) {
        this.topLeft = topLeft;
        this.side = side;
    }

    public Square(int xLeft, int yTop, int side) {
        this.topLeft = new Point(xLeft, yTop);
        this.side = side;
    }

    public Square(int side) {
        this.topLeft = new Point(0, -side);
        this.side = side;
    }

    public Square() {
        this.topLeft = new Point(0, -1);
        this.side = 1;
    }

    public Point getTopLeft() { return topLeft; }
    public int getSide() { return side; }
    public void setTopLeft(Point topLeft) { this.topLeft = topLeft; }
    public void setSide(int side) { this.side = side; }

    @Override
    public void moveTo(int x, int y) {
        this.topLeft = new Point(x, y);
    }

    @Override
    public void moveRel(int dx, int dy) {
        this.topLeft = new Point(topLeft.getX() + dx, topLeft.getY() + dy);
    }

    @Override
    public void resize(double ratio) {
        this.side = (int) (side * ratio);
    }

    @Override
    public double getArea() { return side * side; }

    @Override
    public double getPerimeter() { return 4 * side; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square s = (Square) o;
        return side == s.side &&
                (topLeft != null ? topLeft.equals(s.topLeft) : s.topLeft == null);
    }

    @Override
    public int hashCode() {
        return 31 * (topLeft != null ? topLeft.hashCode() : 0) + side;
    }

    @Override
    public boolean isInside(int x, int y) {
        return x >= topLeft.getX() && x <= topLeft.getX() + side &&
                y >= topLeft.getY() && y <= topLeft.getY() + side;
    }
}