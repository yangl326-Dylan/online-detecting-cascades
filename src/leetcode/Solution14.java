package leetcode;

import java.util.HashMap;

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
		if(head == null || head.next == null)
			return head;
		ListNode pre  =  null;
		ListNode dupB = head;
		ListNode headN = null;
		int cnt = 1;
		ListNode temp = head.next;
		while(temp != null){
			if(temp.val == dupB.val)
			{
				temp = temp.next;
				cnt++;
			}
			else{
				if(cnt == 1)
				{
					if(pre == null)
					{
						pre = dupB;
						headN = pre;
					}
					else
					{
						pre.next = dupB;
						pre = dupB;
					}
				}
				dupB = temp;
				temp = temp.next;
				cnt = 1;
			}
		}
		//这一部分的处理很重要。
		if(pre!=null && cnt == 1)
			pre.next = dupB;
		else if(pre!=null)
			pre.next = null;
		else if(pre == null && cnt == 1)
			return dupB;
		
		return headN;
	}
	
	public boolean exist(char[][] board, String word){
		if(word == null || word.length() <= 0)
			return true;
		int wid = board.length;
		int len = board[0].length;
		//use to mark whether [i,j] has been visited
		HashMap<Integer, Integer> visited;
		for(int i=0;i<wid;i++){
			for(int j =0;j<len;j++){
				if(board[i][j] == word.charAt(0)){
					visited =  new HashMap<Integer,Integer>();
					visited.put(i, j);
					if(dfs(i,j,1,word,board, visited,wid,len))
						return true;
				}
			}
		}
		return false;
	}
	
	private boolean dfs(int x, int y, int index, String word, char[][] board,
			HashMap<Integer,Integer> visited,int wid, int len){
		int[] xdelta = {-1,0,1,0};//up, left, down , right
		int[] ydelta = {0,1,0,-1};
		
		if(index>=word.length())
			return true;
		
		for(int i=0;i<4;i++){
			int xn = x + xdelta[i];
			int yn = y + ydelta[i];
			if(xn>=0 && xn< wid && yn>=0 && yn < len && !(visited.containsKey(xn)&&visited.get(xn).equals(yn)) && board[xn][yn] == word.charAt(index)){
				
				HashMap<Integer,Integer> vis = new HashMap<Integer,Integer>();
				vis.putAll(visited);
				vis.put(xn, yn);
				//recursively dfs
				if(dfs(xn,yn, index+1, word, board, vis, wid, len))
					return true;
			}
		}
		
		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution14 s = new Solution14();
		ListNode r = new ListNode(1);
		r.next = new ListNode(1);
		r.next.next = new ListNode(2);
		//s.deleteDuplicates2(r);
		char[][] a = new char[3][4];
		a[0] = "ABCE".toCharArray();
		a[1] = "SFCS".toCharArray();
		a[2] = "ADEE".toCharArray();
		System.out.println(s.exist(a, "ABCB"));
	}

}
