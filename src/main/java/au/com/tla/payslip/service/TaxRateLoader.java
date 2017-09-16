package au.com.tla.payslip.service;


/** 
 * A Factory interface for loading the TaxRateService.
 * E.g: from a CSV file, or a hard-coded (fixed) table.
 * 
 * @author Clinton
 *
 */
public interface TaxRateLoader {
	
	TaxRateService load();

}
