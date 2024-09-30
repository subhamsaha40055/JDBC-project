import java.io.*;
import java.sql.*;
import java.util.*;

public class Create_DB {

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/company";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Subham@2003";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();

        scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Insert employee data into the database
            String query = "INSERT INTO employee (id, name, salary) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(3, salary);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Employee data inserted successfully!");
            }

            // Retrieve and print all employee data
            String selectQuery = "SELECT * FROM employee";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();

            System.out.println("Employee Details:");
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("id");
                String employeeName = resultSet.getString("name");
                double employeeSalary = resultSet.getDouble("salary");

                System.out.println("ID: " + employeeId + ", Name: " + employeeName + ", Salary: " + employeeSalary);
            }

            // Close the connections
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}
