package au.com.tla.payslip.service;

import au.com.tla.payslip.model.TaxRate;

/**
 * This service returns the tax rates for a particular salary.
 * It's assumed that Date is irrelevant, although in practice, tax rates may change from year-year.
 * An improvement to this interface is to define a getTaxRate(int salary, Date date) method to cater
 * for taxation changes over time.
 * 
 * This class maintain a table (array) of income tax rates.
 * 
 * @author Clinton Arnall
 *
 */
public class TaxRateService {

	private TaxRate[] rates;
	
	/**
	 * Initialise the service with an empty set of TaxRates. 
	 */
	public TaxRateService() {
		this.setRates(new TaxRate[0]);
	}
	
	/**
	 * Initialise the service with the supplied array of TaxRates.
	 * @param array
	 */
	public TaxRateService(TaxRate[] array) {
		this.setRates(array);
	}

	public TaxRate[] getRates() {
		return rates;
	}

	public void setRates(TaxRate[] rates) {
		if (rates == null) {
			throw new NullPointerException("tax rates cannot be null");
		}
		this.rates = rates;
	}
	
	/**
	 * Return the TaxRate for the given annual gross salary.
	 * <br/>Note: in future, add a Date parameter to allow for tax changes over time.
	 * @param annualGrossSalary
	 * @return the TaxRate for the given annual gross salary.
	 */
	public TaxRate getTaxRate(int annualGrossSalary) {
		TaxRate result = null;
		for (TaxRate tier : rates) {
			if (annualGrossSalary >= tier.getMin() && annualGrossSalary <= tier.getMax()) {
				result = tier;
				break;
			}
		}
		if (result == null) {
			throw new IllegalStateException("Unable to calculate tax rate for annual salary $" + 
			annualGrossSalary + " - check the tax table (" + rates.length + " entries)");
		}
		return result;
	}
	
	
	
}
