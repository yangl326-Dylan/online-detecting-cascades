package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Point {
	int x;
	int y;
	Point(){x = 0;y = 0;}
	Point(int a,int b){x =a; y =b;}
}
public class Unsolutioned {

	/**
	 * Max Points on a Line 
	 * 这道题没有解出来 点坐标相同的情况没有考虑
	 * 问题是一步一步解决的。
	 * @param points
	 * @return
	 */
	 public int maxPoints(Point[] points) {
		 Map<String, Integer> lineCnt = new HashMap<String, Integer>();
		 Map<String, Integer> keyLabel = new HashMap<String, Integer>();
		 Map<Point,Integer> pointCnt = new HashMap<Point,Integer>(); //去掉重复的点
		 int [] rep = new int[points.length];
		 int id = 0; 
		 final int lineNum = 6220;
		 int max = 0;
		 int[][] label = new int[lineNum][points.length];//这个用作标记某个点是否已经被计数到某条直线上
		 //初始化
		/** for(int i=0;i<lineNum;i++)
			 for(int j=0;j<points.length;j++)
				 label[i][j] = 0;
		 **/
		 if(points.length == 1) //当输入只有1个点时，输出1个点
			 return 1;

		 //这两重for循环只是为了计算所有直线
		 //key采用字符串的形式，是为了containsKey()可以用
		 for(int i=0;i<points.length-1;i++)
		 {
			 for(int j = i+1;j<points.length;j++)
			 {
				 String kb = new String();
				 if(points[i].x == points[j].x && points[i].y == points[j].y)
					 
				 if(points[i].x == points[j].x)
					 kb = "POSITIVE_INFINITY ";
				 else{
					  Double k = (points[i].y-points[j].y)*1.0/(points[i].x-points[j].x);
					  if(Math.abs(k) <=Double.MIN_NORMAL)
						  kb = "0";
					  else
						  kb = k.toString();
				 }
				 if(points[i].x == points[j].x)
					 kb += ((Double)(points[i].x*1.0)).toString();
				 else{
					 Double b = 1.0*(points[i].x*points[j].y - points[j].x*points[i].y)/(points[i].x - points[j].x);
					  if(Math.abs(b) <=Double.MIN_NORMAL)
						  kb += "0";
					  else
						  kb += b.toString();
				 }
					 //System.out.println(kb[0]+fac);
				 if(lineCnt.containsKey(kb))
				 {
					 int currId = keyLabel.get(kb);
					 if(label[currId][i] == 0 && label[currId][j] == 0)
					 {
						 int t = lineCnt.get(kb);
						lineCnt.put(kb, t+2);
						label[currId][j] = 1;
						 if(t+2>max)
							max = t+2; 
					 }
					 else if(label[currId][i] == 0 || label[currId][j] == 0)
					{	
						int t = lineCnt.get(kb);
						lineCnt.put(kb, t+1);
						label[currId][j] = 1;
						if(t+1>max)
							max = t+1;
					}
				 }
				 else
				 {
					 lineCnt.put(kb, 2);
					 //下面的处理是为了把某条线映射到一个编号id,然后为i和jpoint做标记，表示i，j已经在id线上计数过了
					 keyLabel.put(kb, id);
					 label[id][i] = 1;
					 label[id][j] = 1;
					 id++;
				 }
			 }
		 }
		 return max;
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
