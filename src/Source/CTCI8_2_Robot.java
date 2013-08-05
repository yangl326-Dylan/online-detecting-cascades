package Source;

public class CTCI8_2_Robot {

	/**Imagine a robot sitting on the upper left hand corner of an NxN grid. 
	 * The robot can only move in two directions: 
	 * right and down. How many possible paths are there for the robot?
	 * @param args
	 */
	public static int N=4;
	public static int N2=4;
	public static int [][]grid = new int[N][N2];
	//public static int deep=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		robot(0,0,0);
	}
	/**
	 * 采用宽度优先算法
	 * 打印格式成树状
	 * @param row
	 * @param col
	 * @param deep 表示当前深度
	 */
	public static void robot(int row,int col,int deep){
	
		//这些很关键，对没一层的公共资源要确保不会被改变
		int temp1=deep;
		int temp2=deep;
		int row1=row;
		int row2=row;
		int col1=col;
		int col2=col;
		//当还可以向下走的时候
		if(row<N-1)
		{
			for(int i=0;i<deep;i++)
				System.out.print("	");
			System.out.println("down");
			//int rowt=row++;
			
			robot(++row1,col1,++temp1);
		}
		//判断是否还可以向右走
		if(col<N2-1)
		{
			for(int i=0;i<deep;i++)
				System.out.print("	");
			System.out.println("right");
			//int temp2=deep+1;
			robot(row2,++col2,++temp2);
		}
		//深度加一
		//deep++;
		
	}
}
