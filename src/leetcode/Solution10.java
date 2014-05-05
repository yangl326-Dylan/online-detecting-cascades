package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

class TreeLinkNode{
    int val;
    TreeLinkNode left,right,next;
    TreeLinkNode(int x){val = x;}
}
/**
 * class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
        next = null;
    }
}**/
public class Solution10 {
   /**
    * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
    * @param root
    */
    public void connect(TreeLinkNode root){
        if(root == null)
            return;
        root.next = null;
        if(root.left == null && root.right == null)
            return;
        TreeLinkNode next = root;
        while(next.left!=null && next.right!=null){
            TreeLinkNode temp = next.left;
            while(true)
            {
                if(next.left!=null&& next.right!=null)
                {
                    next.left.next = next.right;
                    next.right.next = next.next==null?null:next.next.left;
                }
                if(next.next == null)
                    break;
                next = next.next;
            }
            next = temp;
        }
    }
    /**
     * What if the given tree could be any binary tree? Would your previous solution still work?
     * @param root
     */
    public void connectAny(TreeLinkNode root){
        if(root == null)
            return ;
        //the root next pointer point to null;
        root.next = null;
        if(root.left == null && root.right == null)
            return ;
        //the current node to be handled
        TreeLinkNode curr = root;
        
        while(curr!=null){
            TreeLinkNode next =null;
            TreeLinkNode temp = null;
            while(true){
                //find the non-leaf node
                while(curr.left == null && curr.right == null){
                    if(curr.next == null)
                        break;
                    curr = curr.next;
                }
                if(next == null)
                    next = curr.left==null?(curr.right==null?null:curr.right):curr.left;
                //temp the node who need to populate the next pointer
                if(temp !=null)
                    temp.next = curr.left==null?(curr.right==null?null:curr.right):curr.left;
                //
                if(curr.left!=null && curr.right!=null)
                {
                    curr.left.next = curr.right;
                    temp = curr.right;
                }else if(curr.left!=null){
                    temp = curr.left;
                }else if(curr.right!=null){
                    temp = curr.right;
                }
                
                if(curr.next ==null){
                    if(temp!=null)
                        temp.next = null;
                    break;
                }
                curr = curr.next;
            }
            curr = next;
        }
    }
    /**
     * Solution DP
     * we keep a m*n matrix and scanning through string T,
     * p[i][j] means the number of distinct subsequence of S(0...j) equal to T(0...i) 
     * p[i][j] =  p[i][j-1]   discard s[j]
     *         +  0           if s[j] != t[i]
     *         +  p[i-1][j-1] if s[j] == t[i]
     * @param S
     * @param T
     * @return
     */
    public int numDistinct1(String S, String T){
        if(S.length()<T.length())
            return 0;
        int[][] p = new int[T.length()][S.length()];
        //for convenient
        char[] Sc = S.toCharArray();
        char[] Tc = T.toCharArray();
        
        p[0][0] = Tc[0] == Sc[0] ? 1:0;
        
        for(int i=1;i<S.length();i++)
            p[0][i] = p[0][i-1]+(Tc[0] == Sc[i]?1:0);
        
        for(int j = 1;j<T.length();j++)
            p[j][0] = 0;
        
        for(int i=1;i<T.length();i++){
            for(int j=1;j<S.length();j++){
                p[i][j] = p[i][j-1]+(Tc[i] == Sc[j] ? p[i-1][j-1]:0);
            }
        }
        return p[T.length()-1][S.length()-1];
    }
    /**
     * O(n) space complexity solution
     * @param S
     * @param T
     * @return
     */
    public int numDistinct2(String S,String T){
        if(S.length()<T.length())
            return 0;
        int[] p = new int[S.length()];
        //for convenient
        char[] Sc = S.toCharArray();
        char[] Tc = T.toCharArray();
       
        p[0] = Tc[0] == Sc[0] ? 1:0;
        
        for(int i=1;i<S.length();i++)
            p[i] = p[i-1]+(Tc[0] == Sc[i]?1:0);
        
        for(int i=1;i<T.length();i++){
            for(int j=S.length()-1;j>=0;j--){
                p[j] = p[j] + (Tc[i] == Sc[j]? p[j-1] :0);
            }
        }
        return p[S.length()-1];
       
    }
    /**
     * Given a binary tree, flatten it to a linked list in-place.
     * If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
     * @param root
     */
    public void flatten(TreeNode root){
        if(root == null || root.left==null && root.right == null)
            return;
        //the stack is for the right children
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode temp = root;
        while(true){
            if(temp.left!=null)
            {
                if(temp.right!=null)
                    stack.push(temp.right);
                temp.right = temp.left;
                temp.left = null;
                
            }
            if(temp.right != null)
                temp = temp.right;
            if(temp.left == null && temp.right == null){
                if(stack.isEmpty())
                    break;
                temp.right = stack.pop();
                temp = temp.right;
            }
        }
        
    }
    /**
     * Given a binary tree and a sum, determine if the tree has
     *  a root-to-leaf path such that adding up all the values along the path equals the given sum.
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum){
        if(root == null )
            return false;
        return recurPathSum(root,sum,0);
    }
    private boolean recurPathSum(TreeNode root, int sum , int curr){
        if(root == null)
            return sum == curr;
        if(root.left == null && root.right == null)
        {
            return curr+root.val==sum;
        }
        return (root.left == null ? false :recurPathSum(root.left,sum, curr+root.val)) || 
                (root.right==null ? false : recurPathSum(root.right, sum, curr+root.val));
    }
    /**
     * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
     * @param root
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum){
        ArrayList<ArrayList<Integer>> r = new ArrayList<ArrayList<Integer>>();
        if(root == null)
            return r;
        recurPathSum(root, sum, 0, new ArrayList<Integer>(), r);
        return r;
    }
    private void recurPathSum(TreeNode root,int sum, int curr, 
            ArrayList<Integer> path, ArrayList<ArrayList<Integer>>paths){
        if(root == null){
            if(curr == sum)
                paths.add(path);
            return;
        }
        if(root.left == null && root.right == null){
            if(curr + root.val == sum)
            {
                path.add(root.val);
                paths.add(path);
            }
            return;
        }
        if(root.left != null)
        {
            ArrayList<Integer> p = new ArrayList<Integer>();
            p.addAll(path);
            p.add(root.val);
            recurPathSum(root.left,sum,curr+root.val,p,paths);
        }
        if(root.right != null)
        {
            ArrayList<Integer> p = new ArrayList<Integer>();
            p.addAll(path);
            p.add(root.val);
            recurPathSum(root.right,sum,curr+root.val,p,paths);
        }
    }
    /**
     * Given a binary tree, find its minimum depth.
     * the minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     * @param root
     * @return
     */
    public int minDepth(TreeNode root){
        //the boundary condition
        if(root == null)
            return 0;
        return minDepthRec(root);
    }
    private int minDepthRec(TreeNode root){
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return 1;
        //the condition judged here is very important here
        if(root.left == null)
            return minDepthRec(root.right)+1;
        if(root.right == null)
            return minDepthRec(root.left)+1;
        return Math.min(minDepthRec(root.left), minDepthRec(root.right))+1;
    }
    /**
     * Given a binary tree, determine if it is height-balanced.
     * For this problem, a height-balanced binary tree is defined as a binary tree 
     * in which the depth of the two subtrees of every node never differ by more than 1.
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root){
        ArrayList<Integer> hd = new ArrayList<Integer>();
        heightRec(root, hd);
        for(int i: hd)
            if(i>1)
                return false;
        return true;
    }
    /**
     * 
     * @param root
     * @param difference is for recording the difference of the height between the two subtrees
     * @return
     */
    private int heightRec(TreeNode root, ArrayList<Integer> difference){
        if(root == null)
            return 0;
        if(root.left==null && root.right ==null)
            return 1;
        int leftH = heightRec(root.left, difference);
        int rightH = heightRec(root.right,difference);
        difference.add(Math.abs(leftH-rightH));
        return Math.max(leftH, rightH )+1;
    }
    /**
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     * list can be accessed in order only
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head){
        if(head == null)
            return null;
        int len = 0;
        ListNode temp = head;
        while(temp != null){
            len ++ ;
            temp = temp.next;
        }
        myHead = head;
        return downUpList2BST( 0 , len-1);           
    }
    ListNode myHead;
    private TreeNode downUpList2BST( int start, int end){
        if(start>end)
            return null;
        int mid = (start+end)/2;
        TreeNode left = downUpList2BST( start, mid-1);
        TreeNode root = new TreeNode(myHead.val);
        root.left = left;
        myHead = myHead.next;
        root.right = downUpList2BST(mid+1,end);
        return root;
    }
    /**
     * array can be accessed randomly
     * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     * @param num
     * @return
     */
    public TreeNode sortedArrayToBST(int[] num){
        if(num == null || num.length<=0)
            return null;
        return recSA2BST(0,num.length-1,num);
    }
    /**
     * 
     * @param start [
     * @param end ]
     * @param num
     * @return
     */
    private TreeNode recSA2BST(int start, int end, int[] num){
        if(start > end)
            return null;
        if(start == end)
        {
            TreeNode temp = new TreeNode(num[start]);
            return temp;
        }
        int mid = (start+end)/2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = recSA2BST(start, mid-1, num);
        root.right = recSA2BST(mid+1,end,num);
        return root;
    }
    public static void main(String[] args){
        Solution10 s = new Solution10();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
       /* root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);*/
       // s.flatten(root);
       // System.out.println(s.hasPathSum(root, 22));
       // System.out.println(s.isBalanced(root));
        ListNode l = new ListNode(3);
        l.next = new ListNode(5);
        l.next.next = new ListNode(8);
        System.out.println(s.sortedListToBST(l));
    }
}
