package firstcrud;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;

public interface StudentDao {

    /**
     * @param item
     *
     */
    public void add(Student item);

    /**
     * Deletes student from the system.
     * @param item Student to delete.
     */
    public void remove(Student item);

    /**
     * @param item Student to edit
     */
    public void update(Student item);

    /**
     *
     * @return
     */
    public Student findById(Long id);

    public List<Student> findAll(int first, int pageSize, String sortField, SortOrder sortOrder);

    public int count();
}
