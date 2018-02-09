package firstcrud;

import org.primefaces.model.SortOrder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoInMemoryImpl implements StudentDao{
    private static List<Student> list = new ArrayList<>();

    @Override
    public void add(Student item) {
//        TODO: do not modify original item
        item.setId(list.isEmpty() ? 1 : list.get(list.size() - 1).getId() + 1);
        list.add(item);
    }

    @Override
    public void remove(Student item) {
        list.remove(item);
    }

    @Override
    public void update(Student item) {
        Student existingItem = findById(item.getId());
        existingItem.update(item);
    }

    @Override
    public Student findById(Long id) {
        for (Student student : list) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> findAll(int first, int pageSize, String sortField, SortOrder sortOrder) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }
}
