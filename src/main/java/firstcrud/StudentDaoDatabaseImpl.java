package firstcrud;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoDatabaseImpl implements StudentDao {

    @Override
    public void add(Student item) {
        System.out.println("Add to database");

        String sql = "insert into students (first_name, last_name, birth_date) values (?, ?, ?)";
        try (Connection connectionObj = getConnection();
             PreparedStatement preparedStmt = connectionObj.prepareStatement(sql)) {
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

        String sql = "delete from students where student_id = "+ item.getId();
        System.out.println("deleteStudentRecordInDB() : Student Id: " + item.getId());
        try (Connection connectionObj = getConnection();
             PreparedStatement preparedStmt = connectionObj.prepareStatement(sql)) {
            preparedStmt.executeUpdate();
        } catch(Exception sqlException){
            sqlException.printStackTrace();
        }
    }

    @Override
    public void update(Student item) {
        System.out.println("Update in database");

        String sql = "update students set first_name=?, last_name=? , birth_date=? where student_id=?";
        try (Connection connectionObj = getConnection();
             PreparedStatement preparedStmt =  connectionObj.prepareStatement(sql)) {
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
        ArrayList list = new ArrayList();

        try (Connection connectionObj = getConnection();
             Statement stmtObj = connectionObj.createStatement();
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

    private static Connection getConnection() {
        System.out.println("connect");
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/test");
            return ds.getConnection();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    private static Date getSqlDateFormat(java.util.Date birthDate){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String date = fmt.format(birthDate);
        return Date.valueOf(new String(date));
    }
}
