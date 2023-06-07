package ClassesDAO;

import basicClasses.Table;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import databaseStuff.Database;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class AdminDAO {
    public String returnTablesData() throws SQLException {
        Connection conn = Database.getConnection();
        CallableStatement cstmt = conn.prepareCall("{? = CALL return_table_data()}");
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.executeUpdate();
        String tables = cstmt.getString(1);
        convertStringToJSON(tables, "tablesData.json");
        //convertJSONToList();
        cstmt.close();
        return tables;
    }

    public static void findTableByName(String tableName) throws SQLException {
        Connection con = Database.getConnection();
        String jsonString = new String();
        try (Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(
                     "select * from " + tableName)) {

            //formatting the jsonString in order to prepare for putting it in a json file later
            jsonString += "[";

            ResultSetMetaData metaData = resultSet.getMetaData();
            // Get the number of columns in the result set
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                jsonString += "{";
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnType = metaData.getColumnTypeName(i);

                    jsonString += "\"" + columnName + "\":\"" + resultSet.getObject(columnName) + "\",";

                    System.out.println("Column Name: " + columnName);
                    System.out.println("Column Type: " + columnType);
                    System.out.println(resultSet.getObject(columnName));
                    System.out.println();
                }
                jsonString = jsonString.substring(0, jsonString.length() - 1);
                jsonString += "},";
            }
            jsonString = jsonString.substring(0, jsonString.length() - 1);
            jsonString += "]";
            convertStringToJSON(jsonString, "specifiedTable.json");

            resultSet.close();
            stmt.close();
        }
    }

    public static void convertStringToJSON(String jsonString, String jsonFile) {

        try {
            // Create an ObjectMapper object
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert the string to JSON object
            Object jsonObject = objectMapper.readValue(jsonString, Object.class);

            // Write the JSON object to a file
            objectMapper.writeValue(new File("src/main/resources/jsonFiles/" + jsonFile), jsonObject);

            System.out.println("JSON file created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertJSONToList() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON file into a list of Person objects
            List<Table> tableList = objectMapper.readValue(new File("src/main/resources/jsonFiles/tablesData.json"), new TypeReference<List<Table>>() {
            });

            // Print the list of Person objects
            for (Table table : tableList) {
                System.out.println(table);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
