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
	public FlipFitAdmin()
	{
		super();
//		this.gymOwnerList = gymOwnerList;
	}
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
	
	

}
