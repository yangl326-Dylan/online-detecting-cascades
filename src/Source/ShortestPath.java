package Source;

import java.util.LinkedList;
import java.util.List;

class BNode{
	int data;
	int dx;
	BNode pre;
	public BNode(int _data, int _dx, BNode _pre){
		data = _data;
		dx = _dx;
		pre =_pre;
	}
	public String toString(){
		if(pre!=null)
			return dx+", "+pre.toString();
		return" ";
	}
}
public class ShortestPath {

	public static BNode shortPathBreadthFirst(int A, int B){
		int[] branches = {12,-12,7,-7,5,-5};
		LinkedList<BNode> queue = new LinkedList<BNode>();
		BNode t = null;
		queue.offer(new BNode(A,0,null));//queue把根节点放入
		while(!queue.isEmpty())
		{
			t = queue.remove();
			if(t.data==B)
				break;
			for(int i=0;i<6;i++)
			{
				queue.offer(new BNode(t.data+branches[i],branches[i],t));
			}
		}
		System.out.println(t);
		return t;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		shortPathBreadthFirst(3,36);
	}

}
