package Source;

public class SubArraySumMax {
	/**
	 * 返回子数组和最大的和，使用动态规划
	 * @param array
	 * @return
	 */
	public static int subSumMax(int [] array)
	{
		if(array.length<=0)//输入参数的检查
		{
			System.out.println("输入无效");
			return -1;
		}
		int[] f = new int[array.length];
		f[0]=array[0];
		for(int i=1;i<array.length;i++)
		{
			if(f[i-1]>0)
				f[i] = f[i-1]+array[i];
			else
				f[i] = array[i];
		}
		int max = 0;
		for(int sum : f)
		{
			if(sum>max)
				max = sum;
		}
		return max;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] array={1,-2,3,10,-4,7,2,-5};
		System.out.println(subSumMax(array));
	}

}
