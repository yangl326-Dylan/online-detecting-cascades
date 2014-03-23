package Source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MajorElement {

	/**
	 * 方法1采用递归的方法找到主元素，但是需要保留栈信息
	 * @param array
	 */
	public static Integer recusionMethod1(List<Integer> array)
	{
		if(array.size() == 1)
			return array.get(0);
		List<Integer> subArray = new ArrayList<Integer>();
		for(int i=0;i<array.size();i=i+2)
		{
			//如果两个元素相同，保留其中之一，否则都去掉
			if(array.get(i)==array.get(i+1))
				subArray.add(array.get(i));
		}
		if(array.size()%2 !=0)
			subArray.add(array.get(array.size()-1));
		return recusionMethod1(subArray);
	}
	/**
	 * 采用主元素在数组中出现次数的特性
	 * @param array
	 */
	public static int propertyMethod2(int[] array)
	{
		int currData = 0;
		int retainNum = 0;
		for(int data: array)
		{
			if(retainNum == 0)
			{
				currData = data;
				retainNum = 1;
			}
			if(data == currData)
				retainNum ++;
			else
				retainNum --;
		}
		return currData;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a1 = {1,3,5,1,1,1};
		int[] a2 = {1,3,1,1};
		int[] a3 = {1};
		ArrayList<Integer> al1 = new ArrayList<Integer>();
		ArrayList<Integer> al2 = new ArrayList<Integer>();
		ArrayList<Integer> al3 = new ArrayList<Integer>();
		for(int data:a1)
			al1.add(data);	
		for(int data:a2)
			al2.add(data);
		for(int data:a3)
			al3.add(data);
		System.out.println(recusionMethod1(al1));
		System.out.println(recusionMethod1(al2));
		System.out.println(recusionMethod1(al3));
		System.out.println(propertyMethod2(a1));
		System.out.println(propertyMethod2(a2));
		System.out.println(propertyMethod2(a3));
	}

}
