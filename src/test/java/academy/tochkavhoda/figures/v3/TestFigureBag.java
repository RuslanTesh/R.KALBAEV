package academy.tochkavhoda.figures.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFigureBag {

    @Test
    public void testFigureBag() throws ColorException {
        Figure figure1 = new Circle(10,10,20);
        Figure figure2 = new ColoredCircle(10,10,20, Color.RED);
        Figure figure3 = new Rectangle(10,10,20,20);
        FigureBag figureBag = new FigureBag();
        figureBag.addFigure(figure1);
        figureBag.addFigure(figure2);
        assertEquals(1, figureBag.getFigureCopies(figure1));
        assertEquals(1, figureBag.getFigureCopies(figure2));
        figureBag.addFigure(figure1, 4);
        figureBag.addFigure(figure2, 3);
        assertEquals(5, figureBag.getFigureCopies(figure1));
        assertEquals(4, figureBag.getFigureCopies(figure2));
        assertTrue(figureBag.removeFigure(figure1));
        assertTrue(figureBag.removeFigure(figure2, 3));
        assertEquals(0, figureBag.getFigureCopies(figure1));
        assertEquals(1, figureBag.getFigureCopies(figure2));
        assertEquals(0, figureBag.getFigureCopies(figure3));
        assertTrue(figureBag.removeFigure(figure2, 2));
        assertEquals(0, figureBag.getFigureCopies(figure2));
        assertFalse(figureBag.removeFigure(figure3));
    }
}
