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
    public void addFirst(int data) {

    }

    public void addLast(int data) {

    }

    public void addAt(int data, int pos) {

    }

    private void addAfter(int data, Node node) {

    }

    private void addBefore(int data, Node node) {

    }

    // functions of Display in DLL
    public void displayForward() {

    }

    public void displayBackward() {

    }

    // size function
    public int size() {
        return this.size;
    }

    // function of remove in DLL
    public int removeFirst() {
        return 0;
    }

    public int removeLast() {
        return 0;
    }

    public int removeAt(int pos) {
        return 0;
    }

    private int removeAfter(Node node) {
        return 0;
    }

    private int removeBefore(Node node) {
        return 0;
    }

    private int removeNode(Node node) {
        return 0;
    }

    // get in DLL
    public int getFirst() {
        return 0;
    }

    public int getLast() {
        return 0;
    }

    public int getAt(int pos) {
        return 0;
    }
}


public class doubly {
    public static void main(String[] args) {
        
    }
}
