package queue;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class ArrayQueue extends AbstractQueue {

    private Object[] elements;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue() {
        elements = new Object[2];
    }

    protected void enqImpl(final Object element) {
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    private void ensureCapacity() {
        if ((tail + 1) % elements.length == head) {
            Object[] tmp = new Object[elements.length * 2];
            System.arraycopy(elements, head, tmp, 0, elements.length - head);
            System.arraycopy(elements, 0, tmp, elements.length - head, head);
            tail = elements.length - 1;
            elements = tmp;
            head = 0;
        }
    }

    protected Object elementImpl() {
        return elements[head];
    }

    @Override
    protected Object deqImpl() {
        Object result = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return result;
    }

    protected void clearImpl() {
        Arrays.fill(elements, null);
        head = 0;
        tail = 0;
    }

//    Pred: element != null
//    Post: R = cnt && Immutable
    public int count(Object element) {
        Objects.requireNonNull(element);
        int cnt = 0;
        int k = head;
        while (k % elements.length != tail) {
            if (elements[k % elements.length].equals(element)) {
                cnt++;
            }
            k++;
        }
        return cnt;
    }

    // :NOTE: код похож с count, нужно было вынести в отдельный метод
    protected int countIfImpl(Predicate<Object> t) {
        int cnt = 0;
        for (Object element : elements) {
            if (t.test(element)) {
                cnt++;
            }
        }
        return cnt;
    }
}
