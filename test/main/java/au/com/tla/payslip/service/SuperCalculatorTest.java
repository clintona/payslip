package au.com.tla.payslip.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import au.com.tla.payslip.service.SuperannuationService;

public class SuperCalculatorTest {

	private static final double SUPER_RATE = 0.09;
	private SuperannuationService superannuationService;
	
	@Before
	public void setUp() throws Exception {
		this.superannuationService = new SuperannuationService(SUPER_RATE);
	}

	@Test
	public void testCalculateMonthlySuper() {
		assertEquals(new BigDecimal(450), superannuationService.calculateMonthlySuper(5004));
	}

	@Test
	public void testCalculateMonthlySuperWithRate() {
		assertEquals(new BigDecimal(1000), superannuationService.calculateMonthlySuper(new BigDecimal(10000), new BigDecimal(0.1)));
	}

}
