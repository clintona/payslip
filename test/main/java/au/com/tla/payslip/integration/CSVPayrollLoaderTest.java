package au.com.tla.payslip.integration;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import au.com.tla.payslip.integration.CSVFileParser;
import au.com.tla.payslip.integration.CSVPayrollLoader;
import au.com.tla.payslip.model.PayrollDetail;

public class CSVPayrollLoaderTest {

	private static String PAY_PERIOD = "01 March – 31 March"; 
	
	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testLoad() throws IOException {
		// could use a mock framework like http://www.mockito.org, or EasyMock
		// but a stubbed method does the job 
		CSVFileParser mockParser = new CSVFileParser() {
			public String[][] parseFile(String filename) {
				return new String[][] {
					new String[] {"David", "Rudd", "60050", "0.09", PAY_PERIOD},
					new String[] {"Ryan", "Chen", "120000", "0.1", PAY_PERIOD}
				};
			}
		};
		
		CSVPayrollLoader loader = new CSVPayrollLoader();
		loader.setCsvFileParser(mockParser);
		
		List<PayrollDetail> payroll = loader.load("dummy.file");
		assertEquals(2, payroll.size());
		PayrollDetail detail = payroll.get(0);
		assertEquals("David", detail.getFirstName());
		assertEquals("Rudd", detail.getLastName());
		assertEquals(60050, detail.getAnnualSalary());
		assertEquals(new BigDecimal("0.09"), detail.getSuperannuation());
		assertEquals(PAY_PERIOD, detail.getPeriod());
		
		detail = payroll.get(1);
		assertEquals("Ryan", detail.getFirstName());
		assertEquals("Chen", detail.getLastName());
		assertEquals(120000, detail.getAnnualSalary());
		assertEquals(new BigDecimal("0.1"), detail.getSuperannuation());
		assertEquals(PAY_PERIOD, detail.getPeriod());
	}

}
