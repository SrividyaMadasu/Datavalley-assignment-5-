import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentDAO {
    
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/departments";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    
    private static final String INSERT_DEPARTMENT_SQL = "INSERT INTO department (id, name) VALUES (?, ?)";

    public void saveDepartment(Department department) {
        try (
            
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            
            
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEPARTMENT_SQL)
        ) {
            
            preparedStatement.setInt(1, department.getId());
            preparedStatement.setString(2, department.getName());

            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
        Department department = new Department(1, "IT");

        
        DepartmentDAO departmentDAO = new DepartmentDAO();

        
        departmentDAO.saveDepartment(department);
    }
}

class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
