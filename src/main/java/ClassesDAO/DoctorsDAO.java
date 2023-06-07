package ClassesDAO;

import basicClasses.DoctorUser;
import basicClasses.DoctorsSchedule;
import databaseStuff.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ClassesDAO.AdminDAO.convertJSONToList;
import static ClassesDAO.AdminDAO.convertStringToJSON;

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

    public static void insertSchedule(DoctorsSchedule schedule) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into doctors_schedules (id_doctor, day_of_the_week, hour, minute) values (?, ?, ?, ?)")) {
            pstmt.setInt(1, schedule.getIdDoctor());
            pstmt.setInt(2, schedule.getDay());
            pstmt.setInt(3, schedule.getHour());
            pstmt.setInt(4, schedule.getMinute());
            pstmt.executeUpdate();
            Database.getConnection().commit();
            pstmt.close();
        }
    }

    public static Integer findByHourAndMinute(int idDoctor, int hour, int minute) throws SQLException {
        System.out.println("name: " + hour + " password: " + minute);
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT id_doctor FROM doctors_schedules WHERE hour = ? AND minute = ? AND id_doctor = ?");
        stmt.setInt(1, hour);
        stmt.setInt(2, minute);
        stmt.setInt(3, idDoctor);
        stmt.executeUpdate();
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int rezultat = rs.getInt(1);
            System.out.println(rezultat);
            rs.close();
            stmt.close();
            return 1;
        } else {
            rs.close();
            stmt.close();
            return null;
        }
    }

    public static Integer findByNameAndPassword(String name, String password) throws SQLException {
        System.out.println("name: " + name + " password: " + password);
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT name FROM doctors WHERE name = ? AND password = ?");
        stmt.setString(1, name);
        stmt.setString(2, password);
        stmt.executeUpdate();
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String rezultat = rs.getString(1);
            System.out.println(rezultat);
            rs.close();
            stmt.close();
            return 1;
        } else {
            rs.close();
            stmt.close();
            return null;
        }
    }

    public static Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from doctors where name='" + name + "'")) {
            if (rs.next()) {
                int rezultat = rs.getInt(1);
                System.out.println(rezultat);
                rs.close();
                stmt.close();
                return rezultat;
            } else {
                rs.close();
                stmt.close();
                return null;
            }
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
            rs.close();
            stmt.close();
            return rezultat;
        } else {
            rs.close();
            stmt.close();
//            con.close();
            return null;
        }
    }

    public static List<DoctorsSchedule> returnAllSchedules() throws SQLException {
        List<DoctorsSchedule> schedules = new ArrayList<>();
        Connection conn = Database.getConnection();
        CallableStatement cstmt = conn.prepareCall("{? = CALL return_schedules()}");
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.executeUpdate();
        String schedulesFromDb = cstmt.getString(1);
        convertStringToJSON(schedulesFromDb, "allSchedules.json");
        schedules = convertJSONToList("allSchedules.json");
        cstmt.close();
        return schedules;
    }

    public static List<DoctorsSchedule> returnAllSchedulesByDoctorId(int id) throws SQLException {
        List<DoctorsSchedule> schedules = new ArrayList<>();
        Connection conn = Database.getConnection();
        CallableStatement cstmt = conn.prepareCall("{? = CALL return_schedule_by_id(?)}");
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.setInt(2, id);
        cstmt.executeUpdate();
        String schedulesFromDb = cstmt.getString(1);
        System.out.println(schedulesFromDb);
        convertStringToJSON(schedulesFromDb, "scheduleById.json");
        schedules = convertJSONToList("scheduleById.json");
        cstmt.close();
        return schedules;
    }

    public static List<DoctorUser> returnAllDoctors() throws SQLException {
        List<DoctorUser> doctors = new ArrayList<>();
        Connection conn = Database.getConnection();
        CallableStatement cstmt = conn.prepareCall("{? = CALL return_doctors()}");
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.executeUpdate();
        String schedulesFromDb = cstmt.getString(1);
        convertStringToJSON(schedulesFromDb, "allDoctors.json");
        doctors = convertJSONToList("allDoctors.json");
        cstmt.close();
        return doctors;
    }

    public String returnDocsSpec(String specialization) throws SQLException {
        Connection conn = Database.getConnection();
        CallableStatement cstmt = conn.prepareCall("{? = CALL return_docs_spec(?)}");
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.setString(2, specialization);
        cstmt.executeUpdate();
        String names = cstmt.getString(1);
        cstmt.close();
        return names;
    }

}
