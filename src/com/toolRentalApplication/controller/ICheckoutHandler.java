package com.toolRentalApplication.controller;

import java.time.LocalDate;

public interface ICheckoutHandler {
	public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) throws RentalAgreementException;
}
