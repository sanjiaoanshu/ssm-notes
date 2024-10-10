package Vo;
//值对象的包
import Entity.User;
import java.util.List;
public class DeptVo {
    Integer id;
    String name;
    List<User> users;
    public DeptVo() {
    }
    public DeptVo(Integer id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    @Override
    public String toString() {
        return "DeptVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
