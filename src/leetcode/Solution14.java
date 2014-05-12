package leetcode;

public class Solution14 {
	/**
	 * Given a sorted linked list, delete all duplicates such that each element appear only once.
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head){
		if(head == null || head.next == null)
			return head;
		ListNode dupB = head;
		ListNode temp = head.next;
		while(temp!=null){
			if(temp.val  == dupB.val){
				temp = temp.next;
			}else{
				dupB.next = temp;
				dupB = temp;
				temp = temp.next;
			}
		}
		dupB.next = null;
		return head;
	}
	/**
	 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates2(ListNode head) {
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
