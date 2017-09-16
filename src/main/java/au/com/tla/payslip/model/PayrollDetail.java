package au.com.tla.payslip.model;

import java.math.BigDecimal;

public class PayrollDetail {
	private String firstName;
	private String lastName;
	private String period;
	
	private int annualSalary;
	private BigDecimal superannuation;
	
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPeriod() {
		return period;
	}
	
	public void setPeriod(String period) {
		this.period = period;
	}
	
	public int getAnnualSalary() {
		return annualSalary;
	}
	
	public void setAnnualSalary(int annualSalary) {
		this.annualSalary = annualSalary;
	}

	public BigDecimal getSuperannuation() {
		return superannuation;
	}
	public void setSuperannuation(BigDecimal superannuation) {
		this.superannuation = superannuation;
	}
	
}
