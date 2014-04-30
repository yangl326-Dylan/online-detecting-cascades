package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

class SNode{
	String val;
	SNode next;
	
}
public class Solution6 {
	/**
	 * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, 
	 * BFS && 26 characters
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	public int ladderLength(String start, String end, HashSet<String> dict){
		if(start.equals(end))
			return 2;
		//for BFS 
		LinkedList<String> queue = new LinkedList<String>();
		//store the words that have visited and in the dict
		HashSet<String> visited = new HashSet<String>();
		//the level information for every word
		LinkedList<Integer> level = new LinkedList<Integer>();
		queue.offer(start);
		level.offer(1);
		while(!queue.isEmpty()){
			String interW = queue.poll();
			int le = level.poll();
			
			//for every character in the word
			for(int i=0;i<interW.length();i++){
				
				char[] words = interW.toCharArray();
				char o = words[i];//the original word
				for(char c='a';c<='z';c++)
				{
					if(c!=o){
						//subsitude by another char
						words[i] = c;	
						String changed = new String(words);
						if(changed.equals(end))
							return le+1;
						if((!visited.contains(changed)) && dict.contains(changed)){
							visited.add(changed);
							queue.offer(changed);
							level.offer(le+1);
						}
					}
				}
				
			}
		}
		return 0;
	}
	/**
	 * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end,
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
		
		//for BFS 
		LinkedList<String> queue = new LinkedList<String>();
		//store the words that have visited and in the dict, and its corresponding level
		HashMap<String,Integer> visited = new HashMap<String,Integer>();
		//the level information for every word
		LinkedList<Integer> level = new LinkedList<Integer>();
		//the word and its parents
		HashMap<String,ArrayList<String>> wP = new HashMap<String,ArrayList<String>>();
		queue.offer(start);
		level.offer(1);
		wP.put(queue.peek(), null);//start has no parents;
		visited.put(start, 1);
		while(!queue.isEmpty() && (!visited.containsKey(end) || level.peek() == visited.get(end)-1)){
			String par = queue.poll();
			int le = level.poll();
			
			//for every character in the word
			for(int i=0;i<par.length();i++){
				
				char[] words = par.toCharArray();
				char o = words[i];//the original word
				for(char c='a';c<='z';c++)
				{
					if(c!=o){
						//subsitude by another char
						words[i] = c;	
						String changed = new String(words);
						//the last-1 level . 
						if(changed.equals(end))
						{
							visited.put(changed, le+1);// it.s very important!!!! Dont't forget!!
							
							if(wP.containsKey(end)){
								ArrayList<String> p = wP.get(end);
								p.add(par);
								wP.put(end, p);
							}else{
								ArrayList<String> p = new ArrayList<String>();
								p.add(par);
								wP.put(end, p);
							}	
						}
						//return le+1;
						else if((visited.get(changed)==null || visited.get(changed) == le+1) && dict.contains(changed)){
							//the condition is very important!!! otherwise, there will be duplicate.
							if(!visited.containsKey(changed))
							{
								queue.offer(changed);
								level.offer(le+1);
							}
							visited.put(changed,le+1);
							
							//update the word and his parents information
							if(wP.containsKey(changed)){
								ArrayList<String> p = wP.get(changed);
								p.add(par);
								wP.put(changed, p);
							}else{
								ArrayList<String> p = new ArrayList<String>();
								p.add(par);
								wP.put(changed, p);
							}
						}
					}
				}
				
			}
		}
		ArrayList<ArrayList<String>> fl =new ArrayList<ArrayList<String>>();
		//it's very important!!! to Check whether it has such path
		if(!wP.containsKey(end))
			return fl;
		traceback(wP,end,fl, null);
		
		return fl;
	 }
	/**
	 * DFS ,对每个节点的父节点进行深度遍历
	 * @param wP
	 * @param word
	 * @param fl
	 * @param cur
	 */
	private void traceback(HashMap<String,ArrayList<String>> wP, String word, ArrayList<ArrayList<String>> fl, 
			ArrayList<String> cur){
		if(wP.get(word)==null)
		{
			ArrayList<String> next = new ArrayList<String>();
			next.add(word);
			if(cur!=null && cur.size()>0)
				next.addAll(cur);
			fl.add(next);
			return;
		}
		for(String p: wP.get(word)){
			ArrayList<String> next = new ArrayList<String>();
			next.add(word);
			if(cur!=null && cur.size()>0)
				next.addAll(cur);
			traceback(wP, p, fl, next);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution6 s5 = new Solution6();
		HashSet<String> dict= new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("cog");
		dict.add("tot");
		dict.add("hog");
		dict.add("hop");
		dict.add("pot");
		
		//"hot", "dog", ["hot","dog","dot"]
		System.out.println(s5.findLadders("hot", "dog", dict));
	}

}
