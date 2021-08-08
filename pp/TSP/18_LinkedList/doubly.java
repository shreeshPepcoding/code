import java.util.*;

class DoublyLinkedList {
    private class Node {
        int data;
        Node next;
        Node prev;

        public Node() {
            this.data = 0;
            this.next = this.prev = null;
        }

        public Node(int data) {
            this.data = data;
            this.next = this.prev = null;
        }

        public Node(int data, Node prev, Node next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    // function of add in DLL
    private void handleAddWhenSize0(int data) {
        Node nn = new Node(data);
        this.head = this.tail = nn;
        this.size = 1;
    }

    public void addFirst(int data) {
        if(this.size == 0) {
            handleAddWhenSize0(data);
        } else {
            Node nn = new Node(data);
            nn.next = head;
            head.prev = nn;
            head = nn;
            this.size++;
        }
    }

    public void addLast(int data) {
        if(this.size == 0) {
            handleAddWhenSize0(data);
        } else {
            Node nn = new Node(data);
            nn.prev = tail;
            tail.next = nn;
            tail = nn;
            this.size++;
        }
    }

    private Node getNodeAt(int pos) {
        Node temp = this.head;
        for(int i = 0; i < pos; i++) {
            temp = temp.next;
        }
        return temp;
    }
    
    public void addAt(int data, int pos) {
        if(pos < 0 || pos > this.size) {
            System.out.println("IndexIsInValid: -1");
            return;
        } else if(pos == 0) {
            addFirst(data);
        } else if(pos == this.size) {
            addLast(data);
        } else {
            Node nn = new Node(data);
            Node nm1 = getNodeAt(pos - 1);
            nn.next = nm1.next;
            nm1.next.prev = nn;
            nm1.next = nn;
            nn.prev = nm1;
            this.size++;
        }
    }

    private void addAfter(int data, Node node) {
        if(node == this.tail) {
            addFirst(data);
            return;
        }
        Node nn = new Node(data);
        nn.next = node.next;
        node.next.prev = nn;
        node.next = nn;
        nn.prev = node;
        this.size++;
    }

    private void addBefore(int data, Node node) {
        if(node == head) {
            addFirst(data);
            return;
        }
        Node nn = new Node(data);
        nn.prev = node.prev;
        node.prev.next = nn;
        nn.next = node;
        node.prev = nn;
        this.size++;
    }

    // functions of Display in DLL
    public void displayForward() {
        Node temp = this.head;
        System.out.print("[");
        while(temp != this.tail) {
            System.out.print(temp.data + ", ");
            temp = temp.next;
        }
        if(this.tail != null)
           System.out.print(this.tail.data);
        System.out.println("]");
    }

    public void displayBackward() {
        Node temp = this.tail;
        System.out.print("[");
        while(temp != this.head) {
            System.out.print(temp.data + ", ");
            temp = temp.prev;
        }
        if(this.head != null)
           System.out.print(this.head.data);
        System.out.println("]");
    }

    // size function
    public int size() {
        return this.size;
    }

    // function of remove in DLL
    private int handleRemoveWhenSize1() {
        int val = this.head.data;
        this.head = this.tail = null;
        this.size = 0;
        return val;
    }

    public int removeFirst() {
        if(this.size == 0) {
            System.out.print("ListIsEmpty: ");
            return -1;
        } else if(this.size == 1) {
            return handleRemoveWhenSize1();
        } else {
            int val = this.head.data;
            this.head = this.head.next;
            this.head.prev = null;

            this.size--;
            return val;
        }
    }

    public int removeLast() {
        if(this.size == 0) {
            System.out.print("ListIsEmpty: ");
            return -1;
        } else if(this.size == 1) {
            return handleRemoveWhenSize1();
        } else {
            int val = this.tail.data;
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.size--;
            return val;
        }
    }

    public int removeAt(int pos) {
        if(pos < 0 || pos >= this.size) {
            System.out.print("IndexIsInValid: ");
            return -1;
        } else if(pos == 0) {
            return removeFirst();
        } else if(pos == this.size - 1) {
            return removeLast();
        } else {
            Node node = getNodeAt(pos);
            return removeNode(node);
        }
    }

    private int removeAfter(Node node) {
        if(node.next == null) {
            System.out.print("LocationIsInvalid: ");
            return -1;
        }
        return removeNode(node.next);
    }

    private int removeBefore(Node node) {
        if(node.prev == null) {
            System.out.print("LocationIsInvalid: ");
            return -1;
        }
        return removeNode(node.prev);
    }

    private int removeNode(Node node) {
        if(node == this.head) {
            return removeFirst();
        } else if(node == this.tail) {
            return removeLast();
        }  
        int val = node.data;
        Node nm1 = node.prev;
        Node np1 = node.next;

        nm1.next = np1;
        np1.prev = nm1;
        this.size--;
        return val;
    }

    // get in DLL
    public int getFirst() {
        if(this.size == 0) {
            return -1;
        }
        return head.data;
    }

    public int getLast() {
        if(this.size == 0) return -1;

        return tail.data;
    }

    public int getAt(int pos) {
        if(pos < 0 || pos >= this.size) {
            return -1;
        }
        return getNodeAt(pos).data;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

}


public class doubly {
    public static void main(String[] args) {
        
    }
}
