package com.toolRentalApplication.controller;

/**
* A class representing a Tool* 
* 
* @author Karen Pelletier
* @version 1.0
*
*/
public class Tool {
	public String code;
	public String brand;
	public ToolType toolType;
	
	public Tool(String code, String brand, ToolType toolType){
		this.code = code;
		this.brand = brand;
		this.toolType = toolType;
	}
	
	public String getCode(){
		return code;
	}
	
	public String getBrand(){
		return brand;
	}
	
	public ToolType getToolType(){
		return toolType;
	}
}
