/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {

        ListNode slow = head, fast = head;

        // find middle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse second half
        ListNode rev = null;
        while (slow != null) {
            ListNode temp = slow.next;
            slow.next = rev;
            rev = slow;
            slow = temp;
        }

        // compare
        while (rev != null) {
            if (head.val != rev.val) return false;
            head = head.next;
            rev = rev.next;
        }

        return true;
    }
}
