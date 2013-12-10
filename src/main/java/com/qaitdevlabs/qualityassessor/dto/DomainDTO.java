package com.qaitdevlabs.qualityassessor.dto;



/**
 * Created with IntelliJ IDEA.
 * User: anujchhabra
 * Date: 24/8/12
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class DomainDTO {
	private	String id;
    private String name;
    private String weightage;
    public String getName(){
        return  name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getWeightage(){
        return  weightage;
    }
    public void setWeightage(String weightage){
        this.weightage = weightage;
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
