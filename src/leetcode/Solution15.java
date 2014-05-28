package leetcode;

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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution15 solution15 = new Solution15();
		//System.out.println(solution15.atoi("-2147483647"));
		//System.out.println(solution15.isPalindrome(1));
		System.out.println(solution15.isMatch("aaba", "ab*a*c*a"));
	}

}
