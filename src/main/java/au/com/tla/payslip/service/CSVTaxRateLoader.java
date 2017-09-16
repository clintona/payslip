package au.com.tla.payslip.service;

import java.util.ArrayList;

import au.com.tla.payslip.integration.CSVFileParser;
import au.com.tla.payslip.model.TaxRate;

/**
 * Load a Table of TaxRates from a CSV file. The expected format is:
 * <table border="1"><tr>
 * <td>Minimum salary</td>
 * <td>Maximum salary</td>
 * <td>base tax payable</td>
 * <td>additional tax rate</td>
 * </tr> </table> 
 * @author Clinton
 *
 */
public class CSVTaxRateLoader implements TaxRateLoader {

	private String csvFileName;
	private CSVFileParser csvFileParser;
	
	public CSVTaxRateLoader(String filename) {
		// Create a default CSVFileParser else use the setter post constructor
		this.csvFileName = filename;
		this.setCsvFileParser(new CSVFileParser());
	}
	
	
	public TaxRateService load() {
		ArrayList<TaxRate> tiers = new ArrayList<TaxRate>();
		String[][] rawData = csvFileParser.parseFile(this.csvFileName);
		for (String[] tierArray : rawData) {
			tiers.add(createTier(tierArray));
		}
		return new TaxRateService(tiers.toArray(new TaxRate[0]));
	}

	
	
	protected TaxRate createTier(String[] csvField) {
		TaxRate tier = new TaxRate();
		tier.setMin(Integer.parseInt(csvField[0]));
		tier.setMax(Integer.parseInt(csvField[1]));
		tier.setBase(Integer.parseInt(csvField[2]));
		tier.setRate(Double.parseDouble(csvField[3]));
		return tier;
	}
	
	
	public void setCsvFileParser(CSVFileParser csvFileParser) {
		this.csvFileParser = csvFileParser;
	}


	public String getCsvFileName() {
		return csvFileName;
	}


	public void setCsvFileName(String csvFileName) {
		this.csvFileName = csvFileName;
	}

}
