package Source;

import java.util.ArrayList;

public class CTCI8_4_Permutations {

	/**
	 * 求全排列问题，为了简单起见，假设输入的0-n的n+1个数字的数组
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[]={1,2,3};
		ArrayList<ArrayList<Integer>> results=permutations(array);
		for(int i=0;i<results.size();i++)
		{
			System.out.println(results.get(i));
		}
	}
	public static ArrayList<ArrayList<Integer>> permutations(int []array)
	{
		int len=array.length;
		//不合法输入
		if(len<=0)
			return null;
		//基本问题的求解
		if(len == 1)
		{
			ArrayList<ArrayList<Integer>> unitPermu= new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> unit = new ArrayList<Integer>();
			unit.add(array[0]);
			unitPermu.add(unit);
			return unitPermu;
		}
		int []subArray=new int[len-1];
		for(int i=0;i<len-1;i++)
		{
			subArray[i] = array[i];
		}
		//得到子规模问题的解
		ArrayList<ArrayList<Integer>> subPermu=permutations(subArray);
		ArrayList<ArrayList<Integer>> permu = new ArrayList<ArrayList<Integer>>();
		int last = array[len-1];//当前需要被加入的最后一个元素
		//得到当前规模问题的解
		for(int i=0;i<subPermu.size();i++)
		{
			ArrayList<Integer> aSubPermu = subPermu.get(i);
			//往每个子排序的序列中添加当前这个last元素
			for(int j=0;j<aSubPermu.size()+1;j++)
			{
				ArrayList<Integer> aPermu = new ArrayList<Integer>();
				aPermu.addAll(aSubPermu);
				aPermu.add(j, last);
				permu.add(aPermu);
			}
			
		}
		return permu;
	}
}
