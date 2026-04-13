package academy.tochkavhoda.figures.v2;

public class CircleFactory {
    private static int circleCount = 0;

    public static Circle createCircle(Point center, int radius) {
        circleCount++;
        return new Circle(center, radius);
    }

    public static int getCircleCount() {
        return circleCount;
    }

}