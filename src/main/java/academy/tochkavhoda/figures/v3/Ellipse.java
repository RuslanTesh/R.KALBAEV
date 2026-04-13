package academy.tochkavhoda.figures.v3;

import academy.tochkavhoda.iface.v3.Stretchable;

public class Ellipse extends Figure implements Stretchable {
    private Point center;
    private int xAxis;
    private int yAxis;

    public Ellipse(Point center, int xAxis, int yAxis) {
        this.center = center;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public Ellipse(int xCenter, int yCenter, int xAxis, int yAxis) {
        this.center = new Point(xCenter, yCenter);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public Ellipse(int xAxis, int yAxis) {
        this.center = new Point(0, 0);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public Ellipse() {
        this.center = new Point(0, 0);
        this.xAxis = 1;
        this.yAxis = 1;
    }

    public Point getCenter() { return center; }
    public int getXAxis() { return xAxis; }
    public int getYAxis() { return yAxis; }
    public void setCenter(Point center) { this.center = center; }
    public void setXAxis(int xAxis) { this.xAxis = xAxis; }
    public void setYAxis(int yAxis) { this.yAxis = yAxis; }

    @Override
    public void moveTo(int x, int y) { this.center = new Point(x, y); }

    @Override
    public void moveRel(int dx, int dy) {
        this.center = new Point(center.getX() + dx, center.getY() + dy);
    }

    @Override
    public void resize(double ratio) {
        this.xAxis = (int) (xAxis * ratio);
        this.yAxis = (int) (yAxis * ratio);
    }

    @Override
    public void stretch(double xRatio, double yRatio) {
        this.xAxis = (int) (xAxis * xRatio);
        this.yAxis = (int) (yAxis * yRatio);
    }

    @Override
    public double getArea() {
        return Math.PI * (xAxis / 2.0) * (yAxis / 2.0);
    }

    @Override
    public double getPerimeter() {
        double a = xAxis / 2.0, b = yAxis / 2.0;
        return 2 * Math.PI * Math.sqrt((a * a + b * b) / 2.0);
    }

    @Override
    public boolean isInside(int x, int y) {
        double dx = x - center.getX();
        double dy = y - center.getY();
        double a = xAxis / 2.0, b = yAxis / 2.0;
        return (dx * dx) / (a * a) + (dy * dy) / (b * b) <= 1;
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ellipse e = (Ellipse) o;
        return xAxis == e.xAxis && yAxis == e.yAxis &&
                (center != null ? center.equals(e.center) : e.center == null);
    }

    @Override
    public int hashCode() {
        int result = center != null ? center.hashCode() : 0;
        result = 31 * result + xAxis;
        result = 31 * result + yAxis;
        return result;
    }
}