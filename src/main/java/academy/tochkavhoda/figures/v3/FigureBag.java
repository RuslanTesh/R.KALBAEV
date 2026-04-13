package academy.tochkavhoda.figures.v3;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

public class FigureBag {
    private Bag<Figure> bag;

    public FigureBag() {
        this.bag = new HashBag<>();
    }

    public void addFigure(Figure figure) {
        bag.add(figure);
    }

    public void addFigure(Figure figure, int copies) {
        bag.add(figure, copies);
    }

    public boolean removeFigure(Figure figure) {
        int count = bag.getCount(figure);
        if (count == 0) return false;
        bag.remove(figure, count);
        return true;
    }

    public boolean removeFigure(Figure figure, int copies) {
        if (bag.getCount(figure) == 0) return false;
        bag.remove(figure, copies);
        return true;
    }

    public int getFigureCopies(Figure figure) {
        return bag.getCount(figure);
    }
}