package queue;


import java.util.function.Predicate;

public class LinkedQueue extends AbstractQueue {
    private Node head;
    private Node tail;

    protected void enqImpl(final Object element) {
        if (tail == null) {
            tail = new Node(element, null);
            head = tail;
        } else {
            tail.next = new Node(element, null);
            tail = tail.next;
        }
    }

    protected Object elementImpl() {
        return head.element;
    }

    protected Object deqImpl() {
        Object result = head.element;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return result;
    }

    protected void clearImpl() {
        head = tail = null;
    }

    protected int countIfImpl(Predicate<Object> t) {
        int cnt = 0;
        Node h = head;
        while (head != null) {
            if (t.test(head.element)) {
                cnt++;
            }
            head = head.next;
        }
        head = h;
        return cnt;
    }

    public static class Node {
        private final Object element;
        private Node next;

        public Node(Object element, Node next) {
            this.element = element;
            this.next = next;
        }
    }
}
