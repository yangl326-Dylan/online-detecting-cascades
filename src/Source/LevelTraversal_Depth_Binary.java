package Source;

import java.util.LinkedList;

public class LevelTraversal_Depth_Binary {

	/**
	 * 层次遍历
	 * @param root
	 */
	public static void levelTra(Node root){
		LinkedList<Node> queue = new LinkedList<Node>();
		Node pointer;
		int start =1; //记录每层开始的位置
		int end = 0; //记录每层结束的位置
		int pop = 0;//记录被弹出的个数
		queue.offer(root);
		while(!queue.isEmpty())
		{
			if(pop==start)
			{
				start = end+1;
				System.out.println();
			}
			pointer = queue.remove();
			pop++;			
			System.out.print(pointer.data+" ");
			
			if(pointer.leftChild!=null)
			{
				queue.offer(pointer.leftChild);
				end++;
			}
			if(pointer.rightChild!=null)
			{
				queue.offer(pointer.rightChild);
				end++;
			}
			
		}
	}
	/**
	 * 求树的深度，采用递归方式
	 * @param root
	 * @return
	 */
	public static int depth(Node root){
		if(root ==null)
			return 0;
		return Math.max(depth(root.leftChild)+1, depth(root.rightChild)+1);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//levelTra(LCA_Binary.construct());
		System.out.println(depth(LCA_Binary.construct()));
	}

}
