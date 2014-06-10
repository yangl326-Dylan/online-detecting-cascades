package cn.edu.pku.network;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import cn.edu.pku.datastructure.IntArray;

public class Topology {
	public List<Sensor> sensors = new ArrayList<Sensor>();
	/**
	 * 邻居矩阵，1表示i,j邻居结点，0表示i,j非邻居结点
	 */
	public int[][] neighborMatrix;
	
	public Topology() {
	}

	public void readLocationInfo(String locFile) throws Exception {
		BufferedReader loc = new BufferedReader(new FileReader(locFile));

		loc.readLine();// the first line is header, we should
						// ignore it.
		String line;

		while ((line = loc.readLine()) != null) {
			StringTokenizer token = new StringTokenizer(line);
			int sId = new Integer(token.nextToken());
			double locX = new Double(token.nextToken());
			double locY = new Double(token.nextToken());
			Sensor s = new Sensor(sId, locX, locY);

			sensors.add(s);
		}
		loc.close();
	}

	public void readSerialChlorineData(String dataFile) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(dataFile));
		//System.out.println(sensors.size());
		for (int i = 0; i < sensors.size(); i++) {
			Sensor s = sensors.get(i);
			
			StringTokenizer token = new StringTokenizer(in.readLine());

			token.nextToken(); // ignore the id of sensor node,

			while (token.hasMoreTokens()) {
				s.chlorineData.addEle(new Double(token.nextToken()));
			}
		}

		in.close();
	}

	/**
	 * 读取管道拓扑图，注意，目前该函数只支持ID依次为0-K的传感器拓扑图！！！
	 * @param pipeFile
	 * @throws Exception
	 */
	public void buildRelation(String pipeFile) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(pipeFile));
		String line = in.readLine(); // the first line is header, we
										// should ignore it
		neighborMatrix = new int[sensors.size()][sensors.size()];
		for (int i = 0;i < neighborMatrix.length;i++) {
			neighborMatrix[i][i] = 1;
			
			for (int j = i + 1;j < neighborMatrix.length;j++) {
				neighborMatrix[i][j] = neighborMatrix[j][i] = 0;
			}
		}
		int cnt=0;
		while ((line = in.readLine()) != null) {
			StringTokenizer token = new StringTokenizer(line);
			
			token.nextToken();
			int nodeA = new Integer(token.nextToken());
			int nodeB = new Integer(token.nextToken());

			sensors.get(nodeA).neighbors.addEle(nodeB);
			sensors.get(nodeB).neighbors.addEle(nodeA);
		    cnt++;
			neighborMatrix[nodeA][nodeB] = neighborMatrix[nodeB][nodeA] = 1;
		}
		
	}
	
	public boolean isNeighbor(int sensor1Id, int sensor2Id) {
		return (neighborMatrix[sensor1Id][sensor2Id] == 1);
	}
	
	public double getDistance(int sid1, int sid2) {
		Sensor s1 = sensors.get(sid1);
		Sensor s2 = sensors.get(sid2);
		double xDist = s1.locX - s2.locX;
		double yDist = s1.locY - s2.locY;
		
		return Math.sqrt(xDist*xDist + yDist*yDist);
	}
	
	public IntArray getNeighbors(int sensorId) {
		IntArray arr = new IntArray();
		
		for (int i = 0;i < neighborMatrix.length;i++) {
			if (neighborMatrix[sensorId][i] == 1)
				arr.addEle(i);
		}
		
		return arr;
	}
	
	public boolean isTerminalSensor(int sensorId) {
		IntArray arr = this.getNeighbors(sensorId);
		if(arr.size()<2)
			return true;
		return false;
	}
/*	
	public boolean isEffectiveSensor(int sensorId) {
		double[] stream = this.sensors.get(sensorId).chlorineData.toArray();
		Statistic stat = new Statistic(stream);
		
		if (stat.getMean() >= 0.5 && stat.getStandardDeviation() > 0)
			return true;
		return false;
	}
	*/
	/**
	 * 获得拓扑结构例子
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Topology top = new Topology();
		String dir = System.getProperty("user.dir");//获得项目的地址
	    String pipeFile =  dir + "//resource//pipe_info.txt"; //获得管道信息的文件路径
	    String locFile = dir + "//resource//sensor_loc.txt";  //每个传感器位置信息的文件
	    top.readLocationInfo(locFile); //读传感器位置信息
		top.buildRelation(pipeFile); //读邻居信息 也就是topology信息
		int num = 0;
		for (int i = 0;i < top.neighborMatrix.length;i++) {
			IntArray arr = top.getNeighbors(i);
			
			if (arr.size() < 2) {
				System.out.print(i + ", ");
				num++;
			}
		}
	}
}