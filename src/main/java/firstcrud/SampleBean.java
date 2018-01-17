package firstcrud;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class SampleBean {

    private static List<Student> list = new ArrayList<>();
    private Student item;
    private boolean edit;
    private Student itemBeforeEditing;

    @PostConstruct
    public void init() {
        item = new Student();
        itemBeforeEditing = null;
    }

    public void add() {
        item.setId(list.isEmpty() ? 1 : list.get(list.size() - 1).getId() + 1);
        list.add(item);
        item = new Student();
    }

//    public void add() {
//        item.setId(!students.isRowAvailable() ? 1 : students.(students.getRowCount() - 1));
//    }

    public void cancelAdd() {
        item = new Student();
    }

    public void delete(Student item) {
        list.remove(item);
    }

    public void edit(Student item) {
        itemBeforeEditing = item.clone();
        this.item = item;
        edit = true;
    }

    public void saveEdit() {
        item = new Student();
        edit = false;
    }

    public void cancelEdit() {
        this.item.restore(itemBeforeEditing);
        this.item = new Student();
        edit = false;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public Student getItem() {
        return item;
    }

    public void setItem(Student item) {
        this.item = item;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
