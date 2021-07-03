import java.util.*;

// string vs integer
class hashmap<K, V> {
    private class HMNode {
        K key;
        V value;

        public HMNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<HMNode>[] bucket = null;
    private int size = 0;

    private void initBucket(int cap) {
        bucket = new LinkedList[cap];

        for (int i = 0; i < cap; i++) {
            bucket[i] = new LinkedList<>();
        }
        size = 0;
    }

    public hashmap() {
        int cap = 4;
        initBucket(cap);
    }

    private int hashFunction(K key) {
        int bi = Math.abs(key.hashCode()) % bucket.length;
        return bi;
    }

    private int searchWithinBucket(K key, int bi) {
        int di = -1;

        // complexity -> O(n*n) where n is number of HMNodes present in single bucket
        // for(int i = 0; i < bucket[bi].size(); i++) {
        // HMNode n = bucket[bi].get(i);

        // if(n.key.equals(key) == true) {
        // di = i;
        // break;
        // }
        // }

        // for-each loop is working with iterator in complexity of O(n)
        for (HMNode n : bucket[bi]) {
            di++;
            if (n.key.equals(key) == true) {
                return di;
            }
        }
        return -1;
    }

    public void rehash() {
        LinkedList<HMNode>[] ob = bucket;
        initBucket(2 * bucket.length);

        for (int bi = 0; bi < ob.length; bi++) {
            for (HMNode n : ob[bi]) {
                put(n.key, n.value);
            }
        }
    }

    public void put(K key, V value) {
        int bi = hashFunction(key);

        int di = searchWithinBucket(key, bi);

        if (di == -1) {
            // insert
            size++;
            bucket[bi].addLast(new HMNode(key, value));
        } else {
            // update
            bucket[bi].get(di).value = value;
        }

        double lambda = size * 1.0 / bucket.length;
        if (lambda > 2.0) {
            rehash();
        }
    }

    public V get(K key) {
        int bi = hashFunction(key);

        int di = searchWithinBucket(key, bi);

        if (di == -1) {
            // absent
            return null;
        } else {
            // present
            return bucket[bi].get(di).value;
        }
    }

    public V remove(K key) {
        int bi = hashFunction(key);

        int di = searchWithinBucket(key, bi);

        if (di == -1) {
            // absent
            return null;
        } else {
            // present
            size--;
            return bucket[bi].remove(di).value;
        }
    }

    public boolean containsKey(K key) {
        int bi = hashFunction(key);

        int di = searchWithinBucket(key, bi);

        if (di == -1) {
            // absent
            return false;
        } else {
            // present
            return true;
        }
    }

    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();

        for (int bi = 0; bi < bucket.length; bi++) {
            for (HMNode n : bucket[bi]) {
                keys.add(n.key);
            }
        }
        return keys;
    }

    public void display() {
        for (int bi = 0; bi < bucket.length; bi++) {
            for (HMNode n : bucket[bi]) {
                System.out.print(n.key + " = " + n.value + ", ");
            }
        }
        System.out.println();
    }

    public void bucketWiseDisplay() {
        for (int bi = 0; bi < bucket.length; bi++) {
            System.out.print("[" + bi + "] -> ");
            for (HMNode n : bucket[bi]) {
                System.out.print(n.key + " = " + n.value + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }

}

public class genericHMap {

    public static void fun() {
        hashmap<String, Integer> map = new hashmap<>();

        map.put("India", 150);
        map.put("pak", 110);
        map.put("UK", 120);
        map.put("Uganda", 70);
        map.put("Nigeria", 80);
        map.put("England", 105);
        map.display();

        map.put("India", 160);
        map.put("England", 110);
        map.display();

        System.out.println(map.get("India"));
        System.out.println(map.get("Australia"));

        System.out.println(map.remove("pak"));
        map.display();
        System.out.println(map.remove("England"));
        map.display();

        System.out.println(map.containsKey("India"));
        System.out.println(map.containsKey("pak"));

        System.out.println(map.keySet());

    }

    public static void main(String[] args) {
        fun();
    }
}
