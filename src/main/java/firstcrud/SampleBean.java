package firstcrud;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean
@RequestScoped
public class SampleBean implements Serializable {
    private static final long serialVersionUID = 1L;

//    private static StudentDao studentDao = new StudentDaoInMemoryImpl();
    private StudentDao studentDao = new StudentDaoDatabaseImpl();
    private Student item;

    @PostConstruct
    public void init() {
        System.out.println("init");
        item = new Student();
    }

    public String save() {
        System.out.println("save");
        if (item.getId() != null) {
            studentDao.update(item);
        } else {
            studentDao.add(item);
        }

        item = new Student();
        return "/students.xhtml?faces-redirect=true";
    }

    public String cancel() {
        System.out.println("cancel");
        item = new Student();

        return "/students.xhtml?faces-redirect=true";
    }

    public String delete(Student item) {
        System.out.println("delete");
        studentDao.remove(item);

        return "/students.xhtml?faces-redirect=true";
    }

    public void edit(Student item) {
        System.out.println("edit");
        this.item = item;
    }

    public Student getItem() {
        return item;
    }

    public void setItem(Student item) {
        this.item = item;
    }

    public List<Student> getList() {
        return studentDao.list();
    }

}
