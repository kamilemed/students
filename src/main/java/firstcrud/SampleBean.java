package firstcrud;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class SampleBean implements Serializable {

    private static List<Student> list = new ArrayList<>();
    private static Student item = new Student();
    private static boolean edit;
    private static Student itemBeforeEditing = null;

//    @PostConstruct
//    public void init() {
//    }

    public void add() {
        item.setId(list.isEmpty() ? 1 : list.get(list.size() - 1).getId() + 1);
        list.add(item);
        item = new Student();
    }

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

    public void saveEdit(Student item) {
        System.out.println(item.getFirstName());
        item.restore(this.getItem());
        this.item = new Student();
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
