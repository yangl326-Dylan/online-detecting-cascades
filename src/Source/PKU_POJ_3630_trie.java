package Source;

import java.io.IOException;
import java.util.ArrayList;

public class PKU_POJ_3630_trie {

	//branches
	public static int N=10;
	//内部类，节点类
	class NODE{
		boolean isStr = false;
		boolean isPre = false;
		//ArrayList<NODE> branch = new ArrayList<NODE>();	
	    NODE [] branches = new NODE[N];
	}
	
	public NODE root = new NODE();//一个根节点
	/**
	 * 建一个layer层的trie树
	 * @param layer
	 */
/*	public void buildTrie(int layer, NODE root)
	{
		if(layer==0)
		{
			root.branch = null;
			return;
		}
		for(int i=0;i<N;i++)
		{
			NODE node = new NODE();
			root.branch.add(node);
			buildTrie(layer-1,node);
		}
	}*/
	/**
	 * 插入一个序列
	 * 每次插入就可以检查是否
	 * @param array
	 */
	public void insert(ArrayList<Integer> array)
	{
		NODE temp = new NODE();
		temp = root;
		for(int i=0;i<array.size();i++)
		{
			if(temp.branches[array.get(i)]==null)
			{
				NODE next = new NODE();
				temp.branches[array.get(i)]=next;
			}
			if(i<array.size()-1)
				temp.branches[array.get(i)].isPre = true;
			temp=temp.branches[array.get(i)];
			//temp = temp.branch.get(array.get(i));
		
		}
		temp.isStr = true;
		//temp.isPre = false;
	}

	/**
	 * 判断某个序列是否为其他序列的前缀
	 * @param array
	 * @return 是某个序列的前缀返回yes
	 * 
	 */
	public boolean isPrefix(ArrayList<Integer> array)
	{
		NODE temp = new NODE();
		temp = root;
		for(int i=0;i<array.size();i++)
		{
			temp = temp.branches[array.get(i)];
			if(temp.isPre==true)
				continue;
			else
				return false;
		}
		return true;
		
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PKU_POJ_3630_trie trie = new PKU_POJ_3630_trie();
		//trie.buildTrie(10, trie.root);
		int [] array= {113,12340,113440,12345,98346};
		
		for(int i=0;i<array.length;i++)
		{
			int temp=array[i];
			ArrayList<Integer> arrL=new ArrayList<Integer>();
	
			while(temp>0)
			{
				int num= temp%10;
				int rest=temp/10;
				arrL.add(0,num);
				temp = rest;
			}
			trie.insert(arrL);
		}
		
		for(int i=0;i<array.length;i++)
		{
			int temp=array[i];
			ArrayList<Integer> arrL=new ArrayList<Integer>();
	
			while(temp>0)
			{
				int num= temp%10;
				int rest=temp/10;
				arrL.add(0,num);
				temp = rest;
			}
			boolean b = trie.isPrefix(arrL);
			if(b==false)
			{
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

}
