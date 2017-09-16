package au.com.tla.payslip.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SuperannuationService {

	public static double DEFAULT_SUPER_RATE = 0.09;
	private double rate;

	public SuperannuationService() {
		this.rate = DEFAULT_SUPER_RATE;
	}

	public SuperannuationService(double rate) {
		this.rate = rate;
	}
	
	public double getRate() {
		return rate;
	}


	public void setRate(double rate) {
		this.rate = rate;
	}

	
	public BigDecimal calculateMonthlySuper(int grossMonthlyIncome) {
		return calculateMonthlySuper(new BigDecimal(grossMonthlyIncome));
	}
	
	public BigDecimal calculateMonthlySuper(BigDecimal grossMonthlyIncome) {
		BigDecimal result = grossMonthlyIncome.multiply(new BigDecimal(rate));
		return result.setScale(0, RoundingMode.HALF_UP);
	}

	public BigDecimal calculateMonthlySuper(BigDecimal grossMonthlyIncome, BigDecimal rate) {
		BigDecimal result = grossMonthlyIncome.multiply(rate);
		return result.setScale(0, RoundingMode.HALF_UP);
	}

}
