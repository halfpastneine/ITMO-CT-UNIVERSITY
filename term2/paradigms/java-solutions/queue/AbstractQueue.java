package queue;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue{
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object dequeue() {
        assert size > 0;
        size--;
        return deqImpl();
    }

    @Override
    public void enqueue(final Object element) {
        Objects.requireNonNull(element);
        size++;
        enqImpl(element);
    }

    @Override
    public void clear() {
        size = 0;
        clearImpl();
    }

    @Override
    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    @Override
    public int countIf(Predicate<Object> t) {
        Objects.requireNonNull(t);
        return countIfImpl(t);
    }


    protected abstract Object elementImpl();

    protected abstract void clearImpl();

    protected abstract void enqImpl(final Object element);

    protected abstract Object deqImpl();

    // :NOTE: реализации для Array и Linked похожи, можно было вынести в общий метод
    protected abstract int countIfImpl(Predicate<Object> t);

}
