package queue;

class ArrayQueueModuleTest {
    // :NOTE: в тестах стоит использовать assert для сравнения результатов с эталонными,
    //        а не делать System.out.println
    public static void main(String[] args) {
        ArrayQueueModule.enqueue("----------");
        ArrayQueueModule.count("----------");   // :NOTE: странное тестирование метода
        System.out.println(ArrayQueueModule.size());
        ArrayQueueModule.enqueue("value");
        System.out.println(ArrayQueueModule.size());
        System.out.println(ArrayQueueModule.element());
        System.out.println(ArrayQueueModule.size());
        System.out.println(ArrayQueueModule.dequeue());
        System.out.println(ArrayQueueModule.size());
        for (int i = 0; i < 32; i++) {
            ArrayQueueModule.enqueue("biba" + i);
            if (i % 5 == 0) {
                System.out.println(ArrayQueueModule.element());
            }
            if (i % 3 == 0) {
                System.out.println(ArrayQueueModule.size() + "  " + ArrayQueueModule.dequeue());
            }
        }
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.size() + "  " + ArrayQueueModule.dequeue());
        }
        for (int i = 0; i < 5; i++) {
            ArrayQueueModule.enqueue("boba" + i);
            if (i % 3 == 0) {
                System.out.println(ArrayQueueModule.size() + "  " + ArrayQueueModule.dequeue());
            }
        }
        ArrayQueueModule.clear();
        System.out.println(ArrayQueueModule.isEmpty());
    }
}
