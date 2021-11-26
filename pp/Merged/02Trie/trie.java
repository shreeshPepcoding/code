import java.util.*;

public class trie {
    
    // leetcode 208. https://leetcode.com/problems/implement-trie-prefix-tree/
    public static class Trie {

        private class Node {
            Node[] children;
            boolean isEnd;
            Node() {
                this.children = new Node[26];
                this.isEnd = false;
            }
        }

        private Node root = null;

        /** Initialize your data structure here. */
        public Trie() {
            root = new Node();    
        }
    
        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node ptr = root;
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if(ptr.children[ch - 'a'] == null) {
                    Node nn = new Node();
                    ptr.children[ch - 'a'] = nn;
                }
                ptr = ptr.children[ch - 'a'];
            }
            ptr.isEnd = true;
        }
    
        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node ptr = root;
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if(ptr.children[ch - 'a'] == null) {
                    return false;
                }
                ptr = ptr.children[ch - 'a'];
            }
            return ptr.isEnd;
        }
    
        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String word) {
            Node ptr = root;
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if(ptr.children[ch - 'a'] == null) {
                    return false;
                }
                ptr = ptr.children[ch - 'a'];
            }
            return true;
        }
    }
    
    // leetcode 211, https://leetcode.com/problems/design-add-and-search-words-data-structure/
    public static class WordDictionary {

        private class Node {
            Node[] children;
            boolean isEnd;

            Node() {
                this.children = new Node[26];
                this.isEnd = false;
            }
        }
        
        private Node root = null;

        public WordDictionary() {
            root = new Node();
        }
    
        /** Adds a word into the data structure. */
        public void addWord(String word) {
            Node temp = root;
            
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if(temp.children[ch - 'a'] == null) {
                    Node nn = new Node();
                    temp.children[ch - 'a'] = nn;
                }
                temp = temp.children[ch - 'a'];
            }
            temp.isEnd = true;
        }
    
        /**
         * Returns if the word is in the data structure. A word could contain the dot
         * character '.' to represent any one letter.
         */

        private boolean find(Node node, String word, int indx) {
            if(indx == word.length()) {
                return node.isEnd;
            }

            char ch = word.charAt(indx);
            if(ch == '.') {
                for(int i = 0; i < 26; i++) {
                    Node child = node.children[i];
                    if(child != null && find(child, word, indx + 1) == true) {
                        return true;
                    }
                }
            } else if(node.children[ch - 'a'] != null){
                return find(node.children[ch - 'a'], word, indx + 1);
            }
            return false;
        }

        public boolean search(String word) {
            return find(root, word, 0);
        }
    }

    
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();   
    }
}