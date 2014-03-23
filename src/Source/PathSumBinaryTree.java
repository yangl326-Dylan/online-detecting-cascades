package Source;

import java.util.LinkedList;

public class PathSumBinaryTree {

	/**
	 * 其实就是深度优先搜索
	 * @param root
	 * @param curr
	 * @param sum
	 * @param path
	 */
	public static void pathSum(Node root, int curr, int sum, LinkedList<Node> path){
		if(root == null )
			return ;
		path.push(root);
		if(root.leftChild == null && root.rightChild==null)
		{
			//curr+=root.data;
			if(curr+root.data == sum)
			{
				System.out.println(path);//输出打印的路径 反了
				//return;
			}
		}
		
		//if(root.leftChild!=null)		
			pathSum(root.leftChild,curr+root.data,sum,path);
		//if(root.rightChild!=null)
			pathSum(root.rightChild,curr+root.data,sum,path);
		path.pop();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Node root = LCA_Binary.construct();
		Node root = LCA_Binary.construct1();
		pathSum(root,0,22, new LinkedList<Node>());
	}

}
