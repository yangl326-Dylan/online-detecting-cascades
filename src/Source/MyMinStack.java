package Source;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 关键是这个stack的每个元素咋么定义
 * @author Echo
 *
 */
class Element{
	int data;
	int min;
	public Element(int _data,int _min)
	{
		data = _data;
		min = _min;
	}
}
public class MyMinStack {
	int cap = 16;
	Element [] table;
	int top = -1;
	
	public MyMinStack(){
		table = new Element[cap];
	}
	public MyMinStack(int _cap){
		if(_cap>0)
			cap = _cap;
		table = new Element[cap];
	}
	
	public boolean push(int item)
	{
		if(top+1<cap)
		{
			if(top>=0)
			{
				Element e = new Element(item,table[top].min<item?table[top].min:item);
				table[++top] = e;
			}else
				table[++top] = new Element(item,item);
			return true;
		}
		System.out.println("capacity is not enough");
		return false;
		
	}
	
	public int pop()
	{
		if(top>=0)
			return table[top--].data;
		else
		{
			System.out.println("there is no element in the stack");
			return -1;
		}
	}
	
	public int min()
	{
		if(top>=0)
			return table[top].min;
		else
		{
			System.out.println("there is no element in the stack");
			return -1;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyMinStack stack = new MyMinStack();
		//stack.push(13);
		Random r = new Random(17);
		
		for(int i=0;i<10;i++)
		{
			int e = r.nextInt(100);
			System.out.print(e+" ");
			stack.push(e);
		}
		System.out.println();
		System.out.println(stack.min());
		System.out.println(stack.pop());
		
	}

}
