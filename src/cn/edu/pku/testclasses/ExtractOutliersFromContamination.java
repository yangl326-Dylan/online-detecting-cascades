package cn.edu.pku.testclasses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import cn.edu.pku.network.Topology;

public class ExtractOutliersFromContamination {
	
	static List<Integer> outliers;
	static List<Integer> affectedTime;
	
	public static void readDataFromFile(String locFile) throws Exception{
		outliers = new ArrayList<Integer>();
		affectedTime = new ArrayList<Integer>();
		BufferedReader loc = new BufferedReader(new FileReader(locFile));
		String line;

		while ((line = loc.readLine()) != null) {
			StringTokenizer token = new StringTokenizer(line);
			int sId = new Integer(token.nextToken());
			int i = 0;
			while(token.hasMoreTokens()){
				i++;
				if(new Double(token.nextToken()) > 0.0)
				{
					outliers.add(sId);
					affectedTime.add(i);
					break;
				}
			}
		}
		loc.close();
		System.out.println(outliers);
		System.out.println(affectedTime);
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Topology top = new Topology();
		String dir = System.getProperty("user.dir");//获得项目的地址
	    String locFile = dir + "//resource//contamination_p0_5509t_240i.txt";  //文件
	    ExtractOutliersFromContamination.readDataFromFile(locFile);
	}

}
