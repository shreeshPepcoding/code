import java.util.*;

class queue {
    private int[] data;
    private int front = 0;
    private int size = 0;

    public queue(int cap) {
        data = new int[cap];
    }

    public void add(int val) {
        if(size == data.length) {
            System.out.println("Queue overflow");
            return;
        }

        int indx = (front + size) % data.length;
        data[indx] = val;
        size++;
    }

    public int remove() {
        if(size == 0) {
            System.out.println("Queue underflow");
            return -1;
        }

        int val = data[front];
        front = (front + 1) % data.length;
        size--;
        return val;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int peek() {
        if(size == 0) {
            System.out.println("Queue underflow");
            return -1;
        }
        return data[front];
    }

    @Override
    public String toString() {
        String str = "[";

        for(int i = 0; i < size - 1; i++) {
            int indx = (i + front) % data.length;
            str += data[indx] + ", ";
        }

        if(size > 0) {
            int indx = (front + size - 1) % data.length;
            str += data[indx];
        }

        str += "]";

        return str;
    }
}



public class myQueue {

    // push efficient
    public static class QueueToStackAdapter {
        Queue<Integer> mainQ;
        Queue<Integer> helperQ;
    
        public QueueToStackAdapter() {
          mainQ = new ArrayDeque<>();
          helperQ = new ArrayDeque<>();
        }
    
        int size() {
            return mainQ.size();
        }
    
        void push(int val) {
            mainQ.add(val);
        }
    
        int pop() {
            if(mainQ.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }

            // move values from mainQ to helperQ
            while(mainQ.size() > 1) {
                helperQ.add(mainQ.remove());
            }

            int data = mainQ.remove();

            // swap helperQ and mainQ rerference
            Queue<Integer> temp = mainQ;
            mainQ = helperQ;
            helperQ = temp;

            return data;
        }
    
        int top() {
            
            if(mainQ.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }

            // move values from mainQ to helperQ
            while(mainQ.size() > 1) {
                helperQ.add(mainQ.remove());
            }

            int data = mainQ.remove();
            helperQ.add(data);
            // swap helperQ and mainQ rerference
            Queue<Integer> temp = mainQ;
            mainQ = helperQ;
            helperQ = temp;

            return data;
        }
    }


    // add efficient
    public static class StackToQueueAdapter {
        Stack<Integer> mainS;
        Stack<Integer> helperS;
    
        public StackToQueueAdapter() {
          mainS = new Stack<>();
          helperS = new Stack<>();
        }
    
        int size() {
            return mainS.size();
        }
    
        void add(int val) {
            mainS.push(val);
        }
    
        int remove() {
            if(mainS.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }

            // 1. move n-1 element from mainS to helperS
            while(mainS.size() > 1) {
                helperS.push(mainS.pop());
            }
            // 2. get the data
            int data = mainS.pop();
            // 3. move all element from helper to main
            while(helperS.size() > 0) {
                mainS.push(helperS.pop());
            }
            return data;
        }
    
        int peek() {
            if(mainS.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }

            // 1. move n-1 element from mainS to helperS
            while(mainS.size() > 1) {
                helperS.push(mainS.pop());
            }
            // 2. get the data
            int data = mainS.peek();
            // 3. move all element from helper to main
            while(helperS.size() > 0) {
                mainS.push(helperS.pop());
            }
            return data;
        }
    }

    public static void ques() {

    } 

    public static void demo() {
        queue qu = new queue(6);
        qu.add(10);
        qu.add(20);
        qu.add(30);
        System.out.println(qu.size());
        qu.add(40);
        qu.add(50);
        qu.add(60);
        qu.add(70);
        System.out.println(qu.remove());
        System.out.println(qu.remove());
        System.out.println(qu.peek());
        qu.add(80);
        System.out.println(qu.peek());
        qu.add(90);
        System.out.println(qu);
        System.out.println(qu.remove());
        qu.add(100);
        qu.add(110);
        System.out.println(qu.remove());
        System.out.println(qu.remove());
        System.out.println(qu.peek());
        System.out.println(qu.remove());
        System.out.println(qu.remove());
        System.out.println(qu.remove());
        System.out.println(qu.remove());
        System.out.println(qu.peek());



        // Queue<Integer> qu = new ArrayDeque<>();

        // qu.add(10);
        // qu.add(20);
        // qu.add(30);
        // System.out.println(qu);
        // System.out.println(qu.peek());
        // qu.add(40);
        // qu.add(50);
        // System.out.println(qu.remove());
        // System.out.println(qu.peek());
        // System.out.println(qu);
        // System.out.println(qu.size());
    }

    public static void main(String[] args) {
        // demo();
        ques();
    }
}
