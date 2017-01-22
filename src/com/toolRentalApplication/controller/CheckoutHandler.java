package com.toolRentalApplication.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class CheckoutHandler {
	public List<Tool> toolList;
	public DateTimeFormatter dateTimeFormatter;
	
	public CheckoutHandler(){
		this.toolList = new ArrayList<Tool>(){
			private static final long serialVersionUID = 1L;

			{
				add(new Tool("LADW", "Werner", ToolType.LADDER)); 
				add(new Tool("CHNS", "Stihl", ToolType.CHAINSAW));
				add(new Tool("JAKR", "Ridgid", ToolType.JACKHAMMER));
				add(new Tool("JAKD", "DeWalt", ToolType.JACKHAMMER));
			}
		};
		
		this.dateTimeFormatter =DateTimeFormatter.ofPattern("MM/dd/yy");		
	}

	public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) throws RentalAgreementException
	{
		// check for exceptions
		if(rentalDayCount < 1){
			throw new RentalAgreementException("Rental day count must be greater than or equal to 1 day. Please enter a valid rental day count.");
		}
		if(discountPercent < 0 || discountPercent > 100){
			throw new RentalAgreementException("Discount percentage must be between 0 and 100. Please enter a valid discount percentage.");
		}
		
		// calculate inputed percent as a decimal for calculations
		BigDecimal decimalPercent = new BigDecimal(discountPercent).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);		
		
		// set initial values of rental agreement
		RentalAgreement rentalAgreement = new RentalAgreement();
		rentalAgreement.toolCode = toolCode;
		rentalAgreement.rentalDays = rentalDayCount;
		rentalAgreement.checkoutDate = checkoutDate.format(this.dateTimeFormatter);
		rentalAgreement.discountPercentage = NumberFormat.getPercentInstance(Locale.US).format(decimalPercent);
		
		// no exceptions! get tool information by querying the list of tools
		Tool toolInfo = this.toolList.stream()
				.filter(obj -> Objects.equals(obj.code, rentalAgreement.toolCode))
				.findFirst()
				.get();
		
		// set daily charge
		BigDecimal dailyCharge = toolInfo.toolType.charge();
		
		// calculate the number of days to be charged
		List<NoChargeDays> noChargeDays = toolInfo.toolType.noChargeDays();
		int chargeDays = rentalAgreement.rentalDays;
		for(NoChargeDays noChargeDay : noChargeDays){
			chargeDays = noChargeDay.calculateChargeDays(rentalAgreement.rentalDays, checkoutDate, chargeDays);
		}

		// calculate discount total and final charge
		BigDecimal preDiscountTotal = dailyCharge.multiply(new BigDecimal(chargeDays)).setScale(2, RoundingMode.HALF_UP);		
		BigDecimal discountAmount = preDiscountTotal.multiply(decimalPercent).setScale(2, RoundingMode.HALF_UP);		
		BigDecimal finalCharge = preDiscountTotal.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);		
		
		// set values on rental agreement
		rentalAgreement.toolType = toolInfo.toolType.getName();
		rentalAgreement.toolBrand = toolInfo.brand;		
		rentalAgreement.dueDate = checkoutDate.plusDays(rentalAgreement.rentalDays).format(this.dateTimeFormatter);
		rentalAgreement.dailyCharge = NumberFormat.getCurrencyInstance().format(dailyCharge);
		rentalAgreement.chargeDays = chargeDays;
		rentalAgreement.preDiscountTotal = NumberFormat.getCurrencyInstance().format(preDiscountTotal);
		rentalAgreement.discountAmount = NumberFormat.getCurrencyInstance().format(discountAmount);
		rentalAgreement.finalCharge = NumberFormat.getCurrencyInstance().format(finalCharge);
		return rentalAgreement;			
	}	
}
