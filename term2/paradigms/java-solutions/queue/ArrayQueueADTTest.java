package queue;

class ArrayQueueADTTest {
    public static void main(String[] args) {
        ArrayQueueADT queue1 = new ArrayQueueADT();
        ArrayQueueADT queue2 = new ArrayQueueADT();
        for (int i = 0; i < 31; i++) {
            ArrayQueueADT.enqueue(queue1, "biba" + i);
            ArrayQueueADT.enqueue(queue2, "lol" + i);
        }
        while (!ArrayQueueADT.isEmpty(queue1)) {
            System.out.println(ArrayQueueADT.size(queue1) + "  " + ArrayQueueADT.dequeue(queue1));
        }
        while (!ArrayQueueADT.isEmpty(queue2)) {
            System.out.println(ArrayQueueADT.size(queue2) + "  " + ArrayQueueADT.dequeue(queue2));
        }
    }
}
