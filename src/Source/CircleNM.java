package Source;
class circleNode{
	int data;
	circleNode next;
	public circleNode(int _data, circleNode n){
		data = _data;
		next = n;
	}
}
/**
 * 从0开始编号的地推公式：f(n,m) = [f(n-1,m)+m]%n ; f(1,m) = 0;
 * 从1开始编号的递推公式：f(n,m) = [f(n-1,m)+m -1]%n +1; f(1,m) = 1;
 * @author Echo
 *
 */
public class CircleNM {
	/**
	 * 约瑟夫问题的基本问题：n个人围成一圈，从0开始编号到n-1，从1报数到m，数到m的出队，然后从下一个开始数1，报到m的输出队列，
	 * 求最后一个人的编号。
	 * 这里采用数学方式f(n,m) = [f(n-1,m)+m]%n  O(n)
	 * 如果编号是从1开始，则最后输出的时候加1
	 * 采用递推的方法解决
	 * @param n
	 * @param m
	 * @return
	 */
	public static int firstMathMethod(int n,int m)
	{
		if(n<=0 || m<=0)
			return -1;//输入参数不对
		int lastVal = 0;
		for(int i=2;i<=n;i++)
			lastVal = (lastVal+m)%i;
		return lastVal;
	}
	public static int firstMathMethod2(int n,int m)
	{
		if(n<=0 || m<=0)
			return -1;//输入参数不对
		int lastVal = 1;
		for(int i=2;i<=n;i++)
			lastVal = (lastVal+m-1)%i +1;
		return lastVal;
	}
	/**
	 * 问题同上，但是用链表
	 * 这种方法可以输出依次出队的编号，目前我不知道除了模拟方法，还有什么更有效率的方法可以依次输出这个顺序？？
	 * 采用环模拟删除过程 O(nm)
	 * @param n
	 * @param m
	 * @return
	 */
	public static int secondLinkedListMethod(int n, int m)
	{
		//创建环形
		circleNode head = new circleNode(0,null);
		circleNode pointer =head;
		for(int i=1;i<n;i++)
		{
			pointer.next = new circleNode(i,null); 
			pointer = pointer.next;
		}
		pointer.next = head;
		//模拟删除过程
		pointer = head;
		int index = 1;
		while(pointer.next!=pointer)
		{
			if(index == m-1)
			{
				pointer.next = pointer.next.next;
				pointer = pointer.next;
				index = 1;
			}else
			{
				index++;
				pointer =  pointer.next;
			}
		}
		return pointer.data;
		
	}
	
	/**
	 * 这算是约瑟夫问题的一个扩展：n 个人按顺时针围成一圈从1开始按顺序顺序编号，首先先把第m号的人出队，
	 * 然后从m+1号开始按1、2、3 、....、k按顺时针报数，报 k 者退出圈外，
	 * 其余的人再从1、2、3 、....、k 报数，报 k 的人再退出圈外，依次类推。请输出最后一个人的原序号。
	 * 其实整个思维过程和基本问题是一制的。
	 * @param n 如果编号从1开始，则 最后输出的数+1 即可
	 * @param k
	 * @param m
	 * @return
	 */
	public static int genericNMK(int n, int k, int m)
	{
		if(n<=0 || m<=0)
			return -1;//输入参数不对
		int lastValue = 0;
		for(int i=2;i<n;i++)
			lastValue = (lastValue + k)%i;
		return (lastValue +m)%n;
	}
	/**
	 * 另外一种数学递推公式
	 * @param n
	 * @param m
	 * @return
	 */
	public static int mn(int n, int m)
	{
		if(n<=0 || m<=0)
			return -1;//输入参数不对
		int lastValue = 1;
		
		for(int i=2;i<=n;i++)
		{	
			int delta = m%i;
			lastValue = (lastValue + delta-1)%i +1;
		}
		return lastValue;
	}
	/**
	 * 另外一种地推公式 从1开始编号，其实就是f(n,m) = [f(n-1,m)+m-1]%i +1 ;
	 * 同 firstMathMethod2
	 * @param n
	 * @param m
	 * @return
	 */
	public static int mns(int n, int m)
	{
		if(n<=0 || m<=0)
			return -1;//输入参数不对
		int lastValue = 1;
		
		for(int i=2;i<=n;i++)
		{	
			int delta = m%i;
			lastValue = (lastValue + delta-1)%i +1;
		}
		return lastValue;
	}
	/**
	 * 扩展问题2：第P轮出圈的编号，这个r初始的时候为什么是0，我不懂？？？
	 * 这里是从1开始编号
	 * @param n
	 * @param m
	 * @param p
	 * @return
	 */
	public static int NMP(int n,int m, int p)
	{
		int r = 0;
		for(int i=n-p+1;i<=n;i++)
		{
			r = (r+m-1)%i +1;
		}
		return r;
	}
	/**
	 * 扩展问题3，首次出队的报道数和之后的不同
	 * @param n 从1开始编号到n
	 * @param m 除了首次外都按照
	 * @param k 首次出队的编号是报道k的
	 * @param p 第p个出圈
	 * @return
	 */
	public static int NMKP(int n,int m,int k, int p)
	{
		int r = 0;
		for(int i=n-p+1;i<n;i++)
		{
			r = (r+m-1)%i +1;
		}
		return (r+k-1)%n+1;
	}
	/**
	 * 一种特殊情况下，即m=2时，可以得到O（logN）的算法
	 * 可以再想下直接迭代怎么做？？
	 * 采用递归的方式解决上面的特殊问题
	 * @param n
	 * @return
	 */
	public static int N2recu(int n)
	{
		if(n<=0)
			return -1;
		if(n == 1)
			return 1;
		if(n%2 == 0)
			return 2*N2recu(n/2)-1;
		else
			return 2*N2recu(n/2)+1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(firstMathMethod(10,11));
		//System.out.println(firstMathMethod2(10,11));
		//System.out.println(secondLinkedListMethod(8,3));
		
		System.out.println(N2recu(11));
	}

}
