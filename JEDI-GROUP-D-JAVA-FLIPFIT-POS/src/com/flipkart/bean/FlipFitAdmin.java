/**
 * 
 */
package com.flipkart.bean;
import java.util.*;
/**
 * 
 */
public class FlipFitAdmin extends FlipFitGymUser {
	private ArrayList<String> gymOwnerList;

	public ArrayList<String> getGymOwnerList() {
		return gymOwnerList;
	}
	public void setGymOwnerList(ArrayList<String> gymOwnerList) {
		this.gymOwnerList = gymOwnerList;
	}
	public ArrayList<String> getGymList() {
		return gymList;
	}
	public void setGymList(ArrayList<String> gymList) {
		this.gymList = gymList;
	}
	private ArrayList<String> gymList;

	public FlipFitAdmin(ArrayList<String> gymOwnerList, ArrayList<String> gymList) {
		this.gymOwnerList = gymOwnerList;
		this.gymList = gymList;
	}

	public FlipFitAdmin() {
	}

	@Override
	public String toString() {
		return "FlipFitAdmin{" +
				"gymOwnerList=" + gymOwnerList +
				", gymList=" + gymList +
				'}';
	}
}
