package com.placement.dao;

import com.placement.bean.Company;
import com.placement.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    public Company findCompany(String companyID) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM COMPANY_TBL WHERE COMPANY_ID=?");
        ps.setString(1, companyID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Company c = new Company();
            c.setCompanyID(rs.getString(1));
            c.setCompanyName(rs.getString(2));
            c.setJobRole(rs.getString(3));
            c.setCtcLpa(rs.getBigDecimal(4));
            c.setEligibleBranches(rs.getString(5));
            c.setMinimumCgpa(rs.getBigDecimal(6));
            c.setDriveDate(rs.getDate(7));
            c.setStatus(rs.getString(8));
            return c;
        }
        con.close();
        return null;
    }

    public List<Company> viewAllCompanies() throws Exception {
        List<Company> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM COMPANY_TBL");

        while (rs.next()) {
            Company c = new Company();
            c.setCompanyID(rs.getString(1));
            c.setCompanyName(rs.getString(2));
            c.setJobRole(rs.getString(3));
            c.setCtcLpa(rs.getBigDecimal(4));
            c.setEligibleBranches(rs.getString(5));
            c.setMinimumCgpa(rs.getBigDecimal(6));
            c.setDriveDate(rs.getDate(7));
            c.setStatus(rs.getString(8));
            list.add(c);
        }
        con.close();
        return list;
    }

    public List<Company> viewOpenCompanies() throws Exception {
        List<Company> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM COMPANY_TBL WHERE STATUS='OPEN'");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Company c = new Company();
            c.setCompanyID(rs.getString(1));
            c.setCompanyName(rs.getString(2));
            c.setJobRole(rs.getString(3));
            c.setCtcLpa(rs.getBigDecimal(4));
            c.setEligibleBranches(rs.getString(5));
            c.setMinimumCgpa(rs.getBigDecimal(6));
            c.setDriveDate(rs.getDate(7));
            c.setStatus(rs.getString(8));
            list.add(c);
        }
        con.close();
        return list;
    }

    public boolean insertCompany(Company company) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO COMPANY_TBL VALUES (?,?,?,?,?,?,?,?)");
        ps.setString(1, company.getCompanyID());
        ps.setString(2, company.getCompanyName());
        ps.setString(3, company.getJobRole());
        ps.setBigDecimal(4, company.getCtcLpa());
        ps.setString(5, company.getEligibleBranches());
        ps.setBigDecimal(6, company.getMinimumCgpa());
        ps.setDate(7, company.getDriveDate());
        ps.setString(8, company.getStatus());
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    }

    public boolean updateCompanyStatus(String companyID, String status) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE COMPANY_TBL SET STATUS=? WHERE COMPANY_ID=?");
        ps.setString(1, status);
        ps.setString(2, companyID);
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    }

    public boolean deleteCompany(String companyID) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM COMPANY_TBL WHERE COMPANY_ID=?");
        ps.setString(1, companyID);
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    }
}
