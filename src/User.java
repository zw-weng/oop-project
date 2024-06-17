import java.util.*;
import javax.swing.*;

public class User {
    private String id;
    private String name;
    private String pwd;
    private String email;
    private int phoneNo;

    public User(String id, String name, String pwd, String email, int phoneNo) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void login(User user) {
        JOptionPane.showMessageDialog(null, "User " + getName() + " logged in.");
    }

    public void updateProfile(User user) {
        JOptionPane.showMessageDialog(null, "User " + getName() + " updated profile.");
    }
}