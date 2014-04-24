package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

class TreeNode
{
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int value)
	{
		val = value;
	}
}
public class Solution2 {
	/**
	 * 后序遍历
	 * @param root
	 * @return
	 */
	public ArrayList<Integer> postOrderTraversal(TreeNode root)
	{
		ArrayList<Integer> r = new ArrayList<Integer>();
		if(root == null)
			return null;
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		
		ArrayList<TreeNode> tr = new ArrayList<TreeNode>();
		stack.push(root);
		while(!stack.isEmpty())
		{
			TreeNode temp = stack.peek();
			if((temp.left == null && temp.right == null) ||
					tr.contains(temp.left) || tr.contains(temp.right))
			{
				stack.pop();
				r.add(temp.val);
				tr.add(temp);
			}else {
				if(temp.right!=null)
					stack.push(temp.right);
				if(temp.left!=null)
					stack.push(temp.left);
			}
		}
		return r;
	}
	/**
	 * 先序遍历
	 * @param root
	 * @return
	 */
	public ArrayList<Integer> preorderTraversal(TreeNode root)
	{
		if(root == null)
			return null;
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		ArrayList<Integer> r = new ArrayList<Integer>();
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode temp = stack.pop();
			r.add(temp.val);
			if(temp.right!=null)
				stack.push(temp.right);
			if(temp.left!=null)
				stack.push(temp.left);
		}
		return r;
	}
	/**
	 * reorder the list in-place without altering the nodes' value
	 * 参考了讨论中前辈提供的一种思路O(N)时间复杂度、O(1)空间复杂度
	 * @param head
	 */
	public void reorderList(ListNode head){
		if(head == null || head.next == null)
			return ;
		int len = 0;
		ListNode curr = head,temp = null;
		//caculate the length of the list
		while(curr!=null)
		{
			len ++ ;
			curr = curr.next;
		}
		
		int i = 0;
		temp = null; // after the while, temp is the end of the first half List;
		curr = head;// after the while, curr is the head of the second half List
		while(i++<(len-len/2))
		{
			temp = curr;
			curr = curr.next; 
		}
		temp.next = null;// the next of the new end should be null
		
		//reverse the second half List
		ListNode shead = curr;
		ListNode po = curr.next;
		shead.next = null;
		while(po!=null)
		{
			ListNode t = po.next;
			po.next = shead;
			shead = po;
			po = t;
		}
		
		//merge the first half and the second reversed half
		po = head;
		while(shead !=null){
			ListNode f1 = po.next;
			ListNode f2 = shead.next;
			po.next = shead;
			shead.next = f1;
			po = f1;
			shead = f2;		
		}
	}
	/**
	 * detect the first node of the cycle if it has cycle
	 * @param head
	 * @return the first node or null
	 */
	public ListNode detectCycle(ListNode head)
	{
		if(head == null  || head.next == null)
			return null;
		
		//use a faster pointer and a slower pointer, to find the coincident node;
		ListNode faster = head;
		ListNode slower = head;
		ListNode con = null;
		while(faster!=null && faster.next!=null)
		{
			faster = faster.next.next;
			slower = slower.next;
			if(faster == slower)
			{
				con = faster;
				slower = head;
				break;
			}
		}
		//if there is no cycle, directly return null
		if(con == null)
			return null;
		
		//find the first node of the cycle, the one that both reached at the same time
		while(slower!=faster)
		{
			slower = slower.next;
			faster = faster.next;
		}
		
		return faster;
	}
	/**
	 * determine if it has a cycle in it
	 * @param head
	 * @return
	 */
	public boolean hasCycle(ListNode head){
		if(head == null || head.next == null)
			return false;
		
		//use a faster pointer and a slower pointer, to find whether they are coincident in one node;
		ListNode faster = head;
		ListNode slower = head;
		
		while(faster!=null && faster.next!=null)
		{
			faster = faster.next.next;
			slower = slower.next;
			if(faster == slower)
				return true;
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution2 s2 = new Solution2();
	/*	TreeNode root = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(3);
		root.left = n1;
		root.right = n2;
		TreeNode n3 = new TreeNode(6);
		n1.right = n3;
		TreeNode n4 = new TreeNode(4);
		n2.left = n4;
		n4.right = new TreeNode(5);
		
		System.out.println(s2.postOrderTraversal(root));
		System.out.println(s2.preorderTraversal(root));*/
		ListNode list = new ListNode(1);
		list.next = new ListNode(2);
		list.next.next = new ListNode(3);
		list.next.next.next  = new ListNode(4);
		list.next.next.next.next  = list.next.next;
		//System.out.println(list.toString());
		//s2.reorderList(list);
		//System.out.println(list.toString());
		//System.out.println(s2.hasCycle(list));
		System.out.println(s2.detectCycle(list).val);
	}

}
