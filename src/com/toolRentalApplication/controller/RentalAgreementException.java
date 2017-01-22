package com.toolRentalApplication.controller;

/**
* A class representing an Exception that would be thrown by processing a Rental Agreement
* 
* @author Karen Pelletier
* @version 1.0
*
*/
public class RentalAgreementException extends Exception{

	private static final long serialVersionUID = 1L;

	public RentalAgreementException(String message){
		super(message);
	}
}
