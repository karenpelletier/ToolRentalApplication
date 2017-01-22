package com.toolRentalApplication.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

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
