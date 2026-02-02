package com.placement.bean;
import java.math.BigDecimal;

public class Student {
    private String studentRegNo;
    private String fullName;
    private String branch;
    private int currentSemester;
    private BigDecimal cgpa;
    private String email;
    private String mobile;
    private String status;
	public String getStudentRegNo() {
		return studentRegNo;
	}
	public void setStudentRegNo(String studentRegNo) {
		this.studentRegNo = studentRegNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getCurrentSemester() {
		return currentSemester;
	}
	public void setCurrentSemester(int currentSemester) {
		this.currentSemester = currentSemester;
	}
	public BigDecimal getCgpa() {
		return cgpa;
	}
	public void setCgpa(BigDecimal cgpa) {
		this.cgpa = cgpa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
