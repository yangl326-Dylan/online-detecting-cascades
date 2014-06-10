package cn.edu.pku.datastructure;


import java.util.ArrayList;

public class DoubleArray {
	private ArrayList<Double> doubleList;

	public DoubleArray() {
		this.doubleList = new ArrayList<Double>();
	}

	public void addEle(double value) {
		this.doubleList.add(new Double(value));
	}

	public void addArray(double[] array) {
		for (int i = 0;i < array.length;i++)
			this.doubleList.add(new Double(array[i]));
	}
	
	public double getEle(int index) {
		if (index < 0 || index >= size())
			return Integer.MAX_VALUE;
		return this.doubleList.get(index);
	}

	public void setEle(int index, double value) {
		if (index < 0 || index >= size()) {
			return;
		}
		this.doubleList.set(index, new Double(value));
	}

	public int getIndex(double value) {
		return this.doubleList.indexOf(new Double(value));
	}

	public void remByIndex(int index) {
		if (index < 0 || index >= size()) {
			return;
		}
		this.doubleList.remove(index);
	}

	@SuppressWarnings("unchecked")
	public DoubleArray clone() {
		DoubleArray da = new DoubleArray();
		da.doubleList = (ArrayList<Double>) this.doubleList.clone();
		return da;
	}

	public int size() {
		return this.doubleList.size();
	}

	public void clear() {
		this.doubleList.clear();
	}

	public String toString() {
		return this.doubleList.toString();
	}
	
	public double[] toArray() {
		double[] array = new double[doubleList.size()];
		
		for (int i = 0;i < array.length;i++) {
			array[i] = doubleList.get(i);
		}
		
		return array;
	}
}