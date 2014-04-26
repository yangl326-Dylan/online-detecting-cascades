package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Solution3 {
	/**
	 * Word break
	 * Given a string s and a dictionary of words dict, 
	 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
	 * 采用动态规划算法；设数组Db[0...len], Db[i]表示string[0...i]是可segmented的
	 * 采用动态规划时，很关键的一点是得弄清楚子问题是什么。	
	 * 当我在考虑第i个字符时，我应该是从前面的Db[j]为true的j+1开始到i，这段字串是否在dict中，如果不再，则继续往前找合适的j（这里的合适指的是Db[j]为true）
	 * @param s
	 * @param dict
	 * @return
	 */
	public boolean wordBreak1(String s, Set<String> dict)
	{
		//if the dict is empty, return false
		if(dict.size()<=0)
			return false;
		boolean[] Db = new boolean[s.length()];
		//initialize
		if(dict.contains(s.substring(0,1)))
			Db[0] = true;
		else
			Db[0] = false;
		//DP process
		for(int i=1;i<s.length();i++)
		{
			Db[i] = false;
			int j = i-1;
			while(j>=0)
			{
				if(Db[j] && dict.contains(s.substring(j+1,i+1)))
				{
					Db[i] = true;
					break;
				}
				j--;		
			}
			if(!Db[i] && j<0 && dict.contains(s.substring(0,i+1)))
				Db[i] = true;
				
				
		}
		return Db[s.length()-1];
	}
	/**
	 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence 
	 * where each word is a valid dictionary word. Return all such possible sentences.
	 * 这道题是一个递归问题，如果对第一道word break的DP的递推式理解的话，就不难解这一题
	 * 采用递归的方法把一个规模较大的问题递归成一个规模较小的问题
	 * @param s
	 * @param dict
	 * @return
	 */
	public ArrayList<String> wordBreak2(String s,Set<String> dict){
		//先采用dp获得标识信息的数组
		boolean[] Db = wordB(s,dict);
		//if it is not a breakable string, return empty
		if(Db == null || Db[s.length()-1] == false)
			return new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
		//采用递归的方法求解中共有多少种字符串分割方法
		subProblem(list,new String(), Db, s.length()-1,dict, s);
		
		return list;
	}
	/**
	 * 
	 * @param list 相当于全局的一个变量
	 * @param curr 从最后到目前为止，已经形成的字符串，在这次迭代中把当前的一个合法单词放到curr前面，并且以“ ”分割
	 * @param Db 标识信息，一直都需要
	 * @param end 表示目前问题的规模
	 * @param dict 
	 * @param s
	 */
	private void subProblem(ArrayList<String> list, String curr, boolean[] Db, int end,Set<String> dict,String s)
	{
		//迭代结束的基本情况，当所有串都已经获得时，就把这种分割方式的结果放到全局的list中
		if(end == -1)
			list.add(curr.substring(0,curr.length()));
		
		int j = end-1;
		while(j>=0){
			if(Db[j] && dict.contains(s.substring(j+1,end+1)) )
			{		
				String cur = s.substring(j+1,end+1) + " "+curr;
				subProblem(list, cur, Db, j, dict, s);
			}
			j--;
		}
		//这是特殊情况
		if(dict.contains(s.substring(0,end+1)))
		{
			String cur = s.substring(0,end+1) + " "+curr;
			subProblem(list, cur, Db, -1, dict, s);
		}
	}
	/**
	 * DP过程
	 * @param s
	 * @param dict
	 * @return
	 */
	private boolean[] wordB(String s, Set<String> dict){
		// if the dict is empty, return false
		if (dict.size() <= 0)
			return null;
		boolean[] Db = new boolean[s.length()];
		// initialize
		if (dict.contains(s.substring(0, 1)))
			Db[0] = true;
		else
			Db[0] = false;
		// DP process
		for (int i = 1; i < s.length(); i++) {
			Db[i] = false;
			int j = i - 1;
			while (j >= 0) {
				if (Db[j] && dict.contains(s.substring(j + 1, i + 1))) {
					Db[i] = true;
					break;
				}
				j--;
			}
			if (!Db[i] && j < 0 && dict.contains(s.substring(0, i + 1)))
				Db[i] = true;

		}
		return Db;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution3 s3 = new Solution3();
		Set<String> s = new HashSet<String>();
		s.add("cat");
		s.add("cats");
		s.add("and");
		s.add("sand");
		s.add("dog");
		System.out.println(s3.wordBreak2("catsanddog",s));
	}

}
