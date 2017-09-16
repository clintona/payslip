package au.com.tla.payslip.service;

import au.com.tla.payslip.model.TaxRate;

/**
 * Load an array of TaxRates using a fixed (hard-coded) set of rates based on the 2013 Australian tax year.
 * 
 * @author Clinton
 *
 */
public class FixedTaxRateLoader implements TaxRateLoader {

	public TaxRateService load() {
		TaxRate[] rates = new TaxRate[] {
			new TaxRate(0, 18200, 0, 0.0),
			new TaxRate(18201, 37000, 0, 0.19),
			new TaxRate(37001, 80000, 3572, 0.325),
			new TaxRate(80001, 180000, 17547, 0.37),
			new TaxRate(180001, 99999999, 54547, 0.45)
		};
		return new TaxRateService(rates);
	}
}
