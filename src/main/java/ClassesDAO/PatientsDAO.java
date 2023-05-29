package ClassesDAO;

import databaseStuff.Database;

import java.sql.*;

public class PatientsDAO {
    public void create(String name, boolean medicalInsurance) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into patients (name, medical_insurance) values (?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setBoolean(2, medicalInsurance);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from patients where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from patients where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public void deleteMichaelJackson() throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String sql = "delete from patients where name = 'Michael Jackson'";
        int nbRowsAffected = stmt.executeUpdate(sql);

    }
}
