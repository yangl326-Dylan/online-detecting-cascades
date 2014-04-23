package Source;

public class FromOne2NSum {

	static int i=1;
	static int Sum=0;
	
	static boolean get(int n)
	{
		Sum+=i++;
		return (i<=n)&&get(n);//利用布尔运算的特点终止递归
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//FromOne2NSum[] tables = new FromOne2NSum[10];//C++可行 对Java不行
		//System.out.println(FromOne2NSum.getSum());
		get(10);
		System.out.println(Sum);
	}

}
