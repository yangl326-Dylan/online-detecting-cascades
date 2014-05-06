package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

public class Solution12 {
	/**
	 * Given a binary tree, determine if it is a valid binary search tree (BST).
	 * solution :
	 * inorder sequence is an ascending sequence!
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root){
		if(root == null)
			return true;
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		//stack.push(root);
		
		TreeNode p = root;
		TreeNode pre = null;
		while(p!=null||!stack.isEmpty()){
			if(p!=null){
				stack.push(p);
				p = p.left;
			}else{
				p = stack.pop();
				if(pre!=null && pre.val>=p.val)
					return false;
				pre = p;
				p = p.right;
			}
		}
		return true;
	}
	/**
	 * Two elements of a binary search tree (BST) are swapped by mistake.
	 * Recover the tree without changing its structure.
	 * Could you devise a constant space solution?
	 * using Morris Traversal
	 * @param root
	 */
	public void recoverTree(TreeNode root){
		if(root == null || root.left == null && root.right == null)
			return ;
		TreeNode pred1=null ;
		TreeNode cur1=null ;
		TreeNode pred2=null ;
		TreeNode cur2=null ;
		
		TreeNode cur = root;
		TreeNode last = null; // the last node that be printed out
		
		while(cur!=null){
		
			if(cur.left!=null){
				//find its predecessor
				TreeNode pre = cur.left;
				while(pre.right!=null && pre.right != cur){
					pre = pre.right;
				}
				if(pre.right == null)
				{
					pre.right = cur;//make the predecessor's right pointer points to the current node
					cur = cur.left;
				}else
				{
					pre.right = null;//recover the predecessor's right pointer.
					last = cur;
					cur = cur.right;// notice here!!!!!!
				}				
				
			}else{
				last = cur;
				cur = cur.right;
			}
			//it's a very nice method!!!!
			if(last!=null && cur != null && last.val>cur.val){
				if(pred1 == null){
					pred1 = last;
					cur1 = cur;
				}else
				{
					pred2 = last;
					cur2 = cur;
				}
			}
		}
		
		int temp ;
		if(pred1 != null && cur2 !=null){
			temp = pred1.val;
			pred1.val = cur2.val;
			cur2.val = temp;
		}else{
			temp = pred1.val;
			pred1.val = cur1.val;
			cur1.val = temp;
		}
		
	}
	/**
	 * 中序遍历，迭代实现
	 * @param root
	 * @return
	 */
	public ArrayList<Integer> Inorder(TreeNode root){
		ArrayList<Integer> r = new ArrayList<Integer>();
		if(root == null)
			return r;
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode p = root;
		
		while(p!=null|| !stack.isEmpty()){
			if(p!=null){
				stack.push(p);
				p = p.left;
			}else{
				p = stack.pop();
				r.add(p.val);
				p = p.right;
			}
		}
		return r;
	}
	/**
	 * 先序遍历 ， 迭代实现
	 * @param root
	 * @return
	 */
	public ArrayList<Integer> Preorder(TreeNode root){
		ArrayList<Integer> r = new ArrayList<Integer>();
		if(root == null)
			return r;
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		stack.push(root);
		TreeNode p = null;
		
		while(!stack.isEmpty()){
			p = stack.pop();
			r.add(p.val);
			if(p.right!= null)
				stack.push(p.right);
			if(p.left!=null)
				stack.push(p.left);
		}
		return r;
	}
	/**
	 * 后续遍历，迭代实现
	 * @param root
	 * @return
	 */
	public ArrayList<Integer> Postorder(TreeNode root){
		ArrayList<Integer> r = new ArrayList<Integer>();
		if(root == null)
			return r;
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		
		TreeNode p = root;
		TreeNode pre = null;
		while(p!=null || !stack.isEmpty()){
			if(p!=null ){
				stack.push(p);
				p = p.left;
			}else{
				//stack.pop();
				p = stack.peek();
				if(p.right==null || 
						pre!=null && pre == p.right)//the later condition is to make sure if the right children has been popoed out
				{
					r.add(stack.pop().val);
					pre = p;
					p = null;
				}else
					p = p.right;
			}
		}
		return r;
	}
	/**
	 * 中序遍历，采用Morris 遍历算法 ，空间复杂度为O(1)，时间复杂度为O(n)
	 * @param root
	 * @return
	 */
	public ArrayList<Integer> InorderMorris(TreeNode root){
		ArrayList<Integer> r = new ArrayList<Integer>();
		if(root == null)
			return r;
		TreeNode cur = root;
		if(cur.left == null){
			r.add(cur.val);
			cur = cur.right;
		}else{
			//find cur's predecessor
			TreeNode pre = cur.left;
			while(pre!=null && pre.right!=cur)
				pre = pre.right;
			//make the predecessor's right pointer points to the current node or recover it
			if(pre.right == null)
			{
				pre.right = cur;
				cur = cur.left;
			}else{
				pre.right = null;
				cur = cur.right;// notice !!!!!
			}
		}
		return r;
	}
	/**
	 * 先序遍历，采用Morris 遍历算法 ，空间复杂度为O(1)，时间复杂度为O(n)
	 * @param root
	 * @return
	 */
	public ArrayList<Integer> PreorderMorris(TreeNode root){
		ArrayList<Integer> r = new ArrayList<Integer>();
		if(root == null)
			return r;
		TreeNode cur = root;
		while(cur!=null){
			if(cur.left==null){
				r.add(cur.val);
				cur = cur.right;
			}else{
				//find cur's predecessor
				TreeNode pre = cur.left;
				while(pre!=null && pre.right!=cur)
					pre = pre.right;
				//make the predecessor's right pointer points to the current node or recover it
				if(pre.right == null)
				{
					r.add(cur.val); // difference is here compared with Inorder
					pre.right = cur;
					cur = cur.left;
				}else{
					pre.right = null;
					cur = cur.right;// notice !!!!!
				}
			}
		}
		return r;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//TreeNode t = new TreeNode(1);
		//t.left = new TreeNode(1);
		 TreeNode root = new TreeNode(0);
	        root.left = new TreeNode(1);
	       // root.left.left = new TreeNode(3);
	       // root.right = new TreeNode(3);
	       // root.left.left = new TreeNode(11);
	        //root.right.left = new TreeNode(1);
	      //  root.right.right = new TreeNode(4);
	       // root.left.left.left = new TreeNode(7);
	      //  root.left.left.right = new TreeNode(2);
	        //root.right.right.right = new TreeNode(1);
		Solution12 s = new Solution12();
		//s.isValidBST(t);
		//System.out.println(s.Postorder(root));
		s.recoverTree(root);
	}

}
