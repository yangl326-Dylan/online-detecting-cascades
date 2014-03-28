package Source;

class BiNode{
	int data;
	BiNode next;
	BiNode pre;
	public BiNode(int _data, BiNode _next, BiNode _pre){
		data = _data;
		next = _next;
		pre = _pre;
	}
	public String toString()
	{
		return data+" ";
	}
}
public class BinarySearchTree1 {
	/**
	 * 判断整数序列是不是二元查找树的后续遍历
	 * @author Echo
	 *
	 */
	public static boolean isPost(int[] array, int start, int end){
		if(start == end)
			return true;
		int i=start;
		int j=end-1;
		while(array[i]<array[end])
			i++;
		while(array[j]>array[end])
			j--;
		if(i == j+1)
		{
			boolean f1=true;
			boolean f2 = true;
			if(start<=j)
			   f1=isPost(array,start,j);
			if(i<=end-1)
				f2=isPost(array,i,end-1);
			return f1&&f2;
		}
		return false;
	}
	
	public static void mirror(Node root){
		if(root == null)
			return;
		if(root.leftChild!=null && root.rightChild!=null)
		{
			Node temp = root.leftChild;
			root.leftChild = root.rightChild;
			root.rightChild = temp;
			mirror(root.leftChild);
			mirror(root.rightChild);
		}
		if(root.leftChild!=null && root.rightChild == null)
		{
			root.rightChild = root.leftChild;
			root.leftChild = null;
			mirror(root.rightChild);
		}
		if(root.leftChild==null && root.rightChild!=null)
		{
			root.leftChild = root.rightChild;
			root.rightChild = null;
			mirror(root.leftChild);
		}
	}
	/**
	 * 求二叉树的镜像
	 * @param root
	 */
	public static void mirror2(Node root)
	{
		if(root == null)
			return;
		mirror2(root.leftChild);
		mirror(root.rightChild);
		Node temp = root.leftChild;
		root.leftChild = root.rightChild;
		root.rightChild = temp;
	}
	
	public static BiNode binarySearch2BiList(Node root)
	{
		if(root == null)
			return null;
		BiNode before = binarySearch2BiList(root.leftChild);
		BiNode after = binarySearch2BiList(root.rightChild);
		BiNode pointer = before;
		BiNode curr = new BiNode(root.data,after,null);
		if(after!=null)
			after.pre = curr;
		if(before == null)
		{
			return curr;
		}
		while( pointer.next!=null)
			pointer = pointer.next;
		pointer.next = curr;
		curr.pre = pointer;
		return before;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int array[] = {1,4,3,2,6,7,5,11,14,13,16,18,17,15,10};
		//int array[] = {1,4,3,2,6,7,5,11,14,13,16,18,17,15,16};
		//System.out.println(isPost(array,0,14));
		Node root = LCA_Binary.construct2();
		BiNode head = binarySearch2BiList(root);
		BiNode pointer = head;
		
		while(pointer!=null){
			System.out.print(pointer);
			pointer = pointer.next;
		}
		System.out.println();
		pointer= head;
		while(pointer.next!=null)
			pointer = pointer.next;
		while(pointer!=null)	
		{
			System.out.print(pointer);
			pointer = pointer.pre;
		}
		
		//LevelTraversal_Depth_Binary.levelTra(root);
		//BinaryTraversal.inOrder(root);
		//System.out.println();
		//mirror(root);
		//mirror(root);
		//LevelTraversal_Depth_Binary.levelTra(root);
		//BinaryTraversal.inOrder(root);
	}

}
