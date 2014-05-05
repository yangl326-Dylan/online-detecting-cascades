package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

public class Solution11 {

	/**
	 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
	 *  (ie, from left to right, level by level from leaf to root).
	 *  first BSF(but from right to left at each level), then using a stack to traverse from bottom to up
	 * @param root
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root){
		ArrayList<ArrayList<Integer>> r = new ArrayList<ArrayList<Integer>>();
		if(root == null)
			return r;
		// for BFS
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		// to record the level information
		LinkedList<Integer> level = new LinkedList<Integer>();
		//for down to up
		LinkedList<Integer> stack = new LinkedList<Integer>();
		//to record the level information of the stack
		LinkedList<Integer> lel = new LinkedList<Integer>();
		
		queue.offer(root);
		level.offer(1);
		while(!queue.isEmpty()){
			TreeNode cur = queue.poll();
			stack.push(cur.val);// by using stack, we can travesal from the bottom to up
			int levelCur = level.poll();
			lel.push(levelCur);
			if(cur.right!=null)
			{
				queue.offer(cur.right);
				level.offer(levelCur+1);
			}
			if(cur.left!=null){
				queue.offer(cur.left);
				level.offer(levelCur+1);
			}
		}
		ArrayList<Integer> al = null;
		int lev = -1;
		//from stack to Arraylist
		while(!stack.isEmpty()){
			int lc = lel.pop();
			if(lc!=lev)
			{
				if(al!=null)
					r.add(al);
				al = new ArrayList<Integer>();
			}
			al.add(stack.pop());
			lev = lc;
		}
		r.add(al);
		return r;
	}
	/**
	 * Given inorder and postorder traversal of a tree, construct the binary tree.
	 * @param inorder
	 * @param postorder
	 * @return
	 */
	public TreeNode buildTree(int[] inorder,int[] postorder){
		if(inorder == null || postorder ==null 
				|| inorder.length == 0 || postorder.length == 0)
			return null;
		if(inorder.length!=postorder.length)
			return null;
		if(inorder.length == 1)
		{
			if(inorder[0] != postorder[0])
				return null;
			else
				return new TreeNode(inorder[0]);
		}
		// make sure how many nodes in the left
		int i=0;
		while(inorder[i++] != postorder[postorder.length-1]);
		int[] leftInorder = new int[i-1];
		int[] leftPostorder = new int[i-1];
		int j = 0;
		while(j<i-1){
			leftInorder[j] = inorder[j];
			leftPostorder[j] = postorder[j];
			j++;
		}
		TreeNode left = buildTree(leftInorder, leftPostorder);
		TreeNode root = new TreeNode(postorder[postorder.length-1]);
		root.left = left;
		// find the right subtree nodes
		int[] rightInorder = new int[inorder.length - (i-1) -1];
		int[] rightPostorder = new int[postorder.length - (i-1) -1];
		
		j = i;
		while(j<inorder.length){
			rightInorder[j-i] = inorder[j];
			rightPostorder[j-i] = postorder[j-1];
			j++;
		}
		root.right = buildTree(rightInorder,rightPostorder);
		
		return root;
	}
	/**
	 * Given preorder and inorder traversal of a tree, 
	 * construct the binary tree.
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	public TreeNode buildTree2(int[] preorder, int[] inorder){
		if(preorder == null || inorder == null ||
				preorder.length == 0 || inorder.length == 0)
			return null;
		if(preorder.length != inorder.length)
			return null;
		int len = 0;
		while(inorder[len++]!=preorder[0]);
		//determine the left subtree
		int[] leftInorder = new int[len-1];
		int[] leftPreorder = new int[len-1];
		
		int j =0;
		while(j<len-1)
		{
			leftInorder[j] = inorder[j];
			leftPreorder[j] = preorder[j+1];
			j++;
		}
		
		TreeNode left = buildTree( leftPreorder,leftInorder);
		TreeNode root = new TreeNode(preorder[0]);
		root.left =left;
		//determine the right subtree
		int[] rightInorder = new int[inorder.length - (len-1) -1];
		int[] rightPreorder = new int[preorder.length - (len-1) -1];
		j = len;
		while(j<inorder.length){
			rightInorder[j-len] = inorder[j];
			rightPreorder[j-len] = preorder[j];
			j++;
		}
		root.right = buildTree(rightPreorder,rightInorder);
		return root;
	}
	/**
	 * Given a binary tree, find its maximum depth.
	 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root){
		if(root == null)
			return 0;
		if(root.left == null && root.right == null)
			return 1;
		return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
	}
	
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root){
		ArrayList<ArrayList<Integer>> r = new ArrayList<ArrayList<Integer>>();
		if(root == null)
			return r;
		//for BFS
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		// to record the level information
		LinkedList<Integer> level = new LinkedList<Integer>();
		// to reverse the order 
		LinkedList<Integer> stack = null;
		ArrayList<Integer> sub = null;
		queue.offer(root);
		level.offer(1);
		int last = -1;
		while(!queue.isEmpty()){
			TreeNode cur = queue.poll();
			int la = level.poll();
			//1\3\5... directly put into an array
			if(la!=last && la%2 == 1){
				//put the last level into the total result;
				if(stack!=null){
					ArrayList<Integer> rs = new ArrayList<Integer>();
					while(!stack.isEmpty())
						rs.add(stack.pop());
					r.add(rs);
				}
				sub = new ArrayList<Integer>();
				sub.add( cur.val);
			}else if(la!=last && la%2 == 0){
				//put the last level into the total result
				if(sub!=null)
					r.add(sub);
				stack = new LinkedList<Integer>();
				stack.push(cur.val);
			}else if(la == last && la%2 == 1){
				sub.add(cur.val);
			}else if(la == last && la%2 == 0){
				stack.push(cur.val);
			}
			if(cur.left != null){
				queue.offer(cur.left);
				level.offer(la+1);
			}
			if(cur.right != null){
				queue.offer(cur.right);
				level.offer(la+1);
			}	
			last = la;	
		}
		//put the last level into the total result
		if(last%2 == 1)
			r.add(sub);
		else{
			ArrayList<Integer> rs = new ArrayList<Integer>();
			while(!stack.isEmpty())
				rs.add(stack.pop());
			r.add(rs);
		}
		return r;
	}
	/**
	 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
	 * @param root
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root){
		ArrayList<ArrayList<Integer>> r = new ArrayList<ArrayList<Integer>>();
		if(root == null)
			return r;
		//for BFS
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		ArrayList<Integer> sub = new ArrayList<Integer>();
		
		queue.offer(root);
		queue.offer(null);// the null node is a label for telling a new level is begin
		while(!queue.isEmpty()){
			TreeNode cur = queue.poll();
			//to see if it is the new level begins
			if(cur == null){
				//a level has finished, we have to put nodes of the level into the total result
				r.add(sub);
				//begin the next level
				if(queue.isEmpty())
					break;
				else
					cur = queue.pop();
				queue.offer(null);
				//to store the nodes of the new level
				sub = new ArrayList<Integer>();
				sub.add(cur.val);
			}else
				sub.add(cur.val);
			
			if(cur.left!=null)
				queue.offer(cur.left);
			if(cur.right!=null)
				queue.offer(cur.right);
		}
		return r;
	}
	/**
	 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
	 * @param root
	 * @return
	 */
	public boolean isSymmetricRecursively(TreeNode root){
		if(root == null)
			return true;
		return isSymmetricR(root.left, root.right);
	}
	/**
	 * 采用迭代的方式，输入的是两个对称位置的节点，如果以这两个节点为root的子树是对称的，则
	 * 左边的左子树和右边的右子树相同，且左边的右子树和右边的左子树相同，且这两个子树的树根的值相等
	 * @param left
	 * @param right
	 * @return
	 */
	private boolean isSymmetricR(TreeNode left, TreeNode right){
		if(left== null && right == null)
			return true;
		if(left == null && right!=null || right == null && left!=null)
			return false;
		if(left.left == null && left.right == null &&
				right.left == null && right.right == null)
			return left.val == right.val;
		boolean f1 = (left.val == right.val);
		boolean l1 = isSymmetricR(left.left, right.right);
		boolean r1 = isSymmetricR(left.right, right.left);
		return f1&&l1&&r1;
		
	}
	/**
	 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
	 * solve it iteratively
	 * @param root
	 * @return
	 */
	public boolean isSymmetricIteratively(TreeNode root){
		if(root == null || (root.left == null && root.right == null))
			return true;
		LinkedList<TreeNode> qleft = new LinkedList<TreeNode>();
		LinkedList<TreeNode> qright = new LinkedList<TreeNode>();
		
		if(root.left!=null && root.right == null ||
				root.right!=null && root.left == null)
			return false;
		qleft.offer(root.left);
		qright.offer(root.right);
		
		while(!qleft.isEmpty() && !qright.isEmpty()){
			TreeNode left = qleft.poll();
			TreeNode right = qright.poll();
			
			if(left.val != right.val)
				return false;
			
			if(left.right != null && right.left!=null){
				qleft.offer(left.right);
				qright.offer(right.left);
			}else if(left.right!= null && right.left == null ||
					left.right == null && right.left !=null)
				return false;
			
			if(right.right != null && left.left!=null){
				qleft.offer(left.left);
				qright.offer(right.right);
			}else if(right.right!= null && left.left == null ||
					right.right == null && left.left !=null)
				return false;
		}
		return true;
	}
	/**
	 * by BFS, to check if they are equal or not
	 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q){
		if(p == null && q == null)
			return true;
		if(p == null && q!=null || p!=null && q==null)
			return false;
		LinkedList<TreeNode> pqueue = new LinkedList<TreeNode>();
		LinkedList<TreeNode> qqueue = new LinkedList<TreeNode>();
		
		pqueue.offer(p);
		qqueue.offer(q);
		
		while(!pqueue.isEmpty() && !qqueue.isEmpty()){
			TreeNode pC = pqueue.poll();
			TreeNode qC = qqueue.poll();
			//have same value
			if(pC.val!=qC.val)
				return false;
			//structurally identical or not
			if(pC.left!=null && qC.left == null ||
					pC.left == null && qC.left!=null)
				return false;
			else if(pC.left!=null && qC.left!=null){
				pqueue.offer(pC.left);
				qqueue.offer(qC.left);
			}
			
			if(pC.right == null && qC.right != null || 
					pC.right!=null && qC.right == null)
				return false;
			else if(pC.right!=null && qC.right!=null){
				pqueue.offer(pC.right);
				qqueue.offer(qC.right);
			}
		}
		/**
		 * there is no need
		 * if(pqueue.isEmpty() && !qqueue.isEmpty() ||
				qqueue.isEmpty() && !pqueue.isEmpty())
			return false;
		*/
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution11 s = new Solution11();
		TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        s.isSymmetricIteratively(root);
        
		//int[] in = {1,2};
		//int[] right = {2,1};
		//s.buildTree2(in, right);
	}

}
