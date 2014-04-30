package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Solution7 {
/**
 * word ladder 2
 * accept
 * from end to begin
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
		queue.offer(end);
		level.offer(1);
		wP.put(queue.peek(), null);//start has no parents;
		visited.put(end, 1);
		while(!queue.isEmpty() && (!visited.containsKey(start) || level.peek() == visited.get(start)-1)){
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
						if(changed.equals(start))
						{
							visited.put(changed, le+1);
							//level.offer(le+1);
							if(wP.containsKey(start)){
								ArrayList<String> p = wP.get(start);
								p.add(par);
								wP.put(start, p);
							}else{
								ArrayList<String> p = new ArrayList<String>();
								p.add(par);
								wP.put(start, p);
							}	
						}
						//return le+1;
						else if((visited.get(changed)==null || visited.get(changed) == le+1) && dict.contains(changed)){
							//System.out.println(changed);
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
		if(!wP.containsKey(start))
			return fl;
		traceback(wP,start,fl, new ArrayList<String>());
		
		return fl;
	 }
	private void traceback(HashMap<String,ArrayList<String>> wP, String word, ArrayList<ArrayList<String>> fl, 
			ArrayList<String> cur){
		
		if(wP.get(word)==null)
		{
			ArrayList<String> next = new ArrayList<String>();
			if(cur!=null&& cur.size()>0)
			{
				next.addAll( cur);
			}
			next.add(word);
			fl.add(next);
			return;
		}
		for(String p: wP.get(word)){
			
			ArrayList<String> next = new ArrayList<String>();
			if(cur!=null&& cur.size()>0)
				next.addAll(cur);
			next.add(word);
			traceback(wP, p, fl, next);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution7 s5 = new Solution7();
		HashSet<String> dict= new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("cog");
		dict.add("tot");
		dict.add("hog");
		dict.add("hop");
		dict.add("pot");
		//"hot", "dog", ["hot","dog","dot"]  	"hot", "dog", ["hot","dog"]
		//System.out.println(s5.findLadders("hit", "cog", dict));
		//System.out.println(s5.findLadders("hit", "cog", dict));
		//Input: 	"hot", "dog", ["hot","cog","dog","tot","hog","hop","pot","dot"]
		//Output: 	[["hot","hog","dog","hot","dot","dog"],["hot","hog","dog","hot","dot","dog"]]
		//Expected: 	[["hot","dot","dog"],["hot","hog","dog"]]
		System.out.println(s5.findLadders("hot", "dog", dict));
	
	}

}
