import javax.swing.JOptionPane;

public class User {
    protected String name;
    protected String pwd;
    protected String email;
    protected int phoneNo;

    public User(String name, String pwd, String email, int phoneNo) {
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public void setPwd(String pwd) { 
        this.pwd = pwd; 
    }

    public void setEmail(String email) { 
        this.email = email; 
    }

    public void setPhone(int phoneNo) { 
        this.phoneNo = phoneNo; 
    }

    public void updateProfile() {
        name = JOptionPane.showInputDialog("Enter new name:", name);
        pwd = JOptionPane.showInputDialog("Enter new password:", pwd);
        email = JOptionPane.showInputDialog("Enter new email:", email);
        String phoneStr = JOptionPane.showInputDialog("Enter new phone number:", String.valueOf(phoneNo));
        phoneNo = Integer.parseInt(phoneStr);
    }

    public void dispProfile() {
        String profile = "Name: " + name + "\nEmail: " + email + "\nPhone No: " + phoneNo;
        JOptionPane.showMessageDialog(null, profile, "User Profile", JOptionPane.INFORMATION_MESSAGE);
    }
}
