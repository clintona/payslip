package au.com.tla.payslip.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import au.com.tla.payslip.service.IncomeTaxService;
import au.com.tla.payslip.service.TaxRateService;

public class IncomeTaxCalculatorTest {

	private IncomeTaxService calculator;
	
	@Before
	public void setUp() throws Exception {
		TaxRateService taxTable = new FixedTaxRateLoader().load();
		this.calculator = new IncomeTaxService(taxTable);
	}

	@Test
	public void testCalculateMonthlyIncomeTax() {
		BigDecimal tax = calculator.calculateMonthlyIncomeTax(60050);
		assertEquals(new BigDecimal(922), tax);
	}

	@Test
	public void testCalculateMonthlyGrossIncome() {
		BigDecimal monthlyIncome = calculator.calculateMonthlyGrossIncome(60050);
		assertEquals(new BigDecimal(5004), monthlyIncome);
	}

	@Test
	public void testCalculateMonthlyNetIncome() {
		BigDecimal monthlyNetIncome = calculator.calculateMonthlyNetIncome(60050);
		assertEquals(new BigDecimal(4082), monthlyNetIncome);
	}

	@Test(expected=NullPointerException.class)
	public void testNullTaxTable() {
		new IncomeTaxService((TaxRateLoader)null);
	}
}
