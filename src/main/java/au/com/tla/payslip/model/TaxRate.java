package au.com.tla.payslip.model;

/**
 * Model a rate of income tax for a particular tax bracket.
 * 
 * @author Clinton
 *
 */
public class TaxRate {
	private int min;
	private int max;
	private int base;
	private double rate;
	
	public TaxRate() {
		
	}
	
	
	public TaxRate(int min, int max, int base, double rate) {
		this.min = min;
		this.max = max;
		this.base = base;
		this.rate = rate;
	}
	
	public int getMin() {
		return min;
	}


	public void setMin(int min) {
		this.min = min;
	}


	public int getMax() {
		return max;
	}


	public void setMax(int max) {
		this.max = max;
	}


	public int getBase() {
		return base;
	}


	public void setBase(int base) {
		this.base = base;
	}


	public double getRate() {
		return rate;
	}


	public void setRate(double rate) {
		this.rate = rate;
	}

	public String toString() {
		return min + " - " + max + ": $" + base + " + " + rate;
	}
}
