package academy.tochkavhoda.iface.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorException;

public interface Colored {
    Color getColor();
    void setColor(Color color) throws ColorException;
    void setColor(String colorString) throws ColorException;
}