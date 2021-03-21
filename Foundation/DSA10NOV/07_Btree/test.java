import java.util.*;
public class test {

    public static void display(int[] tree, int rootIndx) {
        if(rootIndx >= tree.length) return;

        int lci = 2 * rootIndx + 1;
        int rci = 2 * rootIndx + 2;

        String str = "";
        str += lci < tree.length ? tree[lci] : ". ";
        str += " <- " + tree[rootIndx] + " -> ";
        str += rci < tree.length ? tree[rci] : " .";
        System.out.println(str);

        display(tree, lci);
        display(tree, rci);
    }

    public static void main(String[] args) {
        int[] tree = {10, 20, 30, 40, 50, 60, 70, 80};

        display(tree, 0);
    }
}
