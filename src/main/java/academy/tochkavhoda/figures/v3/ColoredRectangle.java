package academy.tochkavhoda.figures.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.iface.v3.Colored;

import java.util.Objects;

public class ColoredRectangle extends Rectangle implements Colored {
    private Color color;

    public ColoredRectangle(Point leftTop, Point rightBottom, Color color) throws ColorException {
        super(leftTop, rightBottom);
        if (color == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, Color color) throws ColorException {
        super(xLeft, yTop, xRight, yBottom);
        if (color == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    public ColoredRectangle(int length, int width, Color color) throws ColorException {
        super(length, width);
        if (color == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    public ColoredRectangle(Color color) throws ColorException {
        super();
        if (color == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    public ColoredRectangle() {
        super();
        this.color = Color.RED;
    }

    // String конструкторы
    public ColoredRectangle(Point leftTop, Point rightBottom, String colorString) throws ColorException {
        super(leftTop, rightBottom);
        this.color = Color.colorFromString(colorString);
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, String colorString) throws ColorException {
        super(xLeft, yTop, xRight, yBottom);
        this.color = Color.colorFromString(colorString);
    }

    public ColoredRectangle(int length, int width, String colorString) throws ColorException {
        super(length, width);
        this.color = Color.colorFromString(colorString);
    }

    public ColoredRectangle(String colorString) throws ColorException {
        super();
        this.color = Color.colorFromString(colorString);
    }

    @Override
    public Color getColor() { return color; }

    @Override
    public void setColor(Color color) throws ColorException {
        if (color == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        this.color = Color.colorFromString(colorString);
    }

    public boolean isIntersects(ColoredRectangle rectangle) { return super.isIntersects(rectangle); }
    public boolean isInside(ColoredRectangle rectangle) { return super.isInside(rectangle); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        return color == ((ColoredRectangle) o).color;
    }

    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), color); }
}