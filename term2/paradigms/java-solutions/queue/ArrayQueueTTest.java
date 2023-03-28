package queue;

import java.util.Objects;

class ArrayQueueTTest {
    public static void main(String[] args) {
        ArrayQueue queue1 = new ArrayQueue();
        for (int i = 1; i < 6; i++) {
            switch (i) {
                case 1, 3 -> {
                    queue1.enqueue("lol");
                    assert (queue1.size() == 1);
                }
                case 2 -> {
                    queue1.dequeue();
                    assert (queue1.size() == 0);
                }
                case 4 -> {
                    assert (Objects.equals("lol", queue1.element()));
                    assert (queue1.size() == 1);
                }
                case 5 -> {
                    queue1.clear();
                    assert (queue1.size() == 0);
                }
            }
        }
    }
}