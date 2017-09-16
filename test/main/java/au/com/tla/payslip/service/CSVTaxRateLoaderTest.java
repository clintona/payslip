package au.com.tla.payslip.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import au.com.tla.payslip.model.TaxRate;
import au.com.tla.payslip.service.CSVTaxRateLoader;
import au.com.tla.payslip.service.TaxRateLoader;
import au.com.tla.payslip.service.TaxRateService;

public class CSVTaxRateLoaderTest {
	public static String TEST_TAX_FILE = "src/main/resources/2013_tax.csv";
			
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLoadSuccess() {
		CSVTaxRateLoader loader = new CSVTaxRateLoader(TEST_TAX_FILE);
		TaxRateService service = loader.load();
		assertNotNull(service);
		// verify 5 rate tiers were loaded from the file
		assertEquals(5, service.getRates().length);
	}

	@Test
	public void printLoadedRates() {
		TaxRateLoader loader = new CSVTaxRateLoader(TEST_TAX_FILE);
		TaxRateService TaxRateService = loader.load();
		
		for (TaxRate tier : TaxRateService.getRates()) {
			System.out.println(tier);
		}

	}
}
