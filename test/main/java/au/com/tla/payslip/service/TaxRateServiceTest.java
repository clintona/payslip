package au.com.tla.payslip.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Test;

import au.com.tla.payslip.model.TaxRate;
import au.com.tla.payslip.service.TaxRateService;

public class TaxRateServiceTest {
	
	public static String TEST_TAX_FILE = "src/main/resources/2013_tax.csv";
	

	@Test
	public void testGetRates() {
		TaxRate[] rates = new TaxRate[1];
		rates[0] = new TaxRate();
		
		TaxRateService taxTable = new TaxRateService(rates);
		
		assertSame(rates, taxTable.getRates());
	}


	@Test(expected=NullPointerException.class)
	public void testSetRatesNull() {
		TaxRateService taxTable = new TaxRateService();
		taxTable.setRates(null);
	}

	
	@Test
	public void testGetTaxRate() throws IOException {
		TaxRateService taxTable = new FixedTaxRateLoader().load();
		
		assertEquals(taxTable.getRates()[0], taxTable.getTaxRate(0));
		assertEquals(taxTable.getRates()[0], taxTable.getTaxRate(18200));
		assertEquals(taxTable.getRates()[1], taxTable.getTaxRate(18201));
		assertEquals(taxTable.getRates()[2], taxTable.getTaxRate(40000));
		assertEquals(taxTable.getRates()[3], taxTable.getTaxRate(90000));
		assertEquals(taxTable.getRates()[4], taxTable.getTaxRate(200000));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testGetTaxRateNegative() throws IOException {
		TaxRateService taxTable = new FixedTaxRateLoader().load();
		taxTable.getTaxRate(-1);
	}

	@Test(expected=IllegalStateException.class)
	public void testGetTaxRateInvalidTable() throws IOException {
		TaxRateService taxTable = new TaxRateService();
		taxTable.getTaxRate(20000);  // empty tax table causes error here
	}
	
}
