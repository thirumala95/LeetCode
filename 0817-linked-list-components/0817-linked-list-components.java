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
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num: nums)
            set.add(num);
            boolean flag =false;
        int count=0;
        while(head!=null){
            if(set.contains(head.val)){
                if(!flag){
                    count+= 1;
                    flag = true;

                }
            }
            else{
                flag =false;

            }
            head=head.next;
        }
        return count;
    }
}
