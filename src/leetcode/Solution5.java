package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Solution5 {

	public int ladderLength(String start, String end, HashSet<String> dict){
		// kv hold the information between each string and its distance one corresponding String set
		HashMap<String,HashSet<String>> kv = new HashMap<String, HashSet<String>>();
		kv = fillHashMap(dict);
		//find all strings whose distance is one to start string
		HashSet<String> startv = new HashSet<String>();
		String[] b = new String[dict.size()];
		String[] dis =dict.toArray(b);
		for(int i=0;i<dict.size();i++)
			if(isDistanceOne(start,dis[i])==1)
				startv.add(dis[i]);
		//find all strings whose distance is one to end string
		HashSet<String> endv = new HashSet<String>();
		for(int i=0;i<dict.size();i++)
			if(isDistanceOne(end,dis[i])==1)
				endv.add(dis[i]);
		int min = dict.size()+5;
		kv.put(start, startv);
		kv.put(end, endv);
		return func2(start,startv,end,endv,kv, dict );	
	}
	/**
	 * using BFS to search 
	 * @param start
	 * @param startv
	 * @param end
	 * @param endv
	 * @param kv
	 * @param dict
	 * @return
	 */
	private int func2(String start, HashSet<String> startv, String end, HashSet<String> endv,
			HashMap<String,HashSet<String>> kv, HashSet<String> dict){
		int min = dict.size()+5;
		boolean flag = false;
		//boolean flag1 = false;
		HashSet<String> endc = new HashSet<String>();
		HashSet<String> endL = new HashSet<String>();
		endL.add(end);
		int len = 0;
		while(endL.size()>0 && !endc.containsAll(endL)){
			len++;
			HashSet<String> startc = new HashSet<String>();
			HashSet<String> startL = new HashSet<String>();
			startL.add(start);
			int len2 = 0;
			while(startL.size()>0 && !startc.containsAll(startL)){
				//System.out.println(len2);
				len2++;
				//System.out.println(len2);
				Iterator<String> its = endL.iterator();
				flag = false;
				while(its.hasNext()){
					if(startL.contains(its.next()))
					{
						flag = true;
						if(len2+len<min)
							min = len2+len-1;
						break;
					}
				}
				if(flag == true)
					break;
				startc.addAll(startL);// add the new appear words
				//search the next level words
				Iterator<String> it = startL.iterator();
				HashSet<String> startN = new HashSet<String>();
				while(it.hasNext())
				{	
					startN.addAll(kv.get(it.next()));
				}
				startL.clear();//clear startL
				startL.addAll(startN); 
				startL.removeAll(startc); //remove the words that have been appeared
			}
			endc.addAll(endL); // add the new appear word
			Iterator<String> it = endL.iterator();
			HashSet<String> endN = new HashSet<String>();
			while(it.hasNext()){
				endN.addAll(kv.get(it.next()));
			}
			endL.clear();
			endL.addAll(endN);
			endL.removeAll(endc);
		}
		if(flag)
			return min;
		return 0;
	}
	/**
	 * find all 
	 * @param dict
	 * @return
	 */
	private HashMap<String,HashSet<String>> fillHashMap(HashSet<String> dict){
		HashMap<String,HashSet<String>> kv = new HashMap<String, HashSet<String>>();
		String[] a = new String[dict.size()];
		String[] dis =dict.toArray(a);
		//dict.toArray(arg0)
		for(int i=0;i<dict.size();i++){
			HashSet<String> v = new HashSet<String>();
			for(int j=0;j<dict.size();j++){
				if(isDistanceOne(dis[i],dis[j])==1)
					v.add(dis[j]);
			}
			kv.put(dis[i], v);
		}
		return kv;
	}
	/**
	 * caculate two string distance
	 * @param str1
	 * @param str2
	 * @return -1 0 1 2
	 */
	private int isDistanceOne(String str1, String str2){
		int dis = 0;
		if(str1.length()!=str2.length())
			return -1;
		for(int i=0;i<str1.length();i++){
			if(str1.charAt(i)!=str2.charAt(i))
				dis++;
			if(dis>1)
				return 2;
		}
		return dis;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub123123
		Solution5 s5 = new Solution5();
		HashSet<String> dict= new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		System.out.println(s5.ladderLength("hit", "cog", dict));
	}

}
