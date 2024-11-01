package main.java.com.insuranceagency;

import java.util.Date;

public class InsurancePolicy {
    private int policyId;
    private int customerId;
    private String policyNumber;
    private String policyType;
    private double coverageAmount;
    private Date startDate;
    private Date endDate;

    // Constructor
    public InsurancePolicy(int policyId, int customerId, String policyNumber, String policyType,
                           double coverageAmount, Date startDate, Date endDate) {
        this.policyId = policyId;
        this.customerId = customerId;
        this.policyNumber = policyNumber;
        this.policyType = policyType;
        this.coverageAmount = coverageAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public int getPolicyId() { return policyId; }
    public void setPolicyId(int policyId) { this.policyId = policyId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }

    public String getPolicyType() { return policyType; }
    public void setPolicyType(String policyType) { this.policyType = policyType; }

    public double getCoverageAmount() { return coverageAmount; }
    public void setCoverageAmount(double coverageAmount) { this.coverageAmount = coverageAmount; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
}