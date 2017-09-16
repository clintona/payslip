package au.com.tla.payslip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.tla.payslip.integration.CSVPayrollLoader;
import au.com.tla.payslip.model.PayrollDetail;
import au.com.tla.payslip.model.PayslipDetail;
import au.com.tla.payslip.service.CSVTaxRateLoader;
import au.com.tla.payslip.service.IncomeTaxService;
import au.com.tla.payslip.service.SuperannuationService;
import au.com.tla.payslip.service.TaxRateLoader;

public class PayslipManager {

    private IncomeTaxService incomeTaxService;
    private SuperannuationService superService;

    public PayslipManager() {
    }

    /**
     * The main program needs the following arguments:
     * <ul>
     * <li>csv tax rate filename
     * <li>csv payroll filename
     * </ul>
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        checkArguments(args);
        PayslipManager payslipManager = init(args);
        payslipManager.processPayroll(args);
    }

    public static void checkArguments(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Usage: PayslipManager <tax_rate_file> <payroll_file>");
        }
    }

    /**
     * Return a fully configured PayslipManager instance.
     * 
     * @param args
     * @return a fully configured PayslipManager instance.
     */
    private static PayslipManager init(String[] args) {
        PayslipManager manager = new PayslipManager();

        // inject dependent services. Spring could do this instead.

        // load tax rates from a CSV file
        TaxRateLoader taxRateLoader = new CSVTaxRateLoader(args[0]);
        manager.setIncomeTaxService(new IncomeTaxService(taxRateLoader));
        manager.setSuperService(new SuperannuationService());
        return manager;
    }

    public void processPayroll(String[] args) throws IOException {
        List<PayrollDetail> payroll = new CSVPayrollLoader().load(args[1]);
        List<PayslipDetail> payslips = calculatePayslips(payroll);
        writePayslips(payslips);
    }

    public List<PayslipDetail> calculatePayslips(List<PayrollDetail> payroll) {
        List<PayslipDetail> payslips = new ArrayList<PayslipDetail>(payroll.size());
        for (PayrollDetail salary : payroll) {
            PayslipDetail payslip = calculatePayslip(salary);
            payslips.add(payslip);
        }
        return payslips;
    }

    private PayslipDetail calculatePayslip(PayrollDetail salary) {
        PayslipDetail payslip = new PayslipDetail();

        payslip.getPayrollDetail().setFirstName(salary.getFirstName());
        payslip.getPayrollDetail().setLastName(salary.getLastName());
        payslip.getPayrollDetail().setPeriod(salary.getPeriod());
        payslip.setGrossIncome(
                incomeTaxService.calculateMonthlyGrossIncome(salary.getAnnualSalary()));
        payslip.setIncomeTax(incomeTaxService.calculateMonthlyIncomeTax(salary.getAnnualSalary()));
        payslip.setSuperannuation(this.superService.calculateMonthlySuper(payslip.getGrossIncome(),
                salary.getSuperannuation()));
        return payslip;
    }

    /**
     * Output payslips to stddout.
     * In future, this could use a PayslipWriter or equivalent and write a to a variety
     * of output media or formats.
     * 
     * @param payslips
     */
    public void writePayslips(List<PayslipDetail> payslips) {
        for (PayslipDetail payslip : payslips) {
            System.out.println(payslip);
        }
    }

    public IncomeTaxService getIncomeTaxService() {
        return incomeTaxService;
    }

    public void setIncomeTaxService(IncomeTaxService incomeTaxService) {
        this.incomeTaxService = incomeTaxService;
    }

    public SuperannuationService getSuperService() {
        return superService;
    }

    public void setSuperService(SuperannuationService superService) {
        this.superService = superService;
    }
}