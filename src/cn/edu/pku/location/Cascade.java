package cn.edu.pku.location;

import java.util.ArrayList;
import java.util.List;

/**
 * the class of cascade event
 * @author Echo
 *
 */
public class Cascade {
	//the junction id or the node id
	private int junctionId;
	//the detected time or the real time of pollution reaches the node
	private int detectedTime;
	//the children nodes , test
	private List<Cascade> children = new ArrayList<Cascade>();
	//population affected
	private double populationAffected;
	
	
	/**
	 * add a child to the children
	 * @param child
	 */
	public void addChild(Cascade child){
		children.add(child);
	}
	/**
	 * add children to the children
	 * @param children
	 */
	public void addChildren(List<Cascade> children){
		this.children.addAll(children);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getJunctionId() {
		return junctionId;
	}

	public void setJunctionId(int junctionId) {
		this.junctionId = junctionId;
	}

	public int getDetectedTime() {
		return detectedTime;
	}

	public void setDetectedTime(int detectedTime) {
		this.detectedTime = detectedTime;
	}

	public List<Cascade> getChildren() {
		return children;
	}

	public void setChildren(List<Cascade> children) {
		this.children = children;
	}
	public double getPopulationAffected() {
		return populationAffected;
	}
	public void setPopulationAffected(double populationAffected) {
		this.populationAffected = populationAffected;
	}

}
