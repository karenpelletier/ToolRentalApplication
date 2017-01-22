package com.toolRentalApplication.junit;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.toolRentalApplication.controller.CheckoutHandler;
import com.toolRentalApplication.controller.RentalAgreement;
import com.toolRentalApplication.controller.RentalAgreementException;

public class ToolRentalApplicationTest {
	
	private CheckoutHandler checkoutHandler = new CheckoutHandler();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testCase1() throws RentalAgreementException
	{
		thrown.expect(RentalAgreementException.class);
		thrown.expectMessage("Discount percentage must be between 0 and 100. Please enter a valid discount percentage.");
		
		this.checkoutHandler.checkout("JAKR", 5, 101, LocalDate.of(2015, 9, 3));		
	}
	
	@Test
	public void testCase2() throws RentalAgreementException
	{
		RentalAgreement result = this.checkoutHandler.checkout("LADW", 5, 10, LocalDate.of(2015, 9, 3));
		
		assertEquals("09/08/15", result.dueDate);
		assertEquals("$1.99", result.dailyCharge);
		assertEquals(5, result.chargeDays);
		assertEquals("$9.95", result.preDiscountTotal);
		assertEquals("10%", result.discountPercentage);
		assertEquals("$1.00", result.discountAmount);
		assertEquals("$8.95", result.finalCharge);
	}
	
	@Test
	public void testCase3() throws RentalAgreementException
	{
		RentalAgreement result = this.checkoutHandler.checkout("CHNS", 5, 25, LocalDate.of(2015, 7, 2));
		
		assertEquals("07/07/15", result.dueDate);
		assertEquals("$1.49", result.dailyCharge);
		assertEquals(3, result.chargeDays);
		assertEquals("$4.47", result.preDiscountTotal);
		assertEquals("25%", result.discountPercentage);
		assertEquals("$1.12", result.discountAmount);
		assertEquals("$3.35", result.finalCharge);
	}
	
	@Test
	public void testCase4() throws RentalAgreementException
	{
		RentalAgreement result = this.checkoutHandler.checkout("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
		
		assertEquals("09/09/15", result.dueDate);
		assertEquals("$2.99", result.dailyCharge);
		assertEquals(3, result.chargeDays);
		assertEquals("$8.97", result.preDiscountTotal);
		assertEquals("0%", result.discountPercentage);
		assertEquals("$0.00", result.discountAmount);
		assertEquals("$8.97", result.finalCharge);
	}
	
	@Test
	public void testCase5() throws RentalAgreementException
	{
		RentalAgreement result = this.checkoutHandler.checkout("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
		
		assertEquals("07/11/15", result.dueDate);
		assertEquals("$2.99", result.dailyCharge);
		assertEquals(6, result.chargeDays);
		assertEquals("$17.94", result.preDiscountTotal);
		assertEquals("0%", result.discountPercentage);
		assertEquals("$0.00", result.discountAmount);
		assertEquals("$17.94", result.finalCharge);
	}
	
	@Test
	public void testCase6() throws RentalAgreementException
	{
		RentalAgreement result = this.checkoutHandler.checkout("JAKR", 4, 50, LocalDate.of(2020, 7, 2));
		
		assertEquals("07/06/20", result.dueDate);
		assertEquals("$2.99", result.dailyCharge);
		assertEquals(1, result.chargeDays);
		assertEquals("$2.99", result.preDiscountTotal);
		assertEquals("50%", result.discountPercentage);
		assertEquals("$1.50", result.discountAmount);
		assertEquals("$1.49", result.finalCharge);
	}	
}
