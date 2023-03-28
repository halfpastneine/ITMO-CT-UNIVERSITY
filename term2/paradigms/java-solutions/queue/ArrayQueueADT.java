package queue;

import java.util.Arrays;
import java.util.Objects;

public class ArrayQueueADT {

//    Invariant: for i = 0 .. n: a[i] != null;
//    Immutable(n): for i = 0 .. n: a[i] != a[i']

    private Object[] elements = new Object[2];
    private int head = 0;
    private int tail = 0;
    private int size = 0;

//    Pred: element != null
//    Post: tail' = tail + 1 && elements[tail] = element && Immutable(n)
    public static void enqueue(final ArrayQueueADT adt, final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(adt);
        adt.elements[adt.tail] =  element;
        adt.tail = (adt.tail + 1) % adt.elements.length;
        adt.size++;
    }

    private static void ensureCapacity (final ArrayQueueADT adt) {
        if ((adt.tail + 1) % adt.elements.length == adt.head) {
            Object[] tmp = new Object[adt.elements.length * 2];
            System.arraycopy(adt.elements, adt.head, tmp, 0, adt.elements.length - adt.head);
            System.arraycopy(adt.elements, 0, tmp,adt.elements.length - adt.head , adt.head);
            adt.tail = adt.elements.length - 1;
            adt.elements = tmp;
            adt.head = 0;
        }
    }

//    Pred: head != tail
//    Post: R = elements[head] && Immutable(n)
    public static Object element(final ArrayQueueADT adt) {
        assert adt.head != adt.tail;
        return adt.elements[adt.head];
    }

//    Pred: head != tail
//    Post: head' = head + 1 && R = elements[head] && Immutable(n) && elements[head] == null
    public static Object dequeue(final ArrayQueueADT adt) {
        assert adt.head != adt.tail;
        Object result = adt.elements[adt.head];
        adt.elements[adt.head] = null;
        adt.head = (adt.head + 1) % adt.elements.length;
        adt.size--;
        return result;
    }
//    Pred: true
//    Post: R = elements.length && Immutable(n)
    public static int size(final ArrayQueueADT adt) {
        return adt.size;
    }

//    Pred: true
//    Post: R == (head == tail) && Immutable
    public static boolean isEmpty(final ArrayQueueADT adt) {
        return adt.head == adt.tail;
    }

//    Pred: true
//    Post: head = 0 && tail = 0 && size = 0 && for i = 0...n: elements[i] = null
    public static void clear(final ArrayQueueADT adt) {
        Arrays.fill(adt.elements, null);
        adt.head = 0;
        adt.tail = 0;
        adt.size = 0;
    }
//    Pred: element != null
//    Post: R = cnt && Immutable
    public static int count(final ArrayQueueADT adt, Object element) {
        Objects.requireNonNull(element);
        int cnt = 0;
        int k = adt.head;
        while (k % adt.elements.length != adt.tail) {
            if (adt.elements[k % adt.elements.length].equals(element)) {
                cnt++;
            }
            k++;
        }
        return cnt;
    }

}
