package firstcrud;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.*;

public class StudentLazyDataModel extends LazyDataModel<Student> {

    private StudentDao dao;

    StudentLazyDataModel(StudentDao dao){
        this.dao = dao;
    }

    @Override
    public List<Student> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        System.out.println("load");
        List<Student> data = dao.findAll(first, pageSize, sortField, sortOrder);
        this.setRowCount(dao.count());
        return data;
    }
}