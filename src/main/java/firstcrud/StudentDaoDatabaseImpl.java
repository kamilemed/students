package firstcrud;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoDatabaseImpl implements StudentDao {

    @Override
    public void add(Student item) {
        System.out.println("Add to database");

        try (PreparedStatement preparedStmt = getConnection().prepareStatement("insert into students (first_name, last_name, birth_date) values (?, ?, ?)")) {
            preparedStmt.setString(1, item.getFirstName());
            preparedStmt.setString(2, item.getLastName());
            preparedStmt.setDate(3, getSqlDateFormat(item.getBirthDate()));
            preparedStmt.executeUpdate();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void remove(Student item) {
        System.out.println("Remove from database");
        System.out.println("deleteStudentRecordInDB() : Student Id: " + item.getId());
        try (PreparedStatement preparedStmt = getConnection().prepareStatement("delete from students where student_id = "+ item.getId())) {
            preparedStmt.executeUpdate();
        } catch(Exception sqlException){
            sqlException.printStackTrace();
        }
    }

    @Override
    public void update(Student item) {
        System.out.println("Update in database");
        try (PreparedStatement preparedStmt =  getConnection().prepareStatement("update students set first_name=?, last_name=? , birth_date=? where student_id=?")) {
            preparedStmt.setString(1, item.getFirstName());
            preparedStmt.setString(2, item.getLastName());
            preparedStmt.setDate(3, getSqlDateFormat(item.getBirthDate()));
            preparedStmt.setLong(4, item.getId());
            preparedStmt.executeUpdate();
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db_url ="jdbc:mysql://localhost:3306/test",
                    db_userName = "root",
                    db_password = "";
            return DriverManager.getConnection(db_url, db_userName, db_password);
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    private static ArrayList getStudentsListFromDB() {
        ArrayList list = new ArrayList();

        try (Statement stmtObj = getConnection().createStatement();
            ResultSet resultSetObj = stmtObj.executeQuery("select * from students")) {
            while(resultSetObj.next()) {
                Student studentObj = new Student();
                studentObj.setId(resultSetObj.getLong("student_id"));
                studentObj.setFirstName(resultSetObj.getString("first_name"));
                studentObj.setLastName(resultSetObj.getString("last_name"));
                studentObj.setBirthDate(resultSetObj.getDate("birth_date"));
                list.add(studentObj);
            }
            System.out.println("Total Records Fetched: " + list.size());
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }

        return list;
    }

    private static Date getSqlDateFormat(java.util.Date birthDate){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String date = fmt.format(birthDate);
        return Date.valueOf(new String(date));
    }
}
