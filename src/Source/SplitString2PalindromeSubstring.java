package Source;

public class SplitString2PalindromeSubstring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//splitString2PalindromeSubstring("habbaffabba");
		splitString2PalindromeSubstring("abcdefg");
		
	}
	/**
	 * 将一个很长的字符串，分割成一段一段的子字符串，子字符串都是回文字符串。
	 * 有回文字符串就输出最长的，没有回文就输出一个一个的字符。
	 * @param str 输入的字符串
	 */
	public static void splitString2PalindromeSubstring(String str){
		int size=str.length();
		//S[i]表示以str的第（i+1）个字符为结尾时，最长的回文子串
		int S[]=new int[size];
		
		S[0]=1;
		for(int i=1;i<size;i++)
		{
			//比较当前字符与有可能成为回文的对应位置的字符，如果相同，回文长度增长2，否则，以当前字符为结尾的回文长度为1
			char a=' ';
			if(S[i-1]==1)
				a=str.charAt(i-S[i-1]);
			else
				a=str.charAt(i-S[i-1]-1);
			char b=str.charAt(i);
			if(a==b)
			{
				if(S[i-1]==1)
					S[i]=S[i-1]+1;
				else
					S[i]=S[i-1]+2;
			}else
			{
				S[i]=1;
			}
		}
		//打印出分割的子串,从后往前打印
        int i=size-1;
        while(i>=0)
        {
        	if(S[i]==1)
        		System.out.println(str.charAt(i));
        	else
        		System.out.println(str.substring(i-S[i]+1, i+1));
        	i=i-S[i];
        }
		
	}

}
