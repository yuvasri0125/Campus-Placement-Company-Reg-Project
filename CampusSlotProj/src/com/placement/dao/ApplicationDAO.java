package com.placement.dao;

import com.placement.bean.Application;

import com.placement.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplicationDAO {

	public int generateApplicationID() throws Exception {
	    Connection con = DBUtil.getDBConnection();
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(
	        "SELECT APPLICATION_SEQ.NEXTVAL FROM DUAL"
	    );
	    rs.next();
	    int id = rs.getInt(1);
	    con.close();
	    return id;
	}

    public boolean insertApplication(Application a) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
        	    "INSERT INTO APPLICATION_TBL " +
        	    "(APPLICATION_ID, STUDENT_REG_NO, COMPANY_ID, APPLIED_DATE, " +
        	    "APPLICATION_STATUS, INTERVIEW_DATE, INTERVIEW_SLOT, INTERVIEW_PANEL, FINAL_OUTCOME) " +
        	    "VALUES (?,?,?,?,?,?,?,?,?)"
        	);


        ps.setInt(1, a.getApplicationID());
        ps.setString(2, a.getStudentRegNo());
        ps.setString(3, a.getCompanyID());
        ps.setDate(4, new java.sql.Date(a.getAppliedDate().getTime()));
        ps.setString(5, a.getApplicationStatus());
        ps.setDate(6, null); // interview date initially null
        ps.setString(7, null);
        ps.setString(8, null);
        ps.setString(9, null);

        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    }

    public Application findApplication(int applicationID) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM APPLICATION_TBL WHERE APPLICATION_ID=?");
        ps.setInt(1, applicationID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Application a = new Application();
            a.setApplicationID(rs.getInt(1));
            a.setStudentRegNo(rs.getString(2));
            a.setCompanyID(rs.getString(3));
            a.setAppliedDate(rs.getDate(4));
            a.setApplicationStatus(rs.getString(5));
            a.setInterviewDate(rs.getDate(6));
            a.setInterviewSlot(rs.getString(7));
            a.setInterviewPanel(rs.getString(8));
            a.setFinalOutcome(rs.getString(9));
            con.close();
            return a;
        }
        con.close();
        return null;
    }

    public boolean updateApplicationInterview(int applicationID, java.sql.Date interviewDate,
                                              String interviewSlot, String panel, String status) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "UPDATE APPLICATION_TBL SET INTERVIEW_DATE=?, INTERVIEW_SLOT=?, INTERVIEW_PANEL=?, APPLICATION_STATUS=? WHERE APPLICATION_ID=?");
        ps.setDate(1, interviewDate);
        ps.setString(2, interviewSlot);
        ps.setString(3, panel);
        ps.setString(4, status);
        ps.setInt(5, applicationID);
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    }

    public Application findExistingApplication(String studentRegNo, String companyID) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM APPLICATION_TBL WHERE STUDENT_REG_NO=? AND COMPANY_ID=?");
        ps.setString(1, studentRegNo);
        ps.setString(2, companyID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Application a = new Application();
            a.setApplicationID(rs.getInt(1));
            con.close();
            return a;
        }
        con.close();
        return null;
    }

    public List<Application> findInterviewConflicts(String studentRegNo, Date interviewDate, String interviewSlot) throws Exception {
        List<Application> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM APPLICATION_TBL WHERE STUDENT_REG_NO=? AND INTERVIEW_DATE=? AND INTERVIEW_SLOT=?");
        ps.setString(1, studentRegNo);
        ps.setDate(2, new java.sql.Date(interviewDate.getTime()));
        ps.setString(3, interviewSlot);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Application a = new Application();
            a.setApplicationID(rs.getInt(1));
            list.add(a);
        }
        con.close();
        return list;
    }

    public boolean updateApplicationOutcome(int applicationID, String finalOutcome, String newStatus) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "UPDATE APPLICATION_TBL SET FINAL_OUTCOME=?, APPLICATION_STATUS=? WHERE APPLICATION_ID=?");
        ps.setString(1, finalOutcome);
        ps.setString(2, newStatus);
        ps.setInt(3, applicationID);
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    }

    public List<Application> findActiveApplicationsForStudent(String studentRegNo) throws Exception {
        List<Application> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM APPLICATION_TBL WHERE STUDENT_REG_NO=? AND APPLICATION_STATUS IN ('APPLIED','INTERVIEW_SCHEDULED')");
        ps.setString(1, studentRegNo);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Application a = new Application();
            a.setApplicationID(rs.getInt(1));
            a.setStudentRegNo(rs.getString(2));
            a.setCompanyID(rs.getString(3));
            a.setAppliedDate(rs.getDate(4));
            a.setApplicationStatus(rs.getString(5));
            a.setInterviewDate(rs.getDate(6));
            a.setInterviewSlot(rs.getString(7));
            a.setInterviewPanel(rs.getString(8));
            a.setFinalOutcome(rs.getString(9));
            list.add(a);
        }
        con.close();
        return list;
    }

    public List<Application> findActiveApplicationsForCompany(String companyID) throws Exception {
        List<Application> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM APPLICATION_TBL WHERE COMPANY_ID=? AND APPLICATION_STATUS IN ('APPLIED','INTERVIEW_SCHEDULED')");
        ps.setString(1, companyID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Application a =new Application();
            a.setApplicationID(rs.getInt(1));
            a.setStudentRegNo(rs.getString(2));
            a.setCompanyID(rs.getString(3));
            a.setAppliedDate(rs.getDate(4));
            a.setApplicationStatus(rs.getString(5));
            a.setInterviewDate(rs.getDate(6));
            a.setInterviewSlot(rs.getString(7));
            a.setInterviewPanel(rs.getString(8));
            a.setFinalOutcome(rs.getString(9));
            list.add(a);
        }
        con.close();
        return list;
    }
}
