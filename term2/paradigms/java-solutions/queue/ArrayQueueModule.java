package queue;

import java.util.Arrays;
import java.util.Objects;

public class ArrayQueueModule {

//    Invariant: for i = 0 .. n: a[i] != null;
//    Immutable(n): for i = 0 .. n: a[i] != a[i']

    private static Object[] elements = new Object[2];
    private static int head = 0;
    private static int tail = 0;
    private static int size = 0;


//    Pred: element != null
//    Post: tail' = tail + 1 && elements[tail] = element && Immutable(n)
    public static void enqueue(final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
    }

    private static void ensureCapacity() {
        if ((tail + 1) % elements.length == head) {
            Object[] tmp = new Object[elements.length * 2];
            System.arraycopy(elements, head, tmp, 0, elements.length - head);
            System.arraycopy(elements, 0, tmp, elements.length - head, head);
            tail = elements.length - 1;
            elements = tmp;
            head = 0;
        }
    }

//    Pred: head != tail
//    Post: R = elements[head] && Immutable(n)
    public static Object element() {
        assert head != tail;
        return elements[head];
    }

//    Pred: head != tail
//    Post: head' = head + 1 && R = elements[head] && Immutable(n) && elements[head] == null
    public static Object dequeue() {
        assert head != tail;
        Object result = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return result;
    }

//    Pred: true
//    Post: R = elements.length && Immutable(n)
    public static int size() {
        return size;
    }

//    Pred: true
//    Post: R == (head == tail) && Immutable
    public static boolean isEmpty() {
        return head == tail;
    }

//    Pred: true
//    Post: head = 0 && tail = 0 && size = 0 && for i = 0...n: elements[i] = null
    public static void clear() {
        Arrays.fill(elements, null);
        head = 0;
        tail = 0;
        size = 0;
    }
//    Pred: element != null
//    Post: R = cnt && Immutable
    public static int count(Object element) {
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
}
