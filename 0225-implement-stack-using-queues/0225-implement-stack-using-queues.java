import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    // Push element onto stack
    public void push(int x) {
        q2.add(x);

        // move all elements from q1 to q2
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }

        // swap q1 and q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    // Pop top element
    public int pop() {
        if (q1.isEmpty()) return -1;
        return q1.remove();
    }

    // Get top element
    public int top() {
        if (q1.isEmpty()) return -1;
        return q1.peek();
    }

    // Check empty
    public boolean empty() {
        return q1.isEmpty();
    }
}