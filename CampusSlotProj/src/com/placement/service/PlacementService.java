package com.placement.service;

import com.placement.bean.Application;
import com.placement.bean.Company;
import com.placement.bean.Student;
import com.placement.dao.ApplicationDAO;
import com.placement.dao.CompanyDAO;
import com.placement.dao.StudentDAO;
import com.placement.util.EligibilityViolationException;
import com.placement.util.ValidationException;

import java.sql.Date;
import java.util.List;

public class PlacementService {

    private StudentDAO studentDAO = new StudentDAO();
    private CompanyDAO companyDAO = new CompanyDAO();
    private ApplicationDAO applicationDAO = new ApplicationDAO();

 
    public boolean registerNewStudent(Student student) throws ValidationException, Exception {
        if (student == null || student.getStudentRegNo() == null || student.getFullName() == null
                || student.getBranch() == null || student.getCgpa() == null) {
            throw new ValidationException("Student details missing");
        }
        if (studentDAO.findStudent(student.getStudentRegNo()) != null) {
            throw new ValidationException("Student already exists");
        }
        return studentDAO.insertStudent(student);
    }

    public Student viewStudent(String studentRegNo) throws Exception {
        return studentDAO.findStudent(studentRegNo);
    }

    public List<Student> viewAllStudents() throws Exception {
        return studentDAO.viewAllStudents();
    }

    public boolean updateStudentStatus(String studentRegNo, String status) throws Exception {
        return studentDAO.updateStudentStatus(studentRegNo, status);
    }

    
    public boolean registerCompanyDrive(Company company) throws ValidationException, Exception {
        if (company == null || company.getCompanyID() == null || company.getCompanyName() == null
                || company.getMinimumCgpa() == null) {
            throw new ValidationException("Company details missing");
        }
        if (companyDAO.findCompany(company.getCompanyID()) != null) {
            throw new ValidationException("Company already exists");
        }
        return companyDAO.insertCompany(company);
    }

    public Company viewCompany(String companyID) throws Exception {
        return companyDAO.findCompany(companyID);
    }

    public List<Company> viewAllCompanies() throws Exception {
        return companyDAO.viewAllCompanies();
    }

    public List<Company> viewOpenCompanies() throws Exception {
        return companyDAO.viewOpenCompanies();
    }

   
    public boolean applyForCompany(String studentRegNo, String companyID, Date appliedDate)
            throws ValidationException, EligibilityViolationException, Exception {

        Student student = studentDAO.findStudent(studentRegNo);
        if (student == null) throw new ValidationException("Student not found");

        Company company = companyDAO.findCompany(companyID);
        if (company == null) throw new ValidationException("Company not found");

        if (student.getCgpa().compareTo(company.getMinimumCgpa()) < 0)
            throw new EligibilityViolationException("CGPA criteria not met");

        if (applicationDAO.findExistingApplication(studentRegNo, companyID) != null)
            throw new ValidationException("Already applied for this company");

        Application application = new Application();
        application.setApplicationID(applicationDAO.generateApplicationID());
        application.setStudentRegNo(studentRegNo);
        application.setCompanyID(companyID);
        application.setAppliedDate(appliedDate);
        application.setApplicationStatus("APPLIED");

        return applicationDAO.insertApplication(application);
    }

    public boolean scheduleInterview(int applicationID, Date interviewDate, String interviewSlot, String panel)
            throws ValidationException, Exception {

        Application application = applicationDAO.findApplication(applicationID);
        if (application == null) throw new ValidationException("Application not found");

        List<Application> conflicts = applicationDAO.findInterviewConflicts(
                application.getStudentRegNo(), interviewDate, interviewSlot);

        if (!conflicts.isEmpty()) throw new ValidationException("Interview slot conflict");

        return applicationDAO.updateApplicationInterview(applicationID, interviewDate, interviewSlot, panel,
                "INTERVIEW_SCHEDULED");
    }

    public boolean updateFinalOutcome(int applicationID, String finalOutcome) throws ValidationException, Exception {
        Application application = applicationDAO.findApplication(applicationID);
        if (application == null) throw new ValidationException("Application not found");

        String newStatus;
        if ("SELECTED".equalsIgnoreCase(finalOutcome)) newStatus = "SELECTED";
        else if ("REJECTED".equalsIgnoreCase(finalOutcome)) newStatus = "REJECTED";
        else newStatus = "WAITLIST";

        return applicationDAO.updateApplicationOutcome(applicationID, finalOutcome, newStatus);
    }

    public List<Application> viewActiveApplicationsForStudent(String studentRegNo) throws Exception {
        return applicationDAO.findActiveApplicationsForStudent(studentRegNo);
    }

    public List<Application> viewActiveApplicationsForCompany(String companyID) throws Exception {
        return applicationDAO.findActiveApplicationsForCompany(companyID);
    }
}
