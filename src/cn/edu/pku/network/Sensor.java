package cn.edu.pku.network;

import cn.edu.pku.datastructure.DoubleArray;
import cn.edu.pku.datastructure.IntArray;


public class Sensor {

	public double locX; // the location of the sensor node
	public double locY;

	public int sId; // the unique id for the sensor node

	public DoubleArray chlorineData; // a dataArray for record the data of the sensor
							// node, and the records are in accordance with the time;

	public IntArray neighbors; // a intAarray for the neighbor node of this sensor node;

	public Sensor() {
		this.chlorineData = new DoubleArray();
		this.neighbors = new IntArray();
	}

	public Sensor(int id, double locx, double locy) {
		this();
		this.sId = id;
		this.locX = locx;
		this.locY = locy;
	}

	public Sensor clone() {
		Sensor s = new Sensor();
		s.locX = this.locX;
		s.locY = this.locY;
		s.sId = this.sId;
		s.neighbors = this.neighbors.clone();
		s.chlorineData = this.chlorineData.clone();

		return s;
	}

	/*
	 *  get the locx;
	 */
	public double getLocX() {
		return this.locX;
	}

	/*
	 *  get the locy;
	 */
	public double getLocY() {
		return this.locY;
	}

	public IntArray getNeighbors() {
		return neighbors;
	}
	
	public DoubleArray getChlorineData() {
		return chlorineData;
	}
	
	/*
	 *  output the sensor's information, include id, sensory data and neighbor 
	 *  nodes;
	 */ 
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("sensor node_" + this.sId + "'s location are locx:"
				+ this.locX + ",locy:" + this.locY);
		buf.append(", its' residual chlorine data is + this.chlorineData.toString()");
		buf.append(", the neighbor nodes of this sensor are + this.neighbors.toString() ");
		
		return buf.toString();
	}
}
