package com.toolRentalApplication.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* An ENUM to hold all of the ToolType Options
* Each item has methods to get the charge, the days that this type of rental should not be charged for and the name.
* 
* @author Karen Pelletier
* @version 1.0
*/
public enum ToolType {
	LADDER {
		public BigDecimal charge(){return new BigDecimal(1.99);}
		public List<NoChargeDays> noChargeDays(){return new ArrayList<NoChargeDays>();}
		public String getName() {return "Ladder";}		
	},
	CHAINSAW {
		public BigDecimal charge(){return new BigDecimal(1.49);}
		public List<NoChargeDays> noChargeDays() {
			List<NoChargeDays> noChargeDays = new ArrayList<>(Arrays.asList(NoChargeDays.SATURDAY, NoChargeDays.SUNDAY));
			return noChargeDays;			
		}
		public String getName() {return "Chainsaw";}
	},
	JACKHAMMER {
		public BigDecimal charge(){return new BigDecimal(2.99);}
		public List<NoChargeDays> noChargeDays() {
			List<NoChargeDays> noChargeDays = new ArrayList<>(Arrays.asList(NoChargeDays.SATURDAY, NoChargeDays.SUNDAY, NoChargeDays.INDEPENDENCEDAY, NoChargeDays.LABORDAY));
			return noChargeDays;
		}
		public String getName(){return "Jackhammer";}
	};
	
	public abstract BigDecimal charge();
	public abstract List<NoChargeDays> noChargeDays();
	public abstract String getName();

}
