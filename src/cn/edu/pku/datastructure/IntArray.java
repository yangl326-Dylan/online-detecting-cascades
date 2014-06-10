package cn.edu.pku.datastructure;


import java.io.Serializable;
import java.util.*;

public class IntArray implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> intList;

	public IntArray() {
		this.intList = new ArrayList<Integer>();
	}

	public void addEle(int value) {
		this.intList.add(new Integer(value));
	}

	public void insertEle(int pos, int value) {
		this.intList.add(pos, new Integer(value));
	}

	public void setEle(int index, int value) {
		if (index < 0 || index >= size())
			return;
		this.intList.set(index, new Integer(value));
	}

	public int getEle(int index) {
		if (index < 0 || index >= size())
			return Integer.MAX_VALUE;
		return ((Integer) this.intList.get(index)).intValue();
	}

	public int getIndex(int value) {
		return this.intList.indexOf(new Integer(value));
	}

	public boolean contains(int value) {
		return this.intList.contains(new Integer(value));
	}

	public void remByIndex(int index) {
		if (index < 0 || index >= size())
			return;
		this.intList.remove(index);
	}

	@SuppressWarnings("unchecked")
	public IntArray clone() {
		IntArray ia = new IntArray();
		ia.intList = (ArrayList<Integer>) this.intList.clone();
		return ia;
	}

	public void mergeWithoutDuplicated(IntArray array2) {
		for (int i = 0;i < array2.size();i++) {
			int value = array2.getEle(i);
			
			if (!this.contains(value)) {
				this.addEle(value);
			}
		}
	}
	
	public void merge(IntArray array2) {
		for (int i = 0;i < array2.size();i++) {
			this.addEle(array2.getEle(i));
		}
	}
	
	public void remove(IntArray array2) {
		for (int i = 0;i < array2.size();i++) {
			int val = array2.getEle(i);
			
			for (int j = 0;j < intList.size();j++) {
				if (intList.get(j) == val) {
					intList.remove(j);
					j--;
				}
			}
		}
	}
	
	public int size() {
		return this.intList.size();
	}

	public void clear() {
		this.intList.clear();
	}

	public String toString() {
		return this.intList.toString();
	}

	public void clearDuplicated() {
		for (int i = 0; i < size(); i++) {
			int val = this.getEle(i);
			for (int j = i + 1; j < size();) {
				if (val == this.getEle(j))
					this.remByIndex(j);
				else
					j++;
			}
		}
	}
	
	public void sort() {
		for (int i=0;i<size()-1;i++){
			for(int j=0;j<size()-i-1;j++){ 
				int vala=this.getEle(j);
				int valb=this.getEle(j+1);
				if(vala>valb){ //change
                   this.setEle(j, valb);
                   this.setEle(j+1, vala);
				}
			}
		}
	}

}