package ClassesDAO;

import basicClasses.DoctorsSchedule;
import databaseStuff.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorsDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into doctors (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            pstmt.close();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from doctors where name='" + name + "'")) {
            stmt.close();
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public static String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                "select name from doctors where id='" + id + "'");
        if (rs.next()) {
            String rezultat = rs.getString(1);
//            con.close();
            return rezultat;
        } else {
//            con.close();
            return null;
        }
    }

    public static List<DoctorsSchedule> returnAllSchedules() throws SQLException {
        List<DoctorsSchedule> schedules = new ArrayList<>();
        Database.createConnection();
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                "select * from doctors_schedules");
        while (rs.next()) {
            int idDoctor = rs.getInt(1);
            int dayOfWeek = rs.getInt(2);
            int hour = rs.getInt(3);
            int minute = rs.getInt(4);
            DoctorsSchedule schedule = new DoctorsSchedule(idDoctor, dayOfWeek, hour, minute);
            schedules.add(schedule);
        }
//        con.close();
        return schedules;
    }

    public String returnDocsSpec(String specialization) throws SQLException {
        Connection conn = Database.getConnection();
        CallableStatement cstmt = conn.prepareCall("{? = CALL return_docs_spec(?)}");
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.setString(2, specialization);
        cstmt.executeUpdate();
        String names = cstmt.getString(1);
        return names;
    }

}
