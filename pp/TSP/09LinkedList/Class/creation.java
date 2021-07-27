import java.util.*;

class linkedlist {

    private class Node {
        private int data;
        private Node next;
    
        public Node() {
            this.data = 5;
            this.next = null;
        }
    
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public linkedlist() {
        this.head = this.tail = null;
        this.size = 0;
    }

    private void handleAddWhenSize0(int val) {
        Node nn = new Node(val);
        this.head = nn;
        this.tail = nn;
        this.size++;
    }

    public void addFirst(int val) {
        if(size == 0) {
            handleAddWhenSize0(val);
        } else {
            Node nn = new Node(val);
            nn.next = head;
            this.head = nn;
            this.size++;
        }
    }

    public void addLast(int val) {
        if(this.size == 0) {
            handleAddWhenSize0(val);
        } else {
            Node nn = new Node(val);
            this.tail.next = nn;
            this.tail = nn;
            this.size++;
        }
    }

    private Node getNthNode(int pos) {
        Node temp = this.head;

        for(int i = 0; i < pos; i++) {
            temp = temp.next;
        }

        return temp;
    }

    public void addAt(int val, int indx) {
        if(indx < 0 || indx > this.size) {
            System.out.println("Invalid Position");
        } else if(indx == 0) {
            addFirst(val);
        } else if(indx == this.size) {
            addLast(val);
        } else {
            Node nm1 = getNthNode(indx - 1);
            Node nn = new Node(val);

            nn.next = nm1.next;
            nm1.next = nn;
            this.size++;
        }
    }

    public int getFirst() {
        if(this.size == 0) {
            return -1;
        }

        return this.head.data;
    }

    public int getLast() {
        if(this.size == 0) {
            return -1;
        }

        return this.tail.data;
    }

    public int getAt(int indx) {
        if(indx < 0 || indx >= this.size) {
            return -1;
        }

        Node n = getNthNode(indx);
        return n.data;
    }

    private int handleRemoveWhenSize1() {
        int val = this.head.data;
        this.head = this.tail = null;
        this.size = 0;
        return val;
    }

    public int removeFirst() {
        if(this.size == 0) {
            return -1;
        } else if(this.size == 1) {
            return handleRemoveWhenSize1();
        } else {
            int val = this.head.data;
            this.head = this.head.next;
            this.size--;
            return val;
        }
    }

    public int removeLast() {
        if(this.size == 0) {
            return -1;
        } else if(this.size == 1) {
            return handleRemoveWhenSize1();
        } else {
            Node nm1 = getNthNode(this.size - 2);
            int val = this.tail.data;

            nm1.next = null;
            this.tail = nm1;
            this.size--;

            return val;
        }
    }

    public int removeAt(int indx) {
        if(indx < 0 || indx >= this.size) {
            return -1;
        } else if(indx == 0) {
            return removeFirst();
        } else if(indx == this.size - 1) {
            return removeLast();
        } else {
            Node nm1 = getNthNode(indx - 1);
            int val = nm1.next.data;
            nm1.next = nm1.next.next;

            this.size--;
            return val;
        }
    }

    public int size() {
        return this.size;
    }

    public void display() {
        Node temp = this.head;
        while(temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }

}


public class creation {
 
    public static void demo() {
        linkedlist list = new linkedlist();
        list.addLast(10);
        list.addLast(20);
        list.display();
        list.addLast(30);
        list.addFirst(9);
        list.display();
        list.addFirst(7);
        list.addLast(40);
        list.display();
        System.out.println(list.size());

        System.out.println(list.removeFirst());
        list.addAt(40, 2);
        list.display();

        System.out.println(list.removeAt(3));
        System.out.println(list.getAt(3));
        list.addLast(90);
        list.addLast(85);
        list.addLast(40);
        list.addLast(70);
        list.addLast(60);

        list.display();

        System.out.println(list.size());
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeAt(3);
        list.display();

        list.removeLast();
        list.removeLast();
        list.display();
    }

    public static void main(String[] args) {
        demo();
    }
}
