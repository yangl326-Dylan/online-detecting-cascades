package Source;
/**
 * 链表节点结构
 * @author Echo
 *
 */
class LinkNode {
	int data;
	LinkNode next=null;
	public LinkNode(){}
	public LinkNode(int _data){
		data = _data;
	}
	public LinkNode(int _data, LinkNode _next)
	{
		data = _data;
		next = _next;
	}
	public String toString(){
		if(next!=null)
			return data+", " +next.toString();
		return data+".";
	}
}
public class LinkedListProblems {

	/**
	 * 问题1 求两个链表的第一个公共节点
	 * @param head1
	 * @param head2
	 * @return
	 */
	public static LinkNode firstCommonNode(LinkNode head1, LinkNode head2){
		int len1 = 0;
		int len2 = 0;
		LinkNode point = head1;
		while(point.next!=null)
		{
			len1++;
			point = point.next;
		}
		point = head2;
		while(point.next!=null)
		{
			len2++;
			point = point.next;
		}
		int len = 0;
		LinkNode ni = head1;
		LinkNode nj = head2;
		if(len1>len2)
		{
			len=len1-len2;
			while(len>0)
			{
				ni = ni.next;
				len--;
			}
		}
		else
		{
			len=len2-len1;
			while(len>0)
			{
				nj = nj.next;
				len--;
			}
		}
		while(ni!=null && nj!=null)
		{
			if(ni==nj)
			{
				return ni;
			}
			ni=ni.next;
			nj=nj.next;
		}
		return null;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkNode head1 = new LinkNode(0);
		LinkNode curr =head1;
		
		LinkNode head2 = new LinkNode(0);
		LinkNode curr2 =head2;
		//head.next = curr;
		for(int i=0;i<10;i++)
		{
			LinkNode tmp  = new LinkNode(i+1);
			curr.next = tmp;
			curr = tmp;
		}
		for(int i=0;i<10;i++)
		{
			LinkNode tmp  = new LinkNode(i+100);
			curr2.next = tmp;
			curr2 = tmp;
		}
		for(int i=0;i<10;i++)
		{
			LinkNode tmp  = new LinkNode(i+1000);
			curr.next = curr2.next = tmp;
			curr = curr2 = tmp;
		}
		//System.out.println(head1);
		///System.out.println(head2);
		System.out.println(firstCommonNode(head1,head2));
	}

}
