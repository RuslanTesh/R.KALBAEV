package academy.tochkavhoda.figures.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.iface.v3.Colored;

import java.util.Objects;

public class ColoredCircle extends Circle implements Colored {
    private Color color;

    public ColoredCircle(Point center, int radius, Color color) throws ColorException {
        super(center, radius);
        if (color == null) throw new ColorException(academy.tochkavhoda.colors.v3.ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    public ColoredCircle(int xCenter, int yCenter, int radius, Color color) throws ColorException {
        super(xCenter, yCenter, radius);
        if (color == null) throw new ColorException(academy.tochkavhoda.colors.v3.ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    public ColoredCircle(int radius, Color color) throws ColorException {
        super(radius);
        if (color == null) throw new ColorException(academy.tochkavhoda.colors.v3.ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    public ColoredCircle(Color color) throws ColorException {
        super();
        if (color == null) throw new ColorException(academy.tochkavhoda.colors.v3.ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    public ColoredCircle() {
        super();
        this.color = Color.RED;
    }

    // String конструкторы
    public ColoredCircle(Point center, int radius, String colorString) throws ColorException {
        super(center, radius);
        this.color = Color.colorFromString(colorString);
    }

    public ColoredCircle(int xCenter, int yCenter, int radius, String colorString) throws ColorException {
        super(xCenter, yCenter, radius);
        this.color = Color.colorFromString(colorString);
    }

    public ColoredCircle(int radius, String colorString) throws ColorException {
        super(radius);
        this.color = Color.colorFromString(colorString);
    }

    public ColoredCircle(String colorString) throws ColorException {
        super();
        this.color = Color.colorFromString(colorString);
    }

    @Override
    public Color getColor() { return color; }

    @Override
    public void setColor(Color color) throws ColorException {
        if (color == null) throw new ColorException(academy.tochkavhoda.colors.v3.ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        this.color = Color.colorFromString(colorString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        return color == ((ColoredCircle) o).color;
    }

    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), color); }
}