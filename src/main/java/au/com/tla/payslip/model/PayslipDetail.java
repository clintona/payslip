package au.com.tla.payslip.model;

import java.math.BigDecimal;

public class PayslipDetail {

    private PayrollDetail payrollDetail;

    private BigDecimal incomeTax;
    private BigDecimal grossIncome;
    // nb: netIncome is calculated
    private BigDecimal superannuation;

    public PayslipDetail() {
        this.payrollDetail = new PayrollDetail();
    }

    public PayrollDetail getPayrollDetail() {
        return payrollDetail;
    }

    public void setPayrollDetail(PayrollDetail payrollDetail) {
        if (payrollDetail == null) {
            throw new NullPointerException();
        }
        this.payrollDetail = payrollDetail;
    }

    public BigDecimal getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    public BigDecimal getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(BigDecimal grossIncome) {
        this.grossIncome = grossIncome;
    }

    public BigDecimal getSuperannuation() {
        return superannuation;
    }

    public void setSuperannuation(BigDecimal superannuation) {
        this.superannuation = superannuation;
    }

    public BigDecimal getNetIncome() {
        return grossIncome.subtract(incomeTax);
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append(payrollDetail.getFirstName()).append(" ").append(payrollDetail.getLastName());
        buf.append(",").append(payrollDetail.getPeriod());
        buf.append(",").append(grossIncome);
        buf.append(",").append(incomeTax);
        buf.append(",").append(getNetIncome());
        buf.append(",").append(superannuation);
        return buf.toString();
    }

}
