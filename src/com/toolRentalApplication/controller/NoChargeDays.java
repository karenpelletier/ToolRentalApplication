package com.toolRentalApplication.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
* An ENUM to hold all of the No Charge Day Options
* Each item has a method to calculate the updated number of days a customer should be charged.
* 
* Calculate Charge Days Method takes the following parameters:
* rentalDayCount - the number of days a customer wants to rent the tool
* startDate - the start date for the rental
* currentChargeDays - the number of days that a customer can currently be charged for
* 
* @author Karen Pelletier
* @version 1.0
*/
public enum NoChargeDays {
	SATURDAY {
		public int calculateChargeDays(int rentalDayCount, LocalDate startDate, int currentChargeDays){
			LocalDate dateTracker = startDate;
			int chargeDays = currentChargeDays;
			for(int count = 0; count < rentalDayCount; count++){
				if(dateTracker.getDayOfWeek() == DayOfWeek.SATURDAY){
					chargeDays = chargeDays - 1;
				}
				dateTracker = dateTracker.plusDays(1);
			}
			return chargeDays;
		}
	},
	SUNDAY {
		public int calculateChargeDays(int rentalDayCount, LocalDate startDate, int currentChargeDays){
			LocalDate dateTracker = startDate;
			int chargeDays = currentChargeDays;
			for(int count = 0; count < rentalDayCount; count++){
				if(dateTracker.getDayOfWeek() == DayOfWeek.SUNDAY){
					chargeDays = chargeDays - 1;
				}
				dateTracker = dateTracker.plusDays(1);
			}
			return chargeDays;
		}
	},
	LABORDAY{
		public int calculateChargeDays(int rentalDayCount, LocalDate startDate, int currentChargeDays){
			LocalDate observedDate = LocalDate.of(startDate.getYear(), Month.SEPTEMBER, 1);
			while(observedDate.getDayOfWeek() != DayOfWeek.MONDAY){
				observedDate = observedDate.plusDays(1);
			}
			
			LocalDate dateTracker = startDate;
			int chargeDays = currentChargeDays;
			for(int count = 0; count < rentalDayCount; count++){
				if(dateTracker.isEqual(observedDate)){
					chargeDays = chargeDays - 1;
				}
				dateTracker = dateTracker.plusDays(1);
			}
			return chargeDays;
		}		
	},
	INDEPENDENCEDAY{
		public int calculateChargeDays(int rentalDayCount, LocalDate startDate, int currentChargeDays){
			LocalDate observedDate = LocalDate.of(startDate.getYear(), Month.JULY, 4);
			if(observedDate.getDayOfWeek() != DayOfWeek.SATURDAY && observedDate.getDayOfWeek() != DayOfWeek.SUNDAY){
				// do nothing - observed date is already correct.
			}
			else if(observedDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
				observedDate = observedDate.minusDays(1);
			} else  {
				observedDate = observedDate.plusDays(1);
			}
			
			LocalDate dateTracker = startDate;
			int chargeDays = currentChargeDays;
			for(int count = 0; count < rentalDayCount; count++){
				if(dateTracker.isEqual(observedDate)){
					chargeDays = chargeDays - 1;
				}
				dateTracker = dateTracker.plusDays(1);
			}
			return chargeDays;
		}		
	};
	
	public abstract int calculateChargeDays(int rentalDayCount, LocalDate startDate, int currentChargeDays);
}
