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
    public ListNode removeElements(ListNode head, int val) {
        ListNode start = new ListNode(-1);
        ListNode slow =start;
        ListNode fast = head;
        while(fast !=null){
            if(fast.val !=val){
                slow.next =fast;
                slow=fast;

            }
            fast=fast.next;


        }
        slow.next = null;
        return start.next;
    }
}