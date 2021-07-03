import java.util.*;

// string vs integer
class hashMap {
    private class Node {
        String key;
        int value;

        public Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] bucket = null;
    private int size = 0;

    private void initBucket(int cap) {
        bucket = new LinkedList[cap];

        for(int i = 0; i < cap; i++) {
            bucket[i] = new LinkedList<>();
        }
        size = 0;
    }

    public hashMap() {
        int cap = 4;
        initBucket(cap);
    }   

    private int hashFunction(String key) {
        int bi = Math.abs(key.hashCode()) % bucket.length;
        return bi;
    }

    private int searchWithinBucket(String key, int bi) {
        int di = -1;

        // complexity -> O(n*n) where n is number of nodes present in single bucket
        // for(int i = 0; i < bucket[bi].size(); i++) {
        //     Node n = bucket[bi].get(i);

        //     if(n.key.equals(key) == true) {
        //         di = i;
        //         break;
        //     }
        // }

        // for-each loop is working with iterator in complexity of O(n)
        for(Node n : bucket[bi]) {
            di++;
            if(n.key.equals(key) == true) {
                return di;
            }
        }
        return -1;
    }
    
    public void rehash() {
        LinkedList<Node>[] ob = bucket;
        initBucket(2 * bucket.length);

        for(int bi = 0; bi < ob.length; bi++) {
            for(Node n : ob[bi]) {
                put(n.key, n.value);
            }
        }
    }

    public void put(String key, int value) {
        int bi = hashFunction(key);

        int di = searchWithinBucket(key, bi);

        if(di == -1) {
            // insert
            size++;
            bucket[bi].addLast(new Node(key, value));
        } else {
            // update
            bucket[bi].get(di).value = value;
        }

        double lambda = size * 1.0 / bucket.length;
        if(lambda > 2.0) {
            rehash();
        }
    }

    public int get(String key) {
        int bi = hashFunction(key);

        int di = searchWithinBucket(key, bi);

        if(di == -1) {
            // absent
            return -1;
        } else {
            // present
            return bucket[bi].get(di).value;
        }
    }

    public int remove(String key) {
        int bi = hashFunction(key);

        int di = searchWithinBucket(key, bi);

        if(di == -1) {
            // absent
            return -1;
        } else {
            // present
            size--;
            return bucket[bi].remove(di).value;
        }
    }

    public boolean containsKey(String key) {
        int bi = hashFunction(key);

        int di = searchWithinBucket(key, bi);

        if(di == -1) {
            // absent
            return false;
        } else {
            // present
            return true;
        }
    }

    public ArrayList<String> keySet() {
        ArrayList<String> keys = new ArrayList<>();

        for(int bi = 0; bi < bucket.length; bi++) {
            for(Node n : bucket[bi]) {
                keys.add(n.key);
            }
        }
        return keys;
    }

    public void display() {
        for(int bi = 0; bi < bucket.length; bi++) {
            for(Node n : bucket[bi]) {
                System.out.print(n.key + " = " + n.value + ", ");
            }
        }
        System.out.println();
    }

    public void bucketWiseDisplay() {
        for(int bi = 0; bi < bucket.length; bi++) {
            System.out.print("[" + bi + "] -> ");
            for(Node n : bucket[bi]) {
                System.out.print(n.key + " = " + n.value + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }


}

public class hmap {


    public static void fun() {
        hashMap map = new hashMap();

        map.put("India", 150);
        map.put("pak", 110);
        map.put("UK", 120);
        map.put("Uganda", 70);
        map.put("Nigeria", 80);
        map.put("England", 105);
        
        map.bucketWiseDisplay();

        map.put("india", 150);
        map.put("Pak", 110);
        map.put("uk", 120);
        map.put("uganda", 70);
        map.put("nigeria", 80);
        map.put("england", 105);



        map.bucketWiseDisplay();

        map.put("indsf", 150);
        map.put("Paksf", 110);
        map.put("uksf", 120);
        map.put("ugaaserwnda", 70);
        map.put("nigeriwfera", 80);
        map.put("englanefwd", 105);
        
        map.bucketWiseDisplay();

        // map.display();




        // map.put("India", 160);
        // map.put("England", 110);
        // map.display();

        // System.out.println(map.get("India"));
        // System.out.println(map.get("Australia"));

        // System.out.println(map.remove("pak"));
        // map.display();
        // System.out.println(map.remove("England"));
        // map.display();

        // System.out.println(map.containsKey("India"));
        // System.out.println(map.containsKey("pak"));

        // System.out.println(map.keySet());
    }

    public static void demo() {
        // hashmap map = new hashmap();

        // map.hashCode();
        // System.out.println(map);

        LinkedList<Integer> l1 = new LinkedList<>();
        LinkedList<Integer> l2 = new LinkedList<>();

        l1.addLast(10);
        l1.addLast(20);
        l1.addLast(30);

        l2.addFirst(30);
        l2.addFirst(20);
        l2.addFirst(10);

        System.out.println(l1 + " " + l2);
        System.out.println(l1.hashCode() + " " + l2.hashCode());
    }

    public static void main(String[] args) {
        fun();
        // demo();
    }
}
