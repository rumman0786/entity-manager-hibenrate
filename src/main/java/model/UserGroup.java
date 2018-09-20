package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author rumman
 * @since 7/12/18
 */
@Entity
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @OrderColumn(name = "idx")
    private List<AuthUser> users;

    public UserGroup() {
        users = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AuthUser> getUsers() {
        return users;
    }

    public void setUsers(List<AuthUser> users) {
        this.users = users;
    }

    public void addUser(AuthUser user) {
        getUsers().add(user);
        user.setGroup(this);
    }

    public void removeUser(AuthUser user) {
        getUsers().remove(user);
        user.setGroup(null);
    }
}
