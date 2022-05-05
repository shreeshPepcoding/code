import java.util.*;

class hashmap<K, V> {

    private class Node {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size = 0;
    private LinkedList<Node>[] bucket;

    private void initialise(int cap) {
        bucket = new LinkedList[cap];
        // initialise every location as object of linkedlist 
        for(int i = 0; i < cap; i++) {
            bucket[i] = new LinkedList<>();
        }
        this.size = 0;
    }

    public hashmap() {
        initialise(4);
    }

    private void rehash() {
        LinkedList<Node>[] ob = bucket; 
        initialise(2 * bucket.length);

        // travel on old bucket and fill it in bucket
        for(int i = 0; i < ob.length; i++) {
            for(Node node : ob[i]) {
                put(node.key, node.value);
            }
        }
    }

    private int hashFunction(K key) {
        int bi = Math.abs(key.hashCode()) % bucket.length;
        // 0 <= bi < bucket.length
        return bi;
    }

    private int searchInBucket(K key, int bi) {
        int di = 0;
        for(Node node : bucket[bi]) {
            if(node.key.equals(key) == true) {
                return di;
            }
            di++;
        }
        return -1;
    }

    public void put(K key, V value) {
        int bi = hashFunction(key);
        // check if key is already present or need an insertion of key-value pair
        int di = searchInBucket(key, bi);

        if(di == -1) {
            // key is not present -> insertion
            bucket[bi].addLast(new Node(key, value));
            size++;
        } else {
            // key is present -> update value for that key
            bucket[bi].get(di).value = value;
        }

        int n = size;
        int N = bucket.length;

        double lambda = (n * 1.0) / N;
        if(lambda > 2) {
            rehash();
        }

    }

    public V remove(K key) {
        int bi = hashFunction(key);

        int di = searchInBucket(key, bi);
        if(di == -1) {
            // key is not present
            return null;
        } else {
            // get node at di
            Node rem = bucket[bi].remove(di);
            size--;
            return rem.value;
        }
    }

    public V get(K key) {
        int bi = hashFunction(key);

        int di = searchInBucket(key, bi);
        if(di == -1) {
            // key is not present
            return null;
        } else {
            // get node at di
            Node node = bucket[bi].get(di);
            return node.value;
        }
    }

    public boolean containsKey(K key) {
        int bi = hashFunction(key);

        int di = searchInBucket(key, bi);
        if(di == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<K> keySet() {
        ArrayList<K> list = new ArrayList<>();
        for(int bi = 0; bi < bucket.length; bi++) {
            for(Node node : bucket[bi]) {
                list.add(node.key);
            }
        }
        return list;
    }

    public int size() {
        return this.size;
    }

    public void display() {
        for(int bi = 0; bi < bucket.length; bi++) {
            for(Node node : bucket[bi]) {
                System.out.println("[" + node.key + " = " + node.value + "]");
            }
        }
    }

    public void hashmapView() {
        for(int bi = 0; bi < bucket.length; bi++) {
            System.out.print("bucket : " + bi + " -> ");
            for(Node node : bucket[bi]) {
                System.out.print("[" + node.key + " = " + node.value + "], ");
            }
            System.out.println();
        }
    }

}

public class hmapCreation {

    public static void fun() {
        hashmap<String, Integer> map = new hashmap();
        map.put("India", 125);
        map.put("pak", 90);
        map.put("US", 70);
        map.put("Australia", 100);
        map.put("Japan", 50);
        map.put("Nepal", 12);
        map.put("Bhutan", 55);
        map.put("Egypt", 75);
        map.display();
        map.hashmapView();
        map.put("India", 130);
        map.put("pak", 105);
        map.put("newzeland", 156);
        map.put("England", 175);
        map.display();
        map.hashmapView();
        System.out.println(map.size());
        System.out.println(map.remove("pak"));
        map.hashmapView();
        System.out.println(map.size());
    }

    public static void main(String[] args) {
        fun();
    }    
}
