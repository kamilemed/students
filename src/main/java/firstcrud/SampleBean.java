package firstcrud;

import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.*;

@ManagedBean
@ViewScoped
public class SampleBean implements Serializable {
    private static final long serialVersionUID = 1L;

    String test = "test";

//    private static StudentDao studentDao = new StudentDaoInMemoryImpl();
    private StudentDao studentDao = new StudentDaoDatabaseImpl();
    private Student item;
    private LazyDataModel<Student> lazyModel;

    @PostConstruct
    public void init() {
        System.out.println("init");
        item = new Student();
        lazyModel = new StudentLazyDataModel(studentDao);
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

    public String delete(Long id) {
        System.out.println("delete");
        studentDao.remove(studentDao.findById(id));

        return "/students.xhtml?faces-redirect=true";
    }

    public void edit(Long id) {
        System.out.println("edit");
        this.item = studentDao.findById(id);
    }

    public Student getItem() {
        return item;
    }

    public void setItem(Student item) {
        this.item = item;
    }

    public LazyDataModel<Student> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Student> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Date getToday() {
        return new Date();
    }

    public void updateTest() {
        this.test += test;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}