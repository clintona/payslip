package au.com.tla.payslip.integration;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import au.com.tla.payslip.model.PayrollDetail;

/**
 * Load a CSV file of tax rates for a particular year.
 * @author Clinton
 *
 */
public class CSVPayrollLoader {

	private CSVFileParser csvFileParser;
	
	
	/** 
	 * Creates a default CSVFileParser. This can be overridden with {@link #setCsvFileParser(CSVFileParser)}
	 */
	public CSVPayrollLoader() {
		this.csvFileParser = new CSVFileParser();
	}
	
	
	public List<PayrollDetail> load(String filename) throws IOException {
		List<PayrollDetail> payroll = new ArrayList<PayrollDetail>();
		String[][] rawData = csvFileParser.parseFile(filename);
		for (String[] incomeData : rawData) {
			payroll.add(createPayrollDetail(incomeData));
		}
		return payroll;
	}
	
	private PayrollDetail createPayrollDetail(String[] incomeData) {
		PayrollDetail detail = new PayrollDetail();
		detail.setFirstName(incomeData[0]);
		detail.setLastName(incomeData[1]);
		detail.setAnnualSalary(Integer.parseInt(incomeData[2]));
		detail.setSuperannuation(new BigDecimal(incomeData[3]));
		detail.setPeriod(incomeData[4]);
		return detail;
	}


	/** This method is provided for injection of dependencies. */
	public void setCsvFileParser(CSVFileParser csvFileParser) {
		this.csvFileParser = csvFileParser;
	}
	
}
