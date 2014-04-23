package Source;

public class Find {

	public static char findFirstOne(char[] data){
		int[] cnt = new int[256];
		for(int i=0;i<data.length;i++)
		{
			cnt[data[i]]++;
		}
		for(int i=0;i<data.length;i++)
		{
			if(cnt[data[i]] == 1)
				return data[i];
		}
		return ' ';
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="abaccdeff";
		char[] data = str.toCharArray();
		System.out.println(findFirstOne(data));
	}

}
