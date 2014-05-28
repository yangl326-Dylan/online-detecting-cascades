package leetcode;

import java.util.HashMap;

import javax.xml.stream.events.StartDocument;

public class Solution15 {
	/**
	 * string to int
	 * @param str
	 * @return
	 */
	public int atoi(String str){
		//input is null
		if(str == null || str.length() <=0 )
			return 0;
		char[] c = str.toCharArray();
		int INT_MAX = 2147483647;
		int INT_MIN = -2147483648;
		int result = 0;
		boolean flag = false;
		char sign = '+';
		for(int i=0;i<c.length;i++){
			//ignore the whitespaces in the beginning
			if(c[i]==' ' && flag == false)
				continue;
			//come to the real beginning of the numerical characters
			if(flag == false)
			{
				flag = true;
				if(!(((c[i] == '+' || c[i] == '-') && i+1<str.length() && c[i+1]>='0' && c[i+1]<='9' ) 
						||  (c[i]>='0' && c[i]<='9')))
					return 0;
				if(c[i] == '-' || c[i]=='+')
				{
					sign = c[i];
					continue;
				}
				
			}
			if(c[i]>='0' && c[i]<='9'){
				if(sign=='+' && result <= (INT_MAX-c[i]-'0')/10)
				{
					result = result*10+c[i]-'0';	
					//return INT_MAX;
				}
				else if(sign=='-' && result >= (INT_MIN+(c[i]-'0'))/10)
				{
					result = result*10+ (c[i]-'0')*(-1);
				}else {
					return sign == '-' ? INT_MIN : INT_MAX;
				}
				//result = result*10+c[i]-'0';
			}else{
				break;
			}
			
		}
		return  result;
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
		//贪心算法
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
	 * Determine whether an integer is a palindrome. Do this without extra space.
	 * @param x
	 * @return
	 */
	public boolean isPalindrome(int x) {
		if(x<0)
			return false;
        long reverse = 0;
        int o = x;
        while(x>0){
        	reverse = reverse*10+x%10;
        	x = x/10;
        }
        if(o == reverse)
        	return true;
        return false;
    }
	/**
	 * 
	 * by dp solution
	 * unsolved
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p){
		if(p == null || p.length() == 0)
			return false;
		if(s == null || s.length() == 0)
			return true;
		char[] sc = s.toCharArray();
		char[] pc = p.toCharArray();
		boolean [][] dp = new boolean[s.length()][p.length()];
		int last = -1, cur = -1;
		//initialize dp
		if(sc[0] == pc[0] || pc[0] == '.')
			dp[0][0] = true;
		else 
			dp[0][0] = false;
		
		for(int i=1;i<s.length();i++)
			dp[i][0] = false;
		for(int j=1;j<p.length();j++)
			if(dp[0][j-1] == true || sc[0] == pc[j])
				dp[0][j] = true;
			else
				dp[0][j] = false;
		for(int i=1;i<s.length();i++){
			cur = -1;
			for(int j=1;j<p.length();j++){
				//case 1 if s[0,i] has already been matched by p[0,j-1]
				if(dp[i][j-1] == true)
				{
					dp[i][j] = true;
					continue;
				}
				//case 2 come to the '.'
				if(pc[j] == '.' && dp[i-1][j-1]==true)
				{
					dp[i][j] = true;
					continue;
				}
				//case 3 come to the '*'
				if(pc[j] == '*' &&  dp[i-1][j-1] == true &&
						(sc[i] == pc[j-1] || (pc[j-1] == '.') ) ){
					dp[i][j] = true;
					continue;
				}
				//case 4 the normal character
				if(dp[i-1][j-1] == true && pc[j] == sc[i])
				{
					dp[i][j] = true;
					continue;
				
				}
				dp[i][j] = false;	
				}
			//last = cur;
			}
		return dp[s.length()-1][p.length()-1];
		}
	/**
	 * 将一个int型转成rome数字型
	 * @param num
	 * @return
	 */
	public  String intToRoman(int num) {
        String[] dic ={" ", "I" , "II" ,"III", "VI","V","IV","IIV","IIIV","XI",
        		" ", "X","XX","XXX","LX","L","XL","XXL","XXXL","CX",
        		" ", "C","CC","CCC","DC","D","CD","CCD","CCCD","MC",
        		" ", "M","MM","MMM"};
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int t = num;
        while(t>0){
        	int r = t%10;
        	t = t/10;
        	if(r == 0)
        	{
        		i++;
        		continue;
        	}
        	int index = i*10 + r;
        	sb.append(dic[index]);
        	i++;
        }
        char[] ar = sb.toString().toCharArray();
        sb = new StringBuilder();
        //reverse the result
        for(i=ar.length-1;i>=0;i--){
        	sb.append(ar[i]);
        }
        return sb.toString();
    }
	public int romanToInt(String s){
		HashMap<String, Integer> kv = new HashMap<String,Integer>();
		kv.put("I", 1);
		kv.put("II", 2);
		kv.put("III", 3);
		kv.put("IV", 4);
		kv.put("V", 5);
		kv.put("VI", 6);
		kv.put("VII", 7);
		kv.put("VIII", 8);
		kv.put("IX", 9);
		
		kv.put("X", 10);
		kv.put("XX", 20);
		kv.put("XXX", 30);
		kv.put("XL", 40);
		kv.put("L", 50);
		kv.put("LX", 60);
		kv.put("LXX", 70);
		kv.put("LXXX", 80);
		kv.put("XC", 90);
		
		kv.put("C", 100);
		kv.put("CC", 200);
		kv.put("CCC", 300);
		kv.put("CD", 400);
		kv.put("D", 500);
		kv.put("DC", 600);
		kv.put("DCC", 700);
		kv.put("DCCC", 800);
		kv.put("CM", 900);
		
		kv.put("M", 1000);
		kv.put("MM", 2000);
		kv.put("MMM", 3000);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution15 s = new Solution15();
		System.out.println(s.intToRoman(1904));
	}

}
