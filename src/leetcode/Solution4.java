package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

class RandomListNode{
	int label;
	RandomListNode next, random;
	RandomListNode(int x){ this.label = x;}
}
class UndirectedGraphNode{
	int label;
	ArrayList<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}
public class Solution4 {
	/**
	 * deep copy with random pointer
	 * @param head
	 * @return
	 */
	public RandomListNode copyRandomList(RandomListNode head){
		if(head == null )
			return null;
		RandomListNode p = head;
		do{
			RandomListNode q = p.next;
			p.next = new RandomListNode(p.label);
			p.next.next = q;
			p = q;
		}while(p!=null);
		
		p = head;
		do{
			RandomListNode q = p.next.next;
			p.next.random = p.random == null? null: p.random.next;
			p = q;
		}while(p!=null);
		
		p=head;
		RandomListNode q = p.next;
		RandomListNode ch = p.next;
		RandomListNode r = ch;
		while(true)
		{
			p.next = q.next;
			r.next = q.next== null? null:q.next.next;
			if(q.next==null)
				break;
			q=r.next;
			r = q;
			p = p.next;
		}
			
		return ch;
	}
	/**
	 * Given an array of integers, every element appears twice except for one. Find that single one.
	 * 要求最好是时间复杂度为O(n)，空间复杂度为O(1)
	 * 这道题之前在面试的时候，被问到过，思路是借用按位操作符的性质：
	 * 两个相同的数异或之后为0；那么将数组中所有的数做了异或操作后就是那个出现1次的数
	 * @param A
	 * @return
	 */
	public int singleNumber(int[] A){
		int r = A[0];
		for(int i=1;i<A.length;i++)
			r = r^A[i];
		return r;
	}
	/**
	 * Given an array of integers, every element appears three times except for one. Find that single one.
	 * 下面的做法是可以扩展到除了一个数之外，这个数组中所有的数都出现x次的情况下
	 * @param A
	 * @return
	 */
	public int singleNumber3(int[] A){
		int[] count = new int[32];
		int r = 0;
		for(int i=0;i<32;i++)
		{
			count[i] = 0; //初始化
			for(int j=0;j<A.length;j++)
			{
				int t = (A[j]>>i)&1;
				if(t == 1)
					count[i]++;
			}
			r = r | (count[i]%3<<i);//如果每个数出现x次，则modx
		}
		return r;
	}
	/**
	 * There are N children standing in a line. Each child is assigned a rating value.
	 * You are giving candies to these children subjected to the following requirements:
	 * Each child must have at least one candy.
	 * Children with a higher rating get more candies than their neighbors.
	 * What is the minimum candies you must give?
	 * @param ratings
	 * @return
	 */
	public int candy(int[] ratings)
	{
		int N = ratings.length;
		int[] candy = new int [N]; //who get how many candies
		int[] left = new int[N]; //left[i] presents the number of consecutive ratings (in left side of i) smaller than ratings[i]
		int[] right = new int[N]; //right[i] presents the number of consecutive ratings (in right side of i) smaller than ratings[i] 
		
		//by DP, caculating the left and right
		left[0] = 0;
		for(int i=1;i<N;i++)
			left[i] = ratings[i]>ratings[i-1]? left[i-1]+1:0;
		right[N-1] = 0;
		for(int i=N-2;i>=0;i--)
			right[i] = ratings[i]>ratings[i+1]? right[i+1]+1:0;
		//decide how many candies each one should get
		int r = 0; 
		for(int i=0;i<N;i++)
		{
			//the local minimum
			if(left[i] == 0 && right[i] == 0)
				candy[i] = 1;
			//the local maximum
			if(left[i]!=0 && right[i]!=0)
				candy[i] = Math.max(left[i], right[i])+1;
			//other cases
			else 
				candy[i] = left[i]!=0? left[i]+1:right[i]+1;
			r =r+candy[i];
		}
		return r;
	}
	/**
	 * 
	 * @param gas
	 * @param cost
	 * @return Return the starting gas station's index if you can travel around
	 *  the circuit once, otherwise return -1.
	 */
	public int canCompleteCircuit(int[] gas, int[] cost)
	{
		int left = 0;
		int start=0, i;
		int N = gas.length;
		while(true)
		{
			i = start;
			left = 0;
			while(left+gas[i%N]-cost[i%N]>=0)
			{
				left = left+gas[i%N]-cost[i%N];
				i++;
				if(i%N == start%N)
					return i%N;
			}
			if(i>N-1)
				return -1;
			start = ++i;	
		}
	}
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		//use LinkedList to bread first search, only offer those first met nodes
		LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		//use hashmap to query quickly
		HashMap<Integer,UndirectedGraphNode> kv = new HashMap<Integer,UndirectedGraphNode>();
		
