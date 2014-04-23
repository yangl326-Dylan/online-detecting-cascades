package Source;

public class ReverseSentence {

	/**
	 * 逆转一个char数组中[start,end)部分
	 * @param str
	 * @param start
	 * @param end
	 */
	public static void reverse(char[] str, int start, int end){
		
		for(int i=start;i<(start+end)/2;i++)
		{
			char temp = str[i];
			str[i] = str[end-(i-start)-1];
			str[end-(i-start)-1] = temp;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="I am a student.";
		char[] array = str.toCharArray();
		//先把整个句子的所有单词逆转
		reverse(array,0,array.length);
		System.out.println(array);
		//对逆转后的句子的每个单词再逆转
		for(int i=0;i<array.length;i++)
		{
			int mark=i;
			while(i<array.length && array[i]!=' '  )
			{
				i++;
			}
			reverse(array,mark,i);			
		}
		System.out.println(array);
	}

}
