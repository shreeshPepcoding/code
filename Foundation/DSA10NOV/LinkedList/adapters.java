import java.util.*;

class myStack {
    // STL
    private LinkedList<Integer> list = null;
    public myStack() {
        list = new LinkedList<>();
    }

    // push
    public void push(int data) {
        list.addFirst(data);
    }

    // pop
    public int pop(){
        if(list.size() == 0) {
            return -1;
        }
        int data = list.removeFirst();
        return data;
    }

    // top
    public int top() {
        if(list.size() == 0) {
            return -1;
        }
        return list.getFirst();
    }

    // isempty
    public boolean isEmpty() {
        return list.size() == 0;
    }

    // size
    public int size() {
        return list.size();
    }
}

class myQueue {
    private LinkedList<Integer> list = null;
    
    public myQueue() {
        list = new LinkedList<>();
    }

    public void push(int data) {
        list.addLast(data);
    } 

    public int pop() {
        if(list.size() == 0) {
            return  -1;
        }

        int data = list.getFirst();
        list.removeFirst();
        return data;
    }

    public int front() {
        if(list.size() == 0) {
            return  -1;
        }
        return list.getFirst();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
}

public class adapters {
    
    public static void main(String[] args) {
        // myStack st = new myStack();
        // System.out.println(st.isEmpty());
        // st.push(10);
        // st.push(20);
        // st.push(30);
        // System.out.println(st.top());
        // st.push(40);
        // System.out.println(st.isEmpty());
        // st.pop();
        // System.out.println(st.top());
        // st.push(60);
        // System.out.println(st.top());
        // System.out.println(st.size());

        myQueue qu = new myQueue();
        
        System.out.println(qu.isEmpty());
        qu.push(10);
        qu.push(20);
        qu.push(30);
        System.out.println(qu.front());
        qu.push(40);
        System.out.println(qu.pop());
        System.out.println(qu.front());
        System.out.println(qu.size());
        System.out.println(qu.isEmpty());

    }
}