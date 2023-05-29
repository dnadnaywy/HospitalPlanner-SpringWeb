package ClassesDAO;

import databaseStuff.Database;

import java.sql.*;

public class DoctorsDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into doctors (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from doctors where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from doctors where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : null;
        }
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
