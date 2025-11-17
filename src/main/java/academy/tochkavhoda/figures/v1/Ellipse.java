package academy.tochkavhoda.figures.v1;

public class Ellipse {
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

    public Point getCenter() {
        return center;
    }

    public int getXAxis() {
        return xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    public void setXAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public void setYAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void moveTo(int x, int y) {
        this.center = new Point(x, y);
    }

    public void moveTo(Point point) {
        this.center = point;
    }

    public void moveRel(int dx, int dy) {
        this.center = new Point(center.getX() + dx, center.getY() + dy);
    }

    public void resize(double ratio) {
        this.xAxis = (int) (xAxis * ratio);
        this.yAxis = (int) (yAxis * ratio);
    }

    public void stretch(double xRatio, double yRatio) {
        this.xAxis = (int) (xAxis * xRatio);
        this.yAxis = (int) (yAxis * yRatio);
    }

    public double getArea() {
        return Math.PI * (xAxis / 2.0) * (yAxis / 2.0);
    }

    public double getPerimeter() {
        return 2 * Math.PI * Math.sqrt((xAxis * xAxis + yAxis * yAxis) / 8.0);
    }

    public boolean isInside(int x, int y) {
        int dx = x - center.getX();
        int dy = y - center.getY();
        double normalizedDistance = ((double)(dx * dx) / (xAxis * xAxis) + (double)(dy * dy) / (yAxis * yAxis));
        return normalizedDistance <= 0.25 + 1e-9; // <= 1/4
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ellipse ellipse = (Ellipse) o;

        if (xAxis != ellipse.xAxis) return false;
        if (yAxis != ellipse.yAxis) return false;
        return center != null ? center.equals(ellipse.center) : ellipse.center == null;
    }

    @Override
    public int hashCode() {
        int result = center != null ? center.hashCode() : 0;
        result = 31 * result + xAxis;
        result = 31 * result + yAxis;
        return result;
    }
}