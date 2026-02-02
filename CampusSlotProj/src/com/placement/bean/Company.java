package com.placement.bean;
import java.math.BigDecimal;
import java.sql.Date;

public class Company {
    private String companyID;
    private String companyName;
    private String jobRole;
    private BigDecimal ctcLpa;
    private String eligibleBranches;
    private BigDecimal minimumCgpa;
    private Date driveDate;
    private String status;
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	public BigDecimal getCtcLpa() {
		return ctcLpa;
	}
	public void setCtcLpa(BigDecimal ctcLpa) {
		this.ctcLpa = ctcLpa;
	}
	public String getEligibleBranches() {
		return eligibleBranches;
	}
	public void setEligibleBranches(String eligibleBranches) {
		this.eligibleBranches = eligibleBranches;
	}
	public BigDecimal getMinimumCgpa() {
		return minimumCgpa;
	}
	public void setMinimumCgpa(BigDecimal minimumCgpa) {
		this.minimumCgpa = minimumCgpa;
	}
	public Date getDriveDate() {
		return driveDate;
	}
	public void setDriveDate(Date driveDate) {
		this.driveDate = driveDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}