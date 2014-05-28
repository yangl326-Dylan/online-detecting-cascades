package leetcode;

public class Solution15 {
	/**
	 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
		
	}
	 /**
		 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
		 *  n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and 
		 * (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
		 * this solution will get TLE
		 * @param height
		 * @return
		 * @author Administrator
		 * @date 2014-5-27
		 */
		public int maxArea(int[] height){
			//can't form a container
			if(height == null || height.length<=1)
				return 0;
			int len = height.length;
			int[] left = new int[len];//left[i] is to record the largest distance from the left side to the ith line, and a[left[i]]>=a[i]
			int[] right = new int[len];//similar to left, but from the right side
			//specially handler for ascending or descending array
			boolean flag = false;
			int area = height[len-1]*(len-1);
			//the descending one
			for(int i=len-2;i>=0;i--){
				if(height[i]<height[i+1]){
					flag = true;
					break;
				}else{
					if(height[i]*i>area)
						area = height[i]*i;
				}
			}
			if(flag == false)
				return area;
			//ascending one
			flag = false;
			area = height[0]*(len-1);
			for(int i =1;i<len;i++){
				if(height[i]<height[i-1])
				{
					flag = true;
					break;
				}else{
					if(height[i]*(len-1-i)>area)
						area = height[i] * (len-1-i);
				}
			}
			if(flag == false)
				return area;
			//caculate the left array
			left[0] = 0;
		
			for(int i=1;i<len;i++){
				int index = i-1;
				left[i] = 0;
				while(index>=0){
					if(height[index] >= height[i]){//it can make sure that height[left[index]]>=a[i]
						left[i] = (i-index);
						if(left[index]==0)
							index --;
						else
							index -= left[index];
					}else{//to check the next left one
						index -- ;
					}
				}
			}
			//caculate the right array
			right[len-1] = 0;
			
			for(int i = len-2;i>=0;i--){
				int index = i+1;
				right[i] = 0;
				while(index<len){
					if(height[index]>height[i]){
						right[i] = index-i ;
						if(right[index]==0)
							index ++;
						else
							index += right[index];
					}else{
						index++;
					}
				}
			}
			
			//caculate the largest area
			int max = 0;
			for(int i=0;i<len;i++){
				int temp = (left[i]+right[i])*height[i];
				if(temp>max)
					max = temp;
			}
			return max;
		}
		//Ì°ÐÄËã·¨
		public int maxArea2(int[] height){
			int left = 0, right = height.length-1;
				int max = 0;
				while(left<right){
					max = Math.max(max, (right-left)*(Math.min(height[left], height[right]))); 
					if(height[left]<height[right])
					{
						left++;
					}else
						right--;
				}
				return max;
		}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
"test"
	}

}
