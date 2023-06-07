package ClassesDAO;

import basicClasses.DoctorsSchedule;
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
            pstmt.close();
        }
    }

    public static void insertAppointment(DoctorsSchedule schedule) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into patients_appointments (id_doctor, id_patient, hour, minute) values (?, ?, ?, ?)")) {
            pstmt.setInt(1, schedule.getIdDoctor());
            pstmt.setInt(2, schedule.getIdPatient());
            pstmt.setInt(3, schedule.getHour());
            pstmt.setInt(4, schedule.getMinute());
            pstmt.executeUpdate();
            Database.getConnection().commit();
            pstmt.close();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from patients where name='" + name + "'")) {
            stmt.close();
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public static String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from patients where id='" + id + "'")) {
            stmt.close();//TODO: close result set??
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public void deleteMichaelJackson() throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String sql = "delete from patients where name = 'Michael Jackson'";
        int nbRowsAffected = stmt.executeUpdate(sql);
        stmt.close();
    }
}
