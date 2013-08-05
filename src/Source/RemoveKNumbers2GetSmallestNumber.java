package Source;

import java.math.MathContext;

public class RemoveKNumbers2GetSmallestNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[]={9,5,1,2,4,6,8,7,3};
		removeKNumbers2GetSmallestNumber(array,5);
	}
	/**
	 * 给定一个N位数，例如12345，从里面去掉k个数字，得到一个N-k位的数，
	 * 例如去掉2，4，得到135，去掉1，5，得到234。设计算法，求出所有得到的
     * N-k位数里面最小的那一个
	 * @param array 输入的数据
	 * @param K 用户指定的
	 * 算法复杂度是O(m*(n-K))
	 */
	public static void removeKNumbers2GetSmallestNumber(int [] array, int K){
		int len = array.length;
		//flag数组初始时都会被置为0
		int flag [] =new int[len];
		//M是生成的数的位数
		int M=len-K;
		
		int beg=0;
		int end=len-M;
		int min=10000;
		int ind=-1;
		
		for(int i=0;i<M;i++)
		{
			//找到每一位有可能所在的范围最小的数
			for(int j=beg;j<=end;j++)
			{
				if(array[j]<min)
				{
					min=array[j];
					ind=j;
				}
			}
			//更新变量
			flag[ind]=1;//把保留的位置1
			beg=ind+1;
			end=len-M+i+1;
			min=10000;
			ind=-1;
			
		}
		//打印出被保留的
		for(int i=0;i<len;i++)
		{
			if(flag[i]==1)
			{
				System.out.print(array[i]);
			}
		}
	}
}
