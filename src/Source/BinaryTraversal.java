package Source;

import java.util.LinkedList;

/**
 * 迭代实现二叉树的遍历，需要自己维护一个栈
 * @author Echo
 *
 */
public class BinaryTraversal {

	public static void preOrder(Node root){
		LinkedList<Node> stack  = new LinkedList<Node>();
		Node pointer = root;
		stack.push(root);
		while(!stack.isEmpty()){
			pointer = stack.pop();
			System.out.print(pointer.data+", ");
			if(pointer.rightChild!=null)
				stack.push(pointer.rightChild);
			if(pointer.leftChild!=null)
				stack.push(pointer.leftChild);
		}
		System.out.println();
	}
	
	public static void postOrder(Node root){
		LinkedList<Node> stack = new LinkedList<Node>();
		Node pointer = null; //看栈头的一个指针
		Node out = null; //出栈的节点
		stack.push(root);
		while(!stack.isEmpty()){
			pointer = stack.peek();//只是看下栈头的节点
			if(pointer.rightChild!=null && 
					!(out!=null && out==pointer.rightChild ) &&
					!(out!=null && out==pointer.leftChild )) //需要注意已经进过栈的节点不能再进
				stack.push(pointer.rightChild);
			if(pointer.leftChild!=null && !(out!=null && out==pointer.rightChild) &&
					!(out!=null && out==pointer.leftChild )) //同上，需要注意已经进过栈的节点不能再进
				stack.push(pointer.leftChild);
			if(pointer.rightChild == null && pointer.leftChild ==null || //这说明的是叶子节点
					out!=null&&pointer.rightChild == out ||
					out!=null &&pointer.leftChild == out ) //这说明的是子节点已经被遍历到了
			{
				out = stack.pop();
				System.out.print(out.data+",");
			}
		}
		System.out.println();
	}
	/**
	 * 这里每个节点有假弹出和真弹出两种情况
	 * stack的头部都会被先假弹出一次
	 * 之后需要判断当前节点的左右节点是否已经被push进栈一次，如果没有则把当前节点的右节点push进stack，再把当前节点push进去，最后把当前节点的左节点push进去
	 * 这里判断的条件就是当前节点的左子树是否已经被遍历到，也就是上次被真的弹出的节点是当前节点的左节点，或者上次被真弹出的是当前节点左子树中最后被遍历到的一个节点
	 * 
	 * 当前节点被真的弹出 的条件与上面相反
	 * @author TingHuang
	 * @param root
	 */
	public static void inOrder(Node root){
		LinkedList<Node> stack = new LinkedList<Node>(); //迭代实现需要自己建一个栈保存信息
		Node pointer = null; //这个用于查看栈头的节点，我把它称作假出栈
		Node out =null; //这个用于保存上次被遍历到的，也就是真的出栈的节点
		stack.push(root);
		while(!stack.isEmpty())
		{
			pointer = stack.pop();
			if(pointer.rightChild!=null && //1.当前节点没有右节点
					!(out!=null && out==pointer.leftChild) && //2.当前节点的左子树已经被遍历到，且中序遍历左子树的最后一个节点是当前节点的左节点
				    !(out!=null  && out.rightChild ==null)) //3.当前节点的左子树已经被遍历到，且中序遍历左子树的最后一个节点是当前节点的左节点的右子树上，这个最后被遍历的节点无论如何都没有右节点
				stack.push(pointer.rightChild);
			stack.push(pointer);
			if(pointer.leftChild!=null && !(out!=null &&out==pointer.leftChild)
					 && !(out!=null && out.rightChild ==null))
				stack.push(pointer.leftChild);
			if(pointer.leftChild == null || (out!=null && out == pointer.leftChild) || 
				 (out!=null && out.rightChild == null)
					) //
			{
				out = stack.pop();
				System.out.print(out.data+",");
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root = LCA_Binary.construct();
		preOrder(root);
		//postOrder(root);
		//inOrder(root);
		
	}

}
