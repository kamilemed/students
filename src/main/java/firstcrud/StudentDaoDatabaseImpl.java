package firstcrud;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.primefaces.model.SortOrder.ASCENDING;

public class StudentDaoDatabaseImpl extends LazyDataModel<Student> implements StudentDao {

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

        String sql = "delete from students where student_id=?";
        System.out.println("deleteStudentRecordInDB() : Student Id: " + item.getId());
        try (Connection connectionObj = getConnection();
             PreparedStatement preparedStmt = connectionObj.prepareStatement(sql)) {
            preparedStmt.setLong(1, item.getId());
            preparedStmt.executeUpdate();
        } catch(Exception sqlException){
            sqlException.printStackTrace();
        }
    }

    @Override
    public void update(Student item) {
        System.out.println("Update in database");

        String sql = "update students set first_name=?, last_name=?, birth_date=? where student_id=?";
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
    public Student findById(Long id) {
       Student studentObj = new Student();

        try (Connection connectionObj = getConnection();
             PreparedStatement preparedStmt = connectionObj.prepareStatement("select * from students WHERE student_id=" + id);
            ResultSet resultSetObj = preparedStmt.executeQuery()){
            while(resultSetObj.next()) {
                studentObj.setId(resultSetObj.getLong("student_id"));
                studentObj.setFirstName(resultSetObj.getString("first_name"));
                studentObj.setLastName(resultSetObj.getString("last_name"));
                studentObj.setBirthDate(resultSetObj.getDate("birth_date"));
            }
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }

        return studentObj;
    }

    @Override
    public List<Student> findAll(int first, int pageSize, String sortField, SortOrder sortOrder) {
        List<Student> list = new ArrayList<>();
        String order = sortOrder == ASCENDING ? "ASC" : "DESC";

        String sql = "SELECT * FROM students ";
        if (sortField != null) {
            sql += "ORDER BY " + sortField + " " + order + " ";
        }
        sql += "LIMIT " + pageSize + " OFFSET " + first;

        try (Connection connectionObj = getConnection();
             PreparedStatement preparedStmt =  connectionObj.prepareStatement(sql);
             ResultSet resultSetObj = preparedStmt.executeQuery()) {
            while (resultSetObj.next()) {
                Student studentObj = new Student();
                studentObj.setId(resultSetObj.getLong("student_id"));
                studentObj.setFirstName(resultSetObj.getString("first_name"));
                studentObj.setLastName(resultSetObj.getString("last_name"));
                studentObj.setBirthDate(resultSetObj.getDate("birth_date"));
                list.add(studentObj);
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }

        return list;
    }

    @Override
    public int count() {
        int rowsCount = 0;
        try (Connection connectionObj = getConnection();
             PreparedStatement preparedStmt =  connectionObj.prepareStatement("SELECT COUNT(*) FROM students");
             ResultSet resultSetObj = preparedStmt.executeQuery()) {
            if (resultSetObj.next()) {
                rowsCount = resultSetObj.getInt(1);
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }

        return rowsCount;
    }

    private static Connection getConnection() {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/test");
            return ds.getConnection();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    private static Date getSqlDateFormat(java.util.Date birthDate) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String date = fmt.format(birthDate);
        return Date.valueOf(new String(date));
    }
}
