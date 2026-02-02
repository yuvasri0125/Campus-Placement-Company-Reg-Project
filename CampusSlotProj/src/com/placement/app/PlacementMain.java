package com.placement.app;

import com.placement.bean.Company;
import com.placement.bean.Student;
import com.placement.service.PlacementService;
import com.placement.util.EligibilityViolationException;
import com.placement.util.ValidationException;

public class PlacementMain {
		 private static PlacementService service = new
		PlacementService();
		 public static void main(String[] args) {
			 PlacementService service = new PlacementService();
		 java.util.Scanner sc = new java.util.Scanner(System.in);
		 System.out.println("--- Campus Placement Management " +
                 "Console ---");

		
		 try {
		 Student s = new Student();
		 s.setStudentRegNo("21CSE050");
		 s.setFullName("Meenakshi Rao");
		 s.setBranch("CSE");
		 s.setCurrentSemester(7);
		 s.setCgpa(new java.math.BigDecimal("8.75"));
		 s.setEmail("21cse050@college.edu");
		 s.setMobile("9998887771");
		 s.setStatus("ACTIVE");
		 boolean ok = service.registerNewStudent(s);
		 System.out.println(ok ? "STUDENT REGISTERED" :
		"STUDENT REGISTRATION FAILED");
		 } catch (ValidationException e) {
		 System.out.println("Validation Error: " +
		e.toString());
		 } catch (Exception e) {
		 System.out.println("System Error: " +
		e.getMessage());
		 }
		
		 try {
		 Company c = new Company();
		 c.setCompanyID("CMP010");
		 c.setCompanyName("CodeCraft Labs Pvt Ltd");
		 c.setJobRole("Software Engineer - Trainee");
		 c.setCtcLpa(new java.math.BigDecimal("6.00"));
		 c.setEligibleBranches("CSE,IT");
		 c.setMinimumCgpa(new java.math.BigDecimal("7.50"));
		 c.setDriveDate(new
		java.sql.Date(System.currentTimeMillis()));
		 c.setStatus("OPEN");
		 boolean ok = service.registerCompanyDrive(c);
		 System.out.println(ok ? "COMPANY REGISTERED" :
		"COMPANY REGISTRATION FAILED");
		 } catch (ValidationException e) {
		 System.out.println("Validation Error: " +
		e.toString());
		 } catch (Exception e) {
		 System.out.println("System Error: " +
		e.getMessage());
		 }
		
		 try {
		 java.sql.Date appliedDate = new
		java.sql.Date(System.currentTimeMillis());
		 boolean ok = service.applyForCompany("21CSE050",
		"CMP010", appliedDate);
		 System.out.println(ok ? "APPLICATION SUBMITTED" :
		"APPLICATION FAILED");
		 } catch (EligibilityViolationException e) {
		 System.out.println("Eligibility Error: " +
		e.toString());
		 } catch (ValidationException e) {
		 System.out.println("Validation Error: " +
		e.toString());
		 } catch (Exception e) {
		 System.out.println("System Error: " + e.getMessage());
		 }
		 sc.close();
		 }
		}

