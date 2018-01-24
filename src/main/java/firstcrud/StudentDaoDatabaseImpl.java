package firstcrud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoDatabaseImpl implements StudentDao {

    public static Statement stmtObj;
    public static Connection connObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement preparedstmt;

    @Override
    public void add(Student item) {
        System.out.println("Add to database");
        try {
            preparedstmt = getConnection().prepareStatement("insert into students (first_name, last_name) values (?, ?)");
            preparedstmt.setString(1, item.getFirstName());
            preparedstmt.setString(2, item.getLastName());
            preparedstmt.executeUpdate();
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void remove(Student item) {
        System.out.println("Remove from database");
        System.out.println("deleteStudentRecordInDB() : Student Id: " + item.getId());
        try {
            preparedstmt = getConnection().prepareStatement("delete from students where student_id = "+ item.getId());
            preparedstmt.executeUpdate();
            connObj.close();
        } catch(Exception sqlException){
            sqlException.printStackTrace();
        }
    }

    @Override
    public void update(Student item) {
        System.out.println("Update in database");
        try {
            preparedstmt = getConnection().prepareStatement("update students set first_name=?, last_name=? where student_id=?");
            preparedstmt.setString(1, item.getFirstName());
            preparedstmt.setString(2, item.getLastName());
            preparedstmt.setLong(3, item.getId());
            preparedstmt.executeUpdate();
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public List<Student> list() {
        System.out.println("List from database");
        return getStudentsListFromDB();
    }

    private static Connection getConnection(){
        System.out.println("connected");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String db_url ="jdbc:mysql://localhost:3306/test",
                    db_userName = "root",
                    db_password = "";
            connObj = DriverManager.getConnection(db_url, db_userName, db_password);
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return connObj;
    }

    private static ArrayList getStudentsListFromDB() {
        ArrayList list = new ArrayList();

        try {
            stmtObj = getConnection().createStatement();
            resultSetObj = stmtObj.executeQuery("select * from students");
            while(resultSetObj.next()) {
                Student studentObj = new Student();
                studentObj.setId(resultSetObj.getLong("student_id"));
                studentObj.setFirstName(resultSetObj.getString("first_name"));
                studentObj.setLastName(resultSetObj.getString("last_name"));
                list.add(studentObj);
            }
            System.out.println("Total Records Fetched: " + list.size());
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return list;
    }
}
