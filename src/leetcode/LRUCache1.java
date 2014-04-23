package leetcode;

import java.util.HashMap;
/**
 * 自己建一个双向链表
 * 表头表示最新使用过得、表尾表示latest least used
 * 采用LinkedList<E>没法在O(1)的时间内删除某个节点(我感觉是这样)
 * @author Echo
 *
 */
class DNode
{
	int key;
	int value;
	DNode next;
	DNode pre;
	public DNode()
	{
	}
	public DNode(int k, int v)
	{
		key = k;
		value = v;
	}
}
/**
 * 下面是LRU这个类
 * @author Echo
 *
 */
public class LRUCache1 {
	//key和DNode是很明显的对应关系，并且hash表中的value之间又是双向链表
	HashMap<Integer,DNode> KV ; //这个数据结构可以参考 http://www.programcreek.com/2013/03/leetcode-lru-cache-java/
	DNode head ; //表示双向链表的表头
	DNode tail ; //表示双向链表的表尾
	int cap; //cache的容量
	int sz = 0; //cache的实际size
	public LRUCache1(int capacity)
	{
		cap = capacity;
		KV = new HashMap<Integer,DNode>(cap); 
	}
	/**
	 *  Get the value (will always be positive) of the key if the key exists in the cache,
	 *  otherwise return -1.
	 * @param key
	 * @return
	 */
	public int get(int key)
	{
		if(KV.containsKey(key))
		{
			DNode u = KV.get(key);	
			remove(u);
			addHead(u);
			KV.put(key, u);
			return u.value;
		}
		return -1;
	}
	/**
	 * Set or insert the value if the key is not already present. 
	 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
	 * @param key
	 * @param value
	 */
	public void set(int key, int value)
	{
		DNode u = new DNode(key,value);
		if(get(key) == -1)
		{	
			if(sz<cap)
			{
				KV.put(key, u);
				addHead(u);
				sz++;
			}
			else{
				int tailK = tail.key;
				KV.remove(tailK);
				if(tail == head)
				{
					tail = null;
					head = null;
					addHead(u);
					KV.put(key, u);
				}else{
					DNode temp = tail.pre;
					temp.next = null;
					tail = temp;
					addHead(u);
					KV.put(key, u);
				}
				
			}
		}else
		
			KV.get(key).value = value;	
	}
	private void addHead(DNode u) {
		// TODO Auto-generated method stub
		if(head == null)
		{
			head = u;
			tail = u;
		}else{
			head.pre = u;
			u.next = head;
			head = u;
			u.pre = null;
		}
		
		//return head;
	}
	private DNode remove(DNode n)
	{
		DNode pre = n.pre;
		DNode next = n.next;
		if(pre == null && next == null)
		{
			head = null;
			tail = null;
			return null;
		}
		if(pre == null)
		{
			n.next.pre = null;
			head = n.next;
			return n.next;
		}
		if(next == null)
		{
			n.pre.next = null;
			tail = n.pre;
			return n.pre;
		}
		pre.next = n.next;
		next.pre = pre;
		return pre;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LRUCache1 lu = new LRUCache1(1);
		lu.set(2, 1);
		System.out.println(lu.get(2));
		lu.set(3,2);
		System.out.println(lu.get(2));
		System.out.println(lu.get(3));
	}

}
