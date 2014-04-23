package Microsoft;

import java.util.Scanner;

public class Main2 {

	public static boolean hasRepeated(int[] array, int start, int end)
	{
		int len = array.length;
		int s = start;
		for(int i=end;i<=len-(end-start);i++)
		{
			int curr = i;
			while(i<len && array[i]==array[start])
			{
				i++;
				start++;
				if(start==end)
					return true;
			}
			i=curr;
			start = s;
		}
		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] data = new int[N];
        int i =0;
        int max = 0;
        while(i<N)
        {
        	data[i++] = in.nextInt();
        }
        
        for(int len=1;len<=N/2;len++)
        {
        	for(int start = 0;start<N-2*len;start++)
        	{
        		boolean found = hasRepeated(data,start,start+len);
        		if(found == true)
        		{
        			if(len>max)
        				max = len;
        			break;
        		}
        			
        		
        	}
        }
		System.out.println(max);
	}

}
