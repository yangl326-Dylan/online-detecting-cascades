package Source;

import java.util.ArrayList;

public class CTCI8_3_Subsets {

	//the size of the set
	public static int N=3;
	/**
	 * Write a method that returns all subsets of a set.
	 * in this code, we assume that the elements is 0 to n-1
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Integer>> result= getAllSubsets(0);
		for(int i=0;i<result.size();i++)
		{
			System.out.println(result.get(i));
		}
		
	}

	public static ArrayList<ArrayList<Integer>> getAllSubsets(int index)
	{
		ArrayList<ArrayList<Integer>> resultNew = new ArrayList<ArrayList<Integer>>();

		if(index==N-1)
		{
			ArrayList<Integer> subset= new ArrayList<Integer>();
			subset.add(N-1);
			resultNew.add(subset);
			return resultNew;
		}
		
		ArrayList<ArrayList<Integer>> results = getAllSubsets(index+1);
		int size=results.size();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(index);
		resultNew.add(temp);
		//把小规模的所有subset复制到新的结果集中
		for(int i=0;i<size;i++)
		{
			resultNew.add(results.get(i));
		}
		
		//将所有小规模的subsets中添加一个index元素
		for(int i=0;i<size;i++)
		{
			ArrayList<Integer> temp1=new  ArrayList<Integer>();
			temp1.addAll(results.get(i));
			temp1.add(index);
			resultNew.add(temp1);
		}
		
		return resultNew;
	}
}
