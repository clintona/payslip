package au.com.tla.payslip.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import au.com.tla.payslip.model.TaxRate;

/**
 * Perform income tax and related calculations.
 * 
 * You can create an instance one of two ways: <ul>
 * <li> call {@link #IncomeTaxService(TaxRateLoader)} suppling a TaxRateLoader instance. 
 * This allows callers to load the service with TaxRates from either a CSV file 
 * {@link CSVTaxRateLoader} or using a hard-coded set of rates {@link FixedTaxRateLoader}
 * <li> call {{@link #IncomeTaxService(TaxRateService)} with a pre-loaded {@link TaxRateService}
 * </ul> 
 * @author Clinton
 *
 */
public class IncomeTaxService {

	private TaxRateService taxRateService;

	
	public IncomeTaxService(TaxRateService TaxRateService) {
		setTaxRateService(TaxRateService);
	}
	
	public IncomeTaxService(TaxRateLoader taxRateLoader) {
		setTaxRateService(taxRateLoader.load());
	}

	public void setTaxRateService(TaxRateService taxRateService) {
		if (taxRateService == null) {
			throw new NullPointerException("null tax table");
		}
		this.taxRateService = taxRateService;
	}
	
	
	public BigDecimal calculateMonthlyIncomeTax(int annualGrossSalary) {
		TaxRate rate = this.taxRateService.getTaxRate(annualGrossSalary);
		
		int prorataSalary = annualGrossSalary - rate.getMin() + 1;
		BigDecimal annualTax = new BigDecimal(rate.getBase() + prorataSalary * rate.getRate());
		return annualTax.divide(new BigDecimal(12), 0, RoundingMode.HALF_UP);
	}
	
	
	public BigDecimal calculateMonthlyGrossIncome(int annualGrossSalary) {
		return new BigDecimal(annualGrossSalary).divide(new BigDecimal(12), 0, RoundingMode.HALF_DOWN);
	}
	
	
	public BigDecimal calculateMonthlyNetIncome(int annualGrossSalary) {
		BigDecimal tax = calculateMonthlyIncomeTax(annualGrossSalary).setScale(0, RoundingMode.HALF_DOWN);
		return calculateMonthlyGrossIncome(annualGrossSalary).subtract(tax); 
	}
	
	
}
