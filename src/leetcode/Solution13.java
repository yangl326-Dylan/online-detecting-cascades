package leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution13 {

	/**
	 * 用递归的方法解决，TLE
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public boolean isInterleave(String s1, String s2, String s3){
		if(s3.length()!= s1.length()+s2.length())
			return false;
		return isInterleave(s1,s1.length()-1,s2,s2.length()-1, s3,s3.length()-1);
	}
	private boolean isInterleave(String s1, int end1, String s2, int end2, String s3, int end3){
		if(end3 <0 )
			return true;
		char last3 = s3.charAt(end3);
		char last2,last1;
		if(end2<0)
			last2 = ' ';
		else
			last2 = s2.charAt(end2);
		if(end1<0)
			last1 = ' ';
		else
			last1 = s1.charAt(end1);
		
		if(last3 == last2 && last3 == last1){
			return isInterleave(s1,end1,s2,end2-1,s3,end3-1) ||
					isInterleave(s1,end1-1,s2,end2,s3,end3-1);
		}else if(last3 == last2){
			return isInterleave(s1,end1,s2,end2-1,s3,end3-1);
		}else if(last3 == last1){
			return isInterleave(s1,end1-1,s2,end2,s3,end3-1);
		}else
			return false;
	}
	/**
	 * Solution 2 :DP Accepted
	 * create a (len1+1) x (len2+1) matrix A, A[i][j] means s3[0...i+j-1] is formed by the interleaving of s1[0...i-1] and s2[0...j-1]
	 * A[i][j] =  True   if A[i-1][j] = true && s3[i+j-1] = s1[i-1] or A[i][j-1] = true && s3[i+j-1] = s2[j-1];
	 *            False  else
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public boolean isInterleave2(String s1, String s2, String s3){
		int len1 = s1.length();
		int len2 = s2.length();
		int len3 = s3.length();
		
		if(len1+len2!=len3)
			return false;
		
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		char[] c3 = s3.toCharArray();
		
		//DP
		boolean[][] A = new boolean[len1+1][len2+1];
		A[0][0] = true;
		for(int i = 1;i<=len1;i++)
			if( A[i-1][0]== true && c3[i-1] == c1[i-1])
				A[i][0] = true;
			else
				A[i][0] = false;
		for(int j=1;j<=len2;j++)
			if(A[0][j-1] == true && c3[j-1] == c2[j-1])
				A[0][j] = true;
			else
				A[0][j] = false;
		
		for(int i=1;i<=len1;i++){
			for(int j=1;j<=len2;j++){
				if(A[i-1][j] == false && A[i][j-1] == false)
					A[i][j] = false;
				else if(A[i-1][j] == true && c3[i+j-1] == c1[i-1])
					A[i][j] = true;
				else if(A[i][j-1] == true && c3[i+j-1] == c2[j-1])
					A[i][j] = true;
				else
					A[i][j] = false;
			}
		}
		return A[len1][len2];
	}
	/**
	 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
	 * Catalan number sequence
	 * @param n
	 * @return
	 */
	public int numTrees(int n){
		if(n<=1)
			return 1;
		int[] f = new int[n+1];// the catalan
		f[0] = 1;
		f[1] = 1;
		int i=2;
		while(i<=n){
			int j = 0;
			while(j<=i-1){
				f[i] += f[j]*f[i-1-j];
				j++;
			}
			i++;
		}
		return f[n];
	}
	/**
	 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
	 * @param n
	 * @return
	 */
	 public ArrayList<TreeNode> generateTrees(int n) {
		 
		 if(n<=0)
		 {
			 ArrayList<TreeNode> r = new ArrayList<TreeNode>();
			 r.add(null);
			 return r;
		 }
		 return genTrees(1,n);
	 }
	 /**
	  * 
	  * @param start [
	  * @param end ]
	  * @return
	  */
	 private ArrayList<TreeNode> genTrees(int start, int end){
		 if(start>end)
			 return null;
		 ArrayList<TreeNode> r = new ArrayList<TreeNode>();
		 //only one element
		 if(start == end)
		 {
			 r.add(new TreeNode(start));
			 return r;
		 }
		 int i = start;
		 while(i<=end){
			 ArrayList<TreeNode> subL = genTrees(start,i-1);
			 ArrayList<TreeNode> subR = genTrees(i+1,end);
			 TreeNode root;
			 //connect the left & right subtree
			 if(subL!=null && subR!=null){
				 for(TreeNode left: subL){
					 for(TreeNode right:subR){
						 root = new TreeNode(i);
						 root.left = left;
						 root.right = right;
						 r.add(root);
					 }
				 }
			 }
			 if(subL == null && subR !=null){
				 for(TreeNode right: subR){
					 root = new TreeNode(i);
					 root.right = right;
					 r.add(root);
				 }
			 }
			 if(subR == null && subL !=null){
				 for(TreeNode left: subL){
					 root = new TreeNode(i);
					 root.left = left;
					 r.add(root);
				 }
			 }
			 i++;
		 }
		 return r;
	 }
	 /**
	  * Given a string containing only digits,
	  *  restore it by returning all possible valid IP address combinations.
	  *  采用穷举法
	  * @param s
	  * @return
	  */
	 public ArrayList<String> restoreIpAddresses(String s) {
		 ArrayList<String> r = new ArrayList<String>();
		 int len = s.length();
		 for(int i=1;i<=3;i++){
			 for(int j = i+1; j<=i+3;j++){
				 for(int k = j+1;k<=j+3;k++){
					 if(k>len-1)
						 continue;
					 String A = s.substring(0,i);
					 String B = s.substring(i,j);
					 String C = s.substring(j,k);
					 String D = s.substring(k);
					 StringBuilder sb = new StringBuilder();
					 if(isValid(D) && isValid(C) && isValid(B) && isValid(A)){
						 sb.append(A).append(".").append(B).append(".").append(C).append(".").append(D);
						 r.add(sb.toString());
					 }
				 }
			 }
		 }
		 return r;
	 }
	 /**
	  * 用于验证IP地址某一部分是否是有效的
	  * @param s
	  * @return
	  */
	 private boolean isValid(String s){
		 if(s.length()>3)
			 return false;
		 if(Integer.valueOf(s)>255)
			 return false;
		 int len = s.length();
		 char[] cs = s.toCharArray();
		 if(len>1 && cs[0] == '0' )
			 return false;
		 
		 return true;
	 }
	 /**
	  * Reverse a linked list from position m to n. Do it in-place and in one-pass. 
	  * 1 ≤ m ≤ n ≤ length of list. 
	  * @param head
	  * @param m
	  * @param n
	  * @return
	  */
	 public ListNode reverseBetween(ListNode head, int m, int n) {
		 if(head == null || m == n)
			 return head;
		 int [] seq = new int[n-m+1];
		 ListNode start = head;
		 ListNode temp = head;
		 int i = 1;
		 while(i<m){
			 start = start.next;
			 i++;
		 }
		 temp = start;
		 seq[0] = temp.val;
		 int j=1;
		 while(i<n){
			 temp = temp.next;i++;
			 seq[j++] = temp.val;
		 }
		 
		 for(j = n-m;j>=0;j--){
			 start.val = seq[j];
			 start = start.next;
		 }
		 return head;
	 }
	 /**
	  * Given a set of distinct integers, S, return all possible subsets.
	  * 这道题的做法应该要记住！！！！！
	  * @param s
	  * @return
	  */
	 public ArrayList<ArrayList<Integer>> subsets(int[] s){
		 ArrayList<ArrayList<Integer>> r = new  ArrayList<ArrayList<Integer>>();
		 Arrays.sort(s);
		 ArrayList<Integer> sub = new ArrayList<Integer>();
		 r.add(sub);
		 for(int e : s){
			 int cur_size = r.size();
			 for(int j=0;j<cur_size;j++){
				 sub = new ArrayList<Integer>();
				 sub.addAll(r.get(j));
				 sub.add(e);
				 r.add(sub);
			 }
		 }
		 return r;
	 }
	 /**
	  * Given a collection of integers that might contain duplicates, S, return all possible subsets.
	  * @param num
	  * @return
	  */
	 public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
		 ArrayList<ArrayList<Integer>> r = new ArrayList<ArrayList<Integer>>();
		 Arrays.sort(num);
		 int last = Integer.MAX_VALUE;
		 int mark = 0;
		 ArrayList<Integer> sub = new ArrayList<Integer>();
		 r.add(sub);
		 for(int e:num){
			 int cur_size = r.size();
			 int begin = e==last? mark:0;
			 for(int i = begin;i<cur_size;i++){
				 sub = new ArrayList<Integer>();
				 sub.addAll(r.get(i));
				 sub.add(e);
				 r.add(sub);
			 }
			 last = e;
			 mark = cur_size;
		 }
		 return r;
	 }
	 /**
	  * A message containing letters from A-Z is being encoded to numbers using the following mapping: 
	  * 'A' -> 1
	  * 'B' -> 2
	  *	...
      * 'Z' -> 26
      * Given an encoded message containing digits, determine the total number of ways to decode it. 
	  * @param s
	  * @return
	  */
	 public int numDecodings(String s){
		 if(s == null || s.length() == 0)
			 return 0;
		 if(s.length() == 1 && s.charAt(0)-'0'>0 && s.charAt(0)-'0' <=9)
			 return 1;
		 else if(s.length() == 1)
			 return 0;
			 
		 int len = s.length();
		 int [] num = new int[len+1];
		 num[0] = 1;
		 num[1] = 1;
		 if(s.charAt(0) == '0')
			 return 0;
		 for(int i=2;i<=len;i++){
			 char last = s.charAt(i-2);
			 char cur = s.charAt(i-1);
			 if(cur == '0'){
				 if(last == '1' || last == '2')
				 {
					 num[i] = num[i-1];
					 continue;
				 }else{
					 return 0;
				 }
			 }
			  
			 if(cur-'0'>0 && cur-'0'<=9){
				 if((last == '1' || (last == '2' && cur-'0' <=6)) && (i<len && s.charAt(i)!='0' || i == len))
					 num[i] = num[i-1] + num[i-2];
				 else
					 num[i] = num[i-1];
			 }else
				 return 0;
			
		 }
		 return num[len];
	 }
	 /**
	  * The gray code is a binary numeral system where two successive values differ in only one bit.
	  * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
	  * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
	  * 00 - 0
	  * 01 - 1
	  * 11 - 3
	  * 10 - 2
	  * Note:
	  * For a given n, a gray code sequence is not uniquely defined.
	  * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
	  * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
	  * Solution : find the rule!!!!
	  * @param n
	  * @return
	  */
	 public ArrayList<Integer> grayCode(int n) {
		 ArrayList<Integer> r = new ArrayList<Integer>();
		 r.add(0);
		 if(n == 0)
			 return r;
		 r.add(1);
		 if(n == 1)
			 return r;
		 for(int m = 1;m<n;m++){
			 int cur_size = r.size();
			 int base = (int) Math.pow(2, m);
			 for(int i = cur_size - 1;i>=0;i--){
				 r.add(r.get(i)+base);
			 }
		 }
		 return r;
	 }
	 public void merge(int A[], int m, int B[], int n) {
		
		 int len = A.length;
		 //first copy m elements of A to the end of A
		 for(int i=m-1,j = len-1;i>=0;i--){
			 A[j--] = A[i];
		 }
		 //merge the A and B
		 int startA = len - m;
		 int startB = 0;
		 int i = 0;
		 while(startA<len || startB<n){
			 while(startA<len&& startB<n && A[startA]<=B[startB])
			 {
				 A[i++] = A[startA];
				 startA++;
			 }
			 while(startA<len && startB<n && B[startB]<A[startA]){
				 A[i++] = B[startB];
				 startB++;
			 }
			 while(startA == len && startB<n){
				 A[i++] = B[startB++];
			 }
			 while(startB == n  && startA<len){
				 A[i++] = A[startA++];
			 }
		 }
	 }
	 /**
	  * second method very nice . from the end to start
	  * @param A
	  * @param m
	  * @param B
	  * @param n
	  */
	 public void merge2(int A[], int m, int B[], int n){
		 int i = m+n-1;
		 while(i>=0 &&( n>=0 || m>=0)){
			 A[i--] = m-1 < 0? B[(n--)-1]:
				 n-1 < 0? A[(m--)-1] : A[m-1]>=B[n-1]? A[(m--)-1]:B[(n--)-1];
		 }
	 }
	 /**
	  * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
	  * @param s1
	  * @param s2
	  * @return
	  */
	 public boolean isScramble(String s1, String s2) {
		 if(s1 == null && s2 == null || s1.length() == 0 && s2.length()==0)
			 return true;
		 int len1 = s1.length();
		 int len2 = s2.length();
		 if(s1.equals(s2) )
			 return true;
		 char[] c1 = s1.toCharArray();
		 char[] c2 = s2.toCharArray();
		 Arrays.sort(c1);
		 Arrays.sort(c2);
		 if(!Arrays.equals(c1, c2))
			 return false;
		 //partition two substring	
		 for(int len = 1; len<len1;len++){
			 String s11 = s1.substring(0,len);
			 String s12 = s1.substring(len,len1);
			 String s21 = s2.substring(0,len);
			 String s22 = s2.substring(len,len2);
			 String s23 = s2.substring(0,len2-len);
			 String s24 = s2.substring(len2-len,len2);
			 if(isScramble(s11,s21)&&isScramble(s12,s22) || isScramble(s11,s24)&&isScramble(s12,s23))
				 return true;
		 }
		 return false;
	 }
	 /**
	  * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
	  * You should preserve the original relative order of the nodes in each of the two partitions.
	  * @param head
	  * @param x
	  * @return
	  */
	 public ListNode partition(ListNode head, int x) {
	   	 if(head == null )
				 return head;
		     ListNode lessS = null;//the beginning of the less part
			 ListNode lessE = null; // the end of the less part
			 ListNode moreS = null; // the beginning of the more part
			 ListNode moreE = null;
			 ListNode temp = head;
			 
			 while(temp!=null){
				 if(temp.val<x ){
					 if( lessE == null){
					     lessS = temp;
						 lessE = temp;
					 }else{
						 lessE.next = temp;
						 lessE = temp;
					 }
				 }

				 if(temp.val>=x){
					 if(moreS == null)
					 {	 moreS = temp;
					     moreE = temp;
					 }
					 else{
						 moreE.next = temp;
						 moreE = temp;
					 }
				 }
				 temp = temp.next;
			 }
			 if(lessE!=null) //to connect the two partition
			    lessE.next = moreS;
			 if(moreE != null) // it's very important here, we need to make the new end's next pointer point to null
			   moreE.next = null;
			return lessS == null? head: lessS;
	 }
	 /**
	  * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
	  * @param matrix
	  * @return
	  */
	 public int maximalRectangle(char[][] matrix) {
	        if(matrix.length == 0)
				 return 0;
			 int wid = matrix.length;
			 int len = matrix[0].length;
			 int[][] height =new int[wid][len];
			 int area = 0;
			 for(int i = 0;i<wid;i++){
				 for(int j=0;j<len;j++){
					 if(i == 0)
						 height[i][j] = matrix[i][j]-'0';
					 else
						 height[i][j] = matrix[i][j] == '0' ? 0: height[i-1][j]+1;
				 }
				 int each = largestRectangleArea(height[i]);
				 if(each>area)
					 area = each;
			 }
			 return area;
	    }
	 /**
	  * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
	  * @param height
	  * @return
	  */
	 public int largestRectangleArea(int[] height) {
		 int len = height.length;
		 if(len<=0)
			 return 0;
		 int [] left = new int[len];//left[i] means the number of bars who are continuous with the ith bar and whose height is not less than ith bar's;
		 int [] right = new int[len];
		 
		 //first by DP, we caculating the left array and the right array
		 left[0] = 1; // including itself
		 for(int i=1;i<len;i++){
			 int index = i-1;
			 left[i] = 1;
			 while(index>=0 && height[index]>=height[i])
			 {
				 left[i] += left[index];
				 index = index - left[index];
			 }
		 }
		 right[len-1] = 1;
		 for(int i= len-2;i>=0;i--){
			 int index = i+1;
			 right[i] = 1;
			 while(index<len && height[index]>=height[i]){
				 right[i] += right[index];
				 index = index + right[index];
			 }
		 }
		 // caculate the area of rectangle;
		 int area = 0;
		 for(int i=0;i<len;i++){
			 if(area<(left[i]+right[i]-1)*height[i])
				 area = (left[i]+right[i]-1)*height[i];
		 }
		 return area;
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution13 s = new Solution13();
		//s.isInterleave("baababbabbababbaaababbbbbbbbbbbaabaabaaaabaaabbaaabaaaababaabaaabaabbbbaabbaabaabbbbabbbababbaaaabab", "aababaaabbbababababaabbbababaababbababbbbabbbbbababbbabaaaaabaaabbabbaaabbababbaaaababaababbbbabbbbb",
		//		"babbabbabbababbaaababbbbaababbaabbbbabbbbbaaabbabaababaabaaabaabbbaaaabbabbaaaaabbabbaabaaaabbbbababbbababbabaabababbababaaaaaabbababaaabbaabbbbaaaaabbbaaabbbabbbbaaabaababbaabababbbbababbaaabbbabbbab");
		//System.out.println(s.numDecodings("17"));
		//int [] A = {0};
		//int [] B = {1};
		//s.merge(A, 0, B, 1);
		//s.merge2(A, 0, B, 1);
		//ListNode r = new ListNode(1);
		//r.next = new ListNode(1);
		//s.partition(r, 0);
		//char[][] a = {{'0','0','0','0','1','1','1','0','1'},{'0','0','1','1','1','1','1','0','1'},{'0','0','0','1','1','1','1','1','0'}};
		char[][] a = new char[6][10];
		a[0] = "1011100010".toCharArray();
		a[1] = "0100000110".toCharArray();
		a[2] = "0101000011".toCharArray();
		a[3] = "1110000010".toCharArray();
		a[4] = "0111001010".toCharArray();
		a[5] = "1101101110".toCharArray();
		System.out.println(s.maximalRectangle(a));
	}

}
