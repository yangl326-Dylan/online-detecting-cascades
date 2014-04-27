package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

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
class BoardNode{
	int x;
	int y;
	public BoardNode(int x, int y){
		this.x = x;
		this.y = y;
	}
}
class LNode{
	int ele;
	LNode next;
	public LNode(int _ele){
		ele = _ele;
	}
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
	 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
	 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
	 * @param board
	 */
	public void solve(char[][] board){
		//empty 2D board
		if(board.length == 0)
			return;
		//if value == 1,means the group is surrended by 'X', if value == 0 , means it is not
		HashMap<LinkedList<BoardNode>, Integer> groups = new HashMap<LinkedList<BoardNode>, Integer>();
		int xB = board.length;
		int yB = board[0].length;
		if(xB == 1 || yB == 1)
		    return;
		boolean[][] flag = new boolean[xB][yB];
		for(int i=0;i<xB;i++)
			for(int j=0;j<yB;j++)
				flag[i][j] = true;
		for(int i=0;i<xB;i++)
		{
			for(int j=0;j<yB;j++){
				if(board[i][j]=='O' && flag[i][j])
				{
					//a new group
					LinkedList<BoardNode> tree = new LinkedList<BoardNode>();
					//the corresponding value of the group
					int value = 1;
					if(i==0 || i==xB-1 || j==0 || j==yB-1)
						value = 0;
					//bread first search for the group
					tree.offer(new BoardNode(i,j));
					flag[i][j] = false;
					int point = 0;
					while(point<tree.size()){
						BoardNode temp = tree.get(point++);
						//left neighbor
						if(temp.y+1==yB-1 && board[temp.x][temp.y+1]== 'O')
							value = 0;
						if(temp.y+1<yB && flag[temp.x][temp.y+1] && board[temp.x][temp.y+1]== 'O')
						{
							flag[temp.x][temp.y+1] = false;
							tree.offer(new BoardNode(temp.x,temp.y+1));
						}
						//down neighbor
						if(temp.x+1 == xB-1 && board[temp.x+1][temp.y] == 'O')
							value = 0;
						if(temp.x+1<xB && flag[temp.x+1][temp.y] && board[temp.x+1][temp.y] == 'O')
						{
							 flag[temp.x+1][temp.y]=false;
							tree.offer(new BoardNode(temp.x+1,temp.y));
						}
						//up neighbor
						if(temp.x-1 == 0 && board[temp.x-1][temp.y]=='O')
							value = 0;
						if(temp.x-1>=0 && flag[temp.x-1][temp.y] && board[temp.x-1][temp.y] == 'O'){
							flag[temp.x-1][temp.y]=false;
							tree.offer(new BoardNode(temp.x-1,temp.y));
						}
						//left neighbor
						if(temp.y-1==0 && board[temp.x][temp.y-1]=='O')
							value = 0;
						if(temp.y-1>=0 && flag[temp.x][temp.y-1] && board[temp.x][temp.y-1]=='O')
						{
							flag[temp.x][temp.y-1] = false;
							tree.offer(new BoardNode(temp.x,temp.y-1));
						}
					}
					groups.put(tree, value);
				}
			}
		}
		//flipping the region surrounded by 'X'
		for(Entry<LinkedList<BoardNode>,Integer> e: groups.entrySet())
		{
			if(e.getValue() == 1)
			{
				for(BoardNode bn : e.getKey())
					board[bn.x][bn.y] = 'X';
			}
		}
	}
	/**
	 * Sum Root to Leaf Numbers 
	 * 采用递归的方法，宽度遍历
	 */
	int result=0;
	public int sumNumbers(TreeNode root){
		
		bFSearch(root,0);
		return result;
	}
	private void bFSearch(TreeNode root, int sum){
		//a path has been finished
		if(root.left == null && root.right == null)
		{
			result += sum*10+root.val;
			return;
		}
		if(root.left !=null)
			bFSearch(root.left,  sum*10+root.val);
		if(root.right != null)
			bFSearch(root.right,  sum*10+root.val);
	}
	/**
	 * Longest Consecutive Sequence
	 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
	 * O(n)
	 * @param num
	 * @return
	 */
	public int longestConsecutive(int[] num){
		//the most important data structure;
		HashMap<Integer,LNode> kv = new HashMap<Integer,LNode>(num.length);
		int max = 0;
		for(int n: num){
			if(!kv.containsKey(n)){
				LNode l = new LNode(n);
				if(kv.containsKey(n-1))
					kv.get(n-1).next = l;
				if(kv.containsKey(n+1))
					l.next = kv.get(n+1);
				kv.put(n, l);
			}
		}
		Iterator<LNode> it =  kv.values().iterator();
		//k is used to keep tracking those node who has been caculated in other sequences
		//it is to reduce the time complexity, to insure the O(n) time complexity
		HashMap<Integer,LNode> k = new HashMap<Integer,LNode>();
		
		while(it.hasNext())
		{
			LNode temp = it.next();
			if(!k.containsKey(temp.ele))
			{
				int nu = 1;
				while(temp.next!=null){
					temp = temp.next;
					k.put(temp.ele, temp);
					//kv.remove(temp.ele);
					nu++;
				}
				if(nu>max)
					max = nu;
			}
		}
		return max;
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
		
	//	System.out.println(s4.minCut2("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		//char[][] b={{'X','X','X'},{'X','O','X'},{'X','X','X'}};
		//s4.solve(b);
		int[] num = {-339,711,-497,-940,867,-703,654,-852,116,963,15,822,588,925,-501,-431,-412,191,77,-581,781,421,387,-458,-961,-886,915,-29,283,19,-967,-982,-270,-391,-923,-330,-95,803,-109,-905,839,978,916,428,903,-512,-930,-435,678,146,56,533,-359,-556,-925,-321,-443,-937,649,840,154,755,857,883,-636,933,-373,268,736,590,362,575,641,351,76,618,838,146,-817,517,-635,238,123,5,681,197,683,-688,259,406,-848,-513,234,-991,-630,597,-985,69,-62,823,-268,-626,471,-626,724,-379,991,636,839,-753,226,-796,557,-651,282,239,545,38,550,-123,-483,774,363,750,855,806,347,942,-52,-714,765,753,-341,309,-873,-544,929,-809,-908,768,-563,317,-954,993,665,400,305,283,-562,-72,-766,-45,-225,669,-295,-370,475,-874,-428,-503,-516,-590,-750,215,719,-551,670,721,-361,835,490,148,152,608,214,-109,8,-480,-753,445,520,-520,-527,-704,-850,178,-2,697,376,570,-733,859,979,-484,74,771,-36,-256,493,675,-421,-17,-177,804,-409,-962,-305,598,-370,-59,116,-777,494,589,-482,643,-160,589,413,-784,159,680,-925,211,-731,148,-17,233,964,-524,-19,-383,531,877,-579,-805,987,188,865,-383,-798,54,-88,695,-357,503,411,555,92,-175,770,-749,-422,917,534,-154,138,-483,-921,103,65,133,792,-331,10,-787,-137,-3,473,-199,686,674,855,-402,370,-429,-899,-146,-873,192,678,896,515,-744,814,50,102,25,639,253,-872,-295,-541,919,446,468,-795,-617,-462,677,183,-776,352,38,-105,-205,609,-5,648,808,-739,400,704,775,728,591,-102,-170,-383,-462,-843,-183,315,-385,-190,-239,-843,14,143,-306,-235,-600,-9,-810,510,885,-16,192,-47,-295,0,213,-895,-295,62,833,-631,-967,-264,-15,-430,-35,875,957,580,-315,-208,809,771,934,-497,-464,-666,493,-202,844,451,781,36,-595,-440,-890,690,-336,-186,-175,-430,255,-142,379,-759,427,-656,-811,458,996,-127,-750,805,-283,-816,-618,326,-409,947,-876,-492,399,978,616,803,-462,725,566,274,-388,-608,917,867,249,296,-819,-251,639,-631,-793,709,314,-544,514,-969,-287,895,356,376,843,553,956,315,531,572,191,-931,371,-243,415,-944,148,332,922,-530,-372,-824,219,-659,-383,498,-950,930,954,636,-965,-260,532,390,-884,448,16,-928,-237,-453,716,953,-312,160,-290,-896,216,931,435,211,-526,-863,460,692,-523,-923,264,-401,-992,291,-765,-958,-969,839,504,-781,287,-479,-637,-877,140,-921,-924,828,312,-141,-995,-399,-137,439,811,336,648,271,102,-802,421,-634,-204,501,656,104,542,686,-57,119,904,-769,712,-733,-647,-147,-582,-498,-246,729,360,-242,-670,-704,-730,-785,631,918,558,-267,-811,52,98,-15,-447,-246,-838,-905,-487,104,287,-510,407,72,-171,833,997,320,-665,-249,122,767,581,525,-937,851,812,766,842,370,-500,-969,-577,670,88,-25,-502,322,143,10,-573,-497,573,833,574,-525,739,572,794,-853,-604,-10,986,977,-485,-878,901,-673,888,816,769,-612,919,-735,-869,8,313,-372,-670,-471,711,-171,31,357,-264,-322,831,-525,-677,699,-305,-281,-311,-319,-231,276,-197,-257,602,-236,-441,444,224,-522,782,354,558,95,982,961,696,766,-210,-273,123,598,477,-46,-853,800,-347,-159,-408,414,521,360,-310,-603,-897,-635,160,-266,882,-543,211,664,811,-230,-241,866,-269,-545,632,593,254,-245,192,-196,781,338,-396,-492,-821,268,921,772,-299,-315,-757,-124,49,475,609,4,932,-106,-332,816,663,499,682,-533,-973,314,-940,353,-858,324,-842,922,-338,-166,-497,-87,-897,-502,-241,-124,182,1,752,-696,-524,434,308,-519,-672,975,-703,990,548,978,-470,574,365,-410,-72,579,913,85,575,-351,918,-922,561,-906,-425,-680,-30,756,393,-205,-866,942,-771,441,-577,-443,489,-280,-380,37,-229,150,-388,-790,812,539,788,798,-303,363};
		System.out.println(s4.longestConsecutive(num));
	}

}
