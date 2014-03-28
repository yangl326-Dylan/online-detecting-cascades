package Source;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *Binary tree node
 */
class Node {
	int data;
	Node leftChild;
	Node rightChild;
	public Node(int _data){
		data = _data;
	}
	public Node(int _data,  Node left,  Node right){
		data = _data;
		leftChild = left;
		rightChild = right;
		//super.equals(arg0)
	}

	public boolean equals(Node o){
		if(o.data == this.data)
			return true;
		return false;
	}
	public String toString()
	{
		return String.valueOf(this.data);
	}
}
public class LCA_Binary {

	/**
	 *method1
	 * up to down O(n^2)
	 * @return
	 */
	public static Node lca1(Node root, Node p, Node q){
		if(root.equals(p) || root.equals(q))
			return root;
		//if(hasNode(root.leftChild,p)&&hasNode(root.rightChild,q) || hasNode(root.leftChild,q)&&hasNode(root.rightChild,p))
			//return root;
		boolean b1=hasNode(root.leftChild,p,q);
		boolean b2=hasNode(root.rightChild,p,q);
		if(b1&&b2)
			return root;
		if(b1)
			return lca1(root.leftChild,p,q);
		if(b2)
			return lca1(root.rightChild,p,q);
		return null;
		
	}
	private static boolean hasNode( Node root,  Node p,  Node q){
		if(root == null)
			return false;
		if(root.equals(p) || root.equals(q))
			return true;
		if(root.leftChild!=null && hasNode(root.leftChild, p,q))
			return true;
		if(root.rightChild!=null && hasNode(root.rightChild,p,q))
			return true;
		return false;
	}
	/**
	 * method2 from down to up
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public static  Node lca2( Node root,  Node p,   Node q)
	{
		if(root == null)
			return null;
		if(root.equals(p) || root.equals(q))
			return root;
		Node L = lca2(root.leftChild,p,q);
		Node R = lca2(root.rightChild,p,q);
		
		if(L!=null && R !=null){
			return root;
		}
		return L==null ? R:L;
	}
	public static  Node construct(){
		Node root = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n8 = new Node(8);
		Node n9 = new Node(9);
		
		root.leftChild = n2;
		root.rightChild = n3;
		n2.leftChild = n4;
		n2.rightChild = n5;
		n3.leftChild = n6;
		n5.leftChild = n7;
		n4.rightChild = n8;
		n7.rightChild = n9;
		n5.rightChild = new Node(10);
		n9.leftChild = new Node(11);
		return root;
	}
	public static Node construct1(){
		Node root = new Node(5);
		Node n2 = new Node(4);
		Node n3 = new Node(8);
		Node n4 = new Node(11);
		Node n5 = new Node(13);
		Node n6 = new Node(4);
		Node n7 = new Node(7);
		Node n8 = new Node(2);
		Node n9 = new Node(5);
		Node n10 = new Node(1);
		
		root.leftChild = n2;
		root.rightChild = n3;
		n2.leftChild=n4;
		n3.leftChild = n5;
		n3.rightChild = n6;
		n4.leftChild = n7;
		n4.rightChild = n8;
		n6.leftChild = n9;
		n6.rightChild = n10;
		return root;
	}
	/**
	 * binary search tree
	 * @return
	 */
	public static Node construct2(){
		Node root = new Node(10);
		Node n2 = new Node(5);
		Node n3 = new Node(15);
		Node n4 = new Node(2);
		Node n5 = new Node(7);
		Node n6 = new Node(13);
		Node n7 = new Node(17);
		Node n8 = new Node(1);
		Node n9 = new Node(3);
		Node n10 = new Node(6);
		
		root.leftChild = n2;
		root.rightChild = n3;
		n2.leftChild=n4;
		n2.rightChild = n5;
		n3.leftChild = n6;
		n3.rightChild = n7;
		n4.leftChild = n8;
		n4.rightChild = n9;
		n5.leftChild = n10;
		return root;
	}
	public static  Node lca3( Node root,  Node p,   Node q){
		LinkedList< Node> path1 = new LinkedList< Node>();
		LinkedList<Node> path2 = new LinkedList< Node>();
		getPath(root, p, path1);
		getPath(root, q, path2);
		//System.out.println(path1);
		//System.out.println(path2);
		return getLastCommonNode(path1,path2);
	}
	private static Node getLastCommonNode(LinkedList<Node> path1,LinkedList<Node> path2)
	{
		Node last = null;
		while(path1.getLast()!=null && path1.getLast()!=null)
		{
			Node node1 = path1.removeLast();
			Node node2 = path2.removeLast();
			if(node1.equals(node2))
				last = node1;
			else return last;
		}
		return null;
	}
	/**
	 * 获取节点路径
	 * @param root
	 * @param n
	 * @param path
	 * @return
	 */
	private static boolean getPath( Node root,  Node n, LinkedList< Node> path){
		if(root == null)
			return false;
		path.push(root);
		if(root.equals(n))
			return true;
		boolean found=false;
		if(root.leftChild!=null)
			found = getPath(root.leftChild,n,path);
		if(found==false && root.rightChild!=null)
			found = getPath(root.rightChild,n,path);
		if(found ==false)
			path.pop();
		return found;				
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root = LCA_Binary.construct();
		System.out.println(LCA_Binary.lca1(root, new Node(7), new Node(6)));
		System.out.println(LCA_Binary.lca2(root, new Node(7), new Node(6)));
		System.out.println(LCA_Binary.lca3(root, new Node(7), new Node(6)));
		//LinkedList<Node> path = new LinkedList<Node>();
		//LCA_Binary.getPath(root, new Node(8), path);
		//System.out.println(path);
	}

}
