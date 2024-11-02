package xs8911mid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class DatabaseService {
    private static final String DB_URL = "jdbc:postgresql://c-midtermdb.yk2sptchxyway7.postgres.cosmos.azure.com:5432/midtermDB";
    private static final String USERNAME = "citus";
    private static final String PASSWORD = "Frankcj12s!";

    public static List<String> getStudents() throws Exception {
        List<String> students = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM students")) {

            while (resultSet.next()) {
                String student = String.format("{\"studentId\": \"%s\", \"studentFirstName\": \"%s\", \"studentLastName\": \"%s\", \"studentEmail\": \"%s\"}",
                        resultSet.getString("studentId"),
                        resultSet.getString("studentFirstName"),
                        resultSet.getString("studentLastName"),
                        resultSet.getString("studentEmail"));
                students.add(student);
            }
        }
        return students;
    }
}
