package Microsoft;

import java.util.HashMap;
import java.util.Scanner;

public class Main1 {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner in = new Scanner(System.in);
		 int caNum = 0;
		 caNum = in.nextInt();
	      for(int i=0;i<caNum;i++) {
	        	int N = in.nextInt();
	        	int M = in.nextInt();
	        	HashMap<String,String> con= new HashMap<String,String>();
	        	for(int j=0;j<M;j++)
	        	{
	        		String a = in.next();
	        		String b = in.next();
	        		con.put(a, a);
	        	}
	        	//in.n
	        	//String [] sen = in.nextLine().split(" ");
	        	while(!in.hasNextInt())
	        	{
	        		
	        	}
	        	for(int k=1;k<N;k++)
	        	{
	        		for(int x=0;x<sen.length;x++)
	        		{
	        			if(con.containsKey(sen[x]))
	        			{
	        				sen[x] = con.get(sen[x]);
	        			}
	        		}
	        		
	        	}
	        	System.out.print("Case #"+caNum+1+":");
	        	for(int y=0;y<sen.length;y++)
	        	{
	        		System.out.print(sen[y]+" ");
	        	}
	        	System.out.println();
	        }
	}

}
