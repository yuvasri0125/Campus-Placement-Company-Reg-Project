package com.placement.dao;

import com.placement.bean.Student;
import com.placement.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public Student findStudent(String regNo) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM STUDENT_TBL WHERE STUDENT_REG_NO=?");
        ps.setString(1, regNo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Student s = new Student();
            s.setStudentRegNo(rs.getString(1));
            s.setFullName(rs.getString(2));
            s.setBranch(rs.getString(3));
            s.setCurrentSemester(rs.getInt(4));
            s.setCgpa(rs.getBigDecimal(5));
            s.setEmail(rs.getString(6));
            s.setMobile(rs.getString(7));
            s.setStatus(rs.getString(8));
            con.close();
            return s;
        }
        con.close();
        return null;
    }

    public boolean insertStudent(Student s) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO STUDENT_TBL VALUES(?,?,?,?,?,?,?,?)");
        ps.setString(1, s.getStudentRegNo());
        ps.setString(2, s.getFullName());
        ps.setString(3, s.getBranch());
        ps.setInt(4, s.getCurrentSemester());
        ps.setBigDecimal(5, s.getCgpa());
        ps.setString(6, s.getEmail());
        ps.setString(7, s.getMobile());
        ps.setString(8, s.getStatus());
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    }

    public List<Student> viewAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM STUDENT_TBL");
        while (rs.next()) {
            Student s = new Student();
            s.setStudentRegNo(rs.getString(1));
            s.setFullName(rs.getString(2));
            s.setBranch(rs.getString(3));
            s.setCurrentSemester(rs.getInt(4));
            s.setCgpa(rs.getBigDecimal(5));
            s.setEmail(rs.getString(6));
            s.setMobile(rs.getString(7));
            s.setStatus(rs.getString(8));
            list.add(s);
        }
        con.close();
        return list;
    }

    public boolean updateStudentStatus(String regNo, String status) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "UPDATE STUDENT_TBL SET STATUS=? WHERE STUDENT_REG_NO=?");
        ps.setString(1, status);
        ps.setString(2, regNo);
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    }

    public boolean deleteStudent(String regNo) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "DELETE FROM STUDENT_TBL WHERE STUDENT_REG_NO=?");
        ps.setString(1, regNo);
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    }
}