		if(node == null)
			return null;
		queue.offer(node);
		while(!queue.isEmpty())
		{
			UndirectedGraphNode label = queue.poll();
			if(kv.containsKey(label.label))
			{
				for(UndirectedGraphNode nei: label.neighbors)
				{
					if(kv.containsKey(nei.label))
						kv.get(label.label).neighbors.add(kv.get(nei.label));
					else
					{
						queue.offer(nei);
						kv.put(nei.label, new UndirectedGraphNode(nei.label));
						kv.get(label.label).neighbors.add(kv.get(nei.label));
					}
				}
			}else
			{
				kv.put(label.label, new UndirectedGraphNode(label.label));
				for(UndirectedGraphNode nei: label.neighbors)
				{
					if(kv.containsKey(nei.label))
						kv.get(label.label).neighbors.add(kv.get(nei.label));
					else
					{
						queue.offer(nei);
						kv.put(nei.label, new UndirectedGraphNode(nei.label));
						kv.get(label.label).neighbors.add(kv.get(nei.label));
					}
				}
			}
		}
		return kv.get(node.label);
	}
	/**
	 * Given a string s, partition s such that every substring of the partition is a palindrome.
	 * Return all possible palindrome partitioning of s.
	 * @param s
	 * @return
	 */
	public ArrayList<ArrayList<String>> partition(String s){
		ArrayList<ArrayList<String>> global = new ArrayList<ArrayList<String>>();
		subPartition(global, null, 0,s);
		return global;
	}
	/**
	 * 采用递归的方法，将规模较大的问题划分成规模较小的问题
	 * @param global
	 * @param curr
	 * @param start
	 * @param s
	 */
	private void subPartition(ArrayList<ArrayList<String>> global, ArrayList<String> curr, int start, String s){
		if(start == 0)
			curr = new ArrayList<String>();
		
		if(start == s.length()-1){
			curr.add(s.substring(start));
			global.add(curr);
			return;
		}
		
		if(start>=s.length()){
			global.add(curr);
			return;
		}
			
		for(int i = start;i<s.length();i++){
			String sn = s.substring(start,i+1);
			if(isPalindrome(sn)){
				ArrayList<String> nCurr = new ArrayList<String>();
				for(int k=0;k<curr.size();k++)
					nCurr.add(curr.get(k));
				nCurr.add(sn);
				subPartition(global,nCurr,i+1,s);
			}
		}	
	}
	/**
	 * O(n^3)复杂度 出现TLE
	 * @param s
	 * @return
	 */
	public int minCut(String s){
		int N = s.length();
		//mc is the array to keep the minCut of the sub-problem
		int[] mc = new int[N];
		mc[0] = 0;
		if(isPalindrome(s))
			return 0 ;
		for(int i= 1;i<N;i++){
			mc[i] = mc[i-1]+1;
			int j= i-1;
			while(j>=0){
				if(isPalindrome(s.substring(j,i+1)))
				{
					if(j==0)
						mc[i] = 0;
					else
						mc[i] = mc[i]< mc[j-1]+1? mc[i]:mc[j-1]+1;
				}
				j--;
			}
		}
		return mc[N-1];
	}
	/**
	 * O(n^2) Accepted
	 * Given a string s, partition s such that every substring of the partition is a palindrome.
	 * Return the minimum cuts needed for a palindrome partitioning of s.
	 * @param s
	 * @return
	 */
	public int minCut2(String s){
		int N = s.length();
		boolean[][] P = new boolean[N][N];//whether s[i...j] is palindrome
		//单个的都是true；
		for(int i=0;i<N;i++)
			P[i][i] = true;
		//bottom up DP process 
		for(int len =2;len<=N;len++)
		{
			for(int j =0; j<=N-len;j++){
				if(s.charAt(j) == s.charAt(j+len-1)){
					P[j][j+len-1] = len==2? true: P[j+1][j+len-2];
				}
			}
		}
		
		// mc is the array to keep the minCut of the sub-problem
		int[] mc = new int[N];
		mc[0] = 0;
		if (P[0][N-1])
			return 0;
		for (int i = 1; i < N; i++) {
			mc[i] = mc[i - 1] + 1;
			int j = i - 1;
			while (j >= 0) {
				if (P[j][i]) {
					if (j == 0)
						mc[i] = 0;
					else
						mc[i] = mc[i] < mc[j - 1] + 1 ? mc[i] : mc[j - 1] + 1;
				}
				j--;
			}
		}
		return mc[N - 1];
		
	}
	/**
	 * 判断字符串是否是回文
	 * @param s
	 * @return
	 */
	private boolean isPalindrome(String s){
		int i=0;
		int j=s.length()-1;
		while(i<=j){
			if(s.charAt(i++)!=s.charAt(j--))
				return false;
		}
		return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution4 s4 = new Solution4();
		//RandomListNode r = new RandomListNode(-1);
		//r.next =  new RandomListNode(1);
		//s4.copyRandomList(r);
		//int[] r = {1,3,4,3,2,1};
		//System.out.println(s4.candy(r));
		//int[] g ={1,2};int [] c = {2,1};
		//s4.canCompleteCircuit(g, c);
		/**UndirectedGraphNode node = new UndirectedGraphNode(-1);
		*UndirectedGraphNode node1 = new UndirectedGraphNode(1);
		*node.neighbors.add(node1);
		*node1.neighbors.add(node);
		*s4.cloneGraph(node);**/
		
		System.out.println(s4.minCut2("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

}
