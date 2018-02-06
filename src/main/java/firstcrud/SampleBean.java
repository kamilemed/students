package firstcrud;

import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.*;

@ManagedBean
@RequestScoped
public class SampleBean implements Serializable {
    private static final long serialVersionUID = 1L;

//    private static StudentDao studentDao = new StudentDaoInMemoryImpl();
    private StudentDao studentDao = new StudentDaoDatabaseImpl();
    private Student item;
    private LazyDataModel<Student> lazyModel;


    @ManagedProperty(value = "#{param.id}")
    private Long id = null;

    @PostConstruct
    public void init() {
        System.out.println("init");
        if (id != null) {
            item = getItem(id);
        } else {
            item = new Student();
        }

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

    public String delete(Student item) {
        System.out.println("delete");
        studentDao.remove(item);

        return "/students.xhtml?faces-redirect=true";
    }

    public void edit() {
        System.out.println("edit");
        this.item = getItem(id);
    }

    public boolean getIsEdit() {
        return id != null;
    }

    public Student getItem() {
        return item;
    }

    private Student getItem(Long id) {
        for (Student student : getList()) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
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

    public List<Student> getList() {
        return studentDao.list();
    }

    public Date getToday() {
        return new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
