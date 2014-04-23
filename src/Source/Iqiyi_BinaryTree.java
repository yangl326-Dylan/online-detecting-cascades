package Source;

public class Iqiyi_BinaryTree {

	static int Max=0;
	static int Min=0;
	
	public static void maxMin(Node root){
		if(root==null)
			return;
		if(root.leftChild==null && root.rightChild == null)
		{
			if(root.data>Max)
				Max = root.data;
			if(root.data<Min)
				Min = root.data;
	
		}
		if(root.leftChild!=null)
		{
			root.leftChild.data = root.data-1;
			maxMin(root.leftChild);
		}
		if(root.rightChild!=null)
		{
			root.rightChild.data = root.data+1;
			maxMin(root.rightChild);
		}		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		maxMin(LCA_Binary.construct());
		System.out.println(Max-Min);
	}

}
