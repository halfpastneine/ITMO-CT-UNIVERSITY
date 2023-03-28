package queue;

import java.util.function.Predicate;

public interface Queue {

//    Invariant: for i = 0 .. n: a[i] != null;
//    Immutable(n): for i = 0 .. n: a[i] != a[i']

//    Pred: true
//    Post: R = elements.length && Immutable(n)
    int size();

//    Pred: true
//    Post: R == (head == tail) && Immutable
    boolean isEmpty();

//    Pred: head != tail
//    Post: head' = head + 1 && R = elements[head] && Immutable(n) && elements[head] == null
    Object dequeue();

//    Pred: element != null
//    Post: tail' = tail + 1 && elements[tail] = element && Immutable(n)
    void enqueue(final Object element);

//    Pred: true
//    Post: head = 0 && tail = 0 && size = 0 && for i = 0...n: elements[i] = null
    void clear();

//    Pred: head != tail
//    Post: R = elements[head] && Immutable(n)
    Object element();

//    Pred: t != null
//    Post: R = cnt && Immutable(n)
    int countIf(Predicate<Object> t);
}
