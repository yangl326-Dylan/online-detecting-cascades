package Microsoft;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
       // List<Float> rs = new ArrayList<Float>();
        float [] table = new float[N];
        for(int i=0;i<N;i++) {
        	float a = in.nextFloat();
        	String op = in.next();
        	char opp = op.toCharArray()[0];
        	float b = in.nextFloat();
        	float result =0;
        	switch(opp)
        	{
        	case '+': result = a+b;break;
        	case '-': result = a-b;break;
        	case '*': result = a*b;break;
        	case '/': result = a/b;break;
        	}
        	//rs.add(result-9);
        	table[i] = Math.abs(result-9);
        	//System.out.println(a + b);
        }
        double min = 100000;
        int index = -1;
        for(int i=0;i<N;i++)
        {
        	//System.out.println(rs.get(i));
        	if(table[i]<min)
        	{
        		index = i+1;
        		min = table[i];
        	}
        }
        System.out.println(index);
    }
}