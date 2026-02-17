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
    public int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> values = new ArrayList<>();
        while (head !=null){
            values.add(head.val);
            head=head.next;
        }
        int n =values.size();
        Stack<Integer> stack = new Stack<>();
        int [] res = new int [n];
        for(int i=0;i<n;i++){
            while(!stack.isEmpty () && values.get(i) > values .get(stack.peek())){
                int ind =stack.pop();
                res[ind]=values.get(i);

            }
            stack.push(i);
        }
        return res;
    }
}