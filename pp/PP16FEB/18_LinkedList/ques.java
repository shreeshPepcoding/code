import java.util.*;

public class ques {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // leetcode 206. https://leetcode.com/problems/reverse-linked-list/
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return  prev;
    }

    // leetcode 876. https://leetcode.com/problems/middle-of-the-linked-list/
    public ListNode middleNode(ListNode head) {

    }

    public static void main(String[] args) {

    }
}