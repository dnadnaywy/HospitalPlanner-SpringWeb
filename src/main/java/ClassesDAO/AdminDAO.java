package ClassesDAO;

import basicClasses.Table;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import databaseStuff.Database;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class AdminDAO {
    public String returnTablesData()  throws SQLException {
        Connection conn = Database.getConnection();
        CallableStatement cstmt = conn.prepareCall("{? = CALL return_table_data()}");
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.executeUpdate();
        String tables = cstmt.getString(1);
        convertStringToJSON(tables);
        //convertJSONToList();
        return tables;
    }

    public void convertStringToJSON(String jsonString) {

        try {
            // Create an ObjectMapper object
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert the string to JSON object
            Object jsonObject = objectMapper.readValue(jsonString, Object.class);

            // Write the JSON object to a file
            objectMapper.writeValue(new File("src/main/resources/jsonFiles/tablesData.json"), jsonObject);

            System.out.println("JSON file created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertJSONToList() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON file into a list of Person objects
            List<Table> tableList = objectMapper.readValue(new File("src/main/resources/jsonFiles/tablesData.json"), new TypeReference<List<Table>>() {});

            // Print the list of Person objects
            for (Table table : tableList) {
                System.out.println(table);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
