package Everball.plazoletafc.Utils;

public class Tuple<T, D> {
    private final T item1;
    private final D item2;

    public Tuple(T item1, D item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public T getItem1() {
        return item1;
    }

    public D getItem2() {
        return item2;
    }
}
