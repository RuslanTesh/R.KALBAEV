package academy.tochkavhoda.figures.v2;

public class Circle extends Figure {
    private Point center;
    private int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle(int xCenter, int yCenter, int radius) {
        this.center = new Point(xCenter, yCenter);
        this.radius = radius;
    }

    public Circle(int radius) {
        this.center = new Point(0, 0);
        this.radius = radius;
    }

    public Circle() {
        this.center = new Point(0, 0);
        this.radius = 1;
    }

    public Point getCenter() { return center; }
    public int getRadius() { return radius; }
    public void setCenter(Point center) { this.center = center; }
    public void setRadius(int radius) { this.radius = radius; }

    @Override
    public void moveTo(int x, int y) { this.center = new Point(x, y); }

    @Override
    public void moveRel(int dx, int dy) {
        this.center = new Point(center.getX() + dx, center.getY() + dy);
    }

    @Override
    public void resize(double ratio) {
        this.radius = (int) (radius * ratio);
    }

    @Override
    public double getArea() { return Math.PI * radius * radius; }

    @Override
    public double getPerimeter() { return 2 * Math.PI * radius; }

    public boolean isInside(int x, int y) {
        int dx = x - center.getX();
        int dy = y - center.getY();
        return dx * dx + dy * dy <= radius * radius;
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return radius == circle.radius &&
                (center != null ? center.equals(circle.center) : circle.center == null);
    }

    @Override
    public int hashCode() {
        int result = center != null ? center.hashCode() : 0;
        return 31 * result + radius;
    }
}