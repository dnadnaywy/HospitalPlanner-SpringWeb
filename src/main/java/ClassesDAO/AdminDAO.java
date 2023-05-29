package ClassesDAO;

import databaseStuff.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class AdminDAO {
    public String returnTablesData() throws SQLException {
        Connection conn = Database.getConnection();
        CallableStatement cstmt = conn.prepareCall("{? = CALL return_table_data()}");
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.executeUpdate();
        String tables = cstmt.getString(1);
        return tables;
    }
}
