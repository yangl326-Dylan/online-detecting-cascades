package Source;

public class CTCI8_1_Fibonacci {

	/**
	 * write a method to generate the nth Fibonacci number
	 * 注意判断n的边界与有效性
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(fibonacci_mathMatrix(7));
		System.out.println(fibonacci_recurison(7));
		System.out.println(fibonacci_iteration(7));
	}
	/**
	 * 递归的方法，代码简单，但是复杂度高
	 * @param n
	 * @return
	 */
	public static int fibonacci_recurison(int n)
	{
		if(n<0)
			return -1;//Error condition
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		if(n==2)
			return 1;
		return fibonacci_recurison(n-1)+fibonacci_recurison(n-2);
	}
	/**
	 * 迭代的方法，用空间换取时间
	 * @param n
	 * @return
	 */
	public static int fibonacci_iteration(int n)
	{
		if(n<0)
			return -1;//Error condition;
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		int fib[]=new int[n+1];
		fib[0]=0;
		fib[1]=1;
		for(int i=2;i<n+1;i++)
			fib[i]=fib[i-1]+fib[i-2];
		return fib[n];
	}
	/**
	 * 这个是用的分治策略，时间复杂度是O(lgn)
	 * @param n
	 * @return
	 */
	public static int fibonacci_mathMatrix(int n)
	{
		if(n<0)
			return -1;//Error condition;
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		int [][] f= new int[2][2];
		int [][] cell={{1,1},{1,0}};
		f=matrix_power_n(cell,n);
		return f[0][1];
	}
	
	public static int[][] matrix_power_n(int [][] array,int n)
	{
		if(n==1)
			return array;
		int [][] temp=new int[2][2];
		int [][] cell={{1,1},{1,0}};
		temp=matrix_power_n(array,n/2);
		temp=matrix_multiple(temp,temp);
	    if(n%2==0)
	    {
	    	return temp;
	    }
	   // temp=matrix_power_n(array,n/2);
	    return matrix_multiple(temp,cell);
	}
	public static int[][] matrix_multiple(int [][] array,int [][] array1)
	{
		
		int [][] m=new int[2][2];
		m[0][0] = array[0][0]*array1[0][0]+array[0][1]*array1[1][0];
		m[0][1] = array[0][0]*array1[1][0]+array[0][1]*array1[1][1];
		m[1][0] = array[1][0]*array1[0][0]+array[1][1]*array1[1][0];
		m[1][1] = array[1][0]*array1[1][0]+array[1][1]*array1[1][1];
		return m;
	}
}
