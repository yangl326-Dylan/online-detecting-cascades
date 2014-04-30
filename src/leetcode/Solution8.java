package leetcode;

public class Solution8 {
	/**
	 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
	 * @param s
	 * @return
	 */
	public boolean isPalindrome(String s){
		String pre = preProcess(s);
		char[] sw = pre.toCharArray();
		if(pre==null || pre.length()<=1 )
			return true;
		int i=0;
		int j=pre.length()-1;
		while(i<=j){
			if(sw[i]!=sw[j])
				return false;
			i++;
			j--;
		}
		return true;
	}
	/**
	 * pre-processing the string, remove all non-isAlphanumeric
	 * and change all to lower
	 * @param s
	 * @return
	 */
	private String preProcess(String s){
		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<s.length();i++)
			if(isAlphanumeric(s.charAt(i)))
				sb.append(s.charAt(i));
		return sb.toString().toLowerCase();
	}
	/**
	 * make decision if a character is a alphanumeric
	 * @param c
	 * @return
	 */
	private boolean isAlphanumeric(char c){
		if(c>='a' && c<='z' || c>='A' && c<='Z' || c>='0'&& c<='9')
			return true;
		return false;
	}
	
	public int maxPathSum(TreeNode root){
		if(root == null)
			return 0;
		return subProblem(root);
	}
	private int subProblem(TreeNode root){
		if(root == null)
			return 0;
		if(root.left == null && root.right == null)
			return root.val;
		int left = subProblem(root.left);
		int right = subProblem(root.right);
		int lc = maxSinglePath(root.left);
		int rc = maxSinglePath(root.right);
		return Math.max(left, right)>lc+rc+root.val?Math.max(left, right):lc+rc+root.val;
	}
	private int maxSinglePath(TreeNode root){
		if(root==null)
			return 0;
		if(root.left==null && root.right ==null)
			return root.val;
		int lc = maxSinglePath(root.left);
		int lr = maxSinglePath(root.right);
		return Math.max(lc, lr)+root.val;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution8 s8 = new Solution8();
		//System.out.println(s8.isPalindrome("aA"));
	}

}
