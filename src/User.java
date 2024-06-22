import javax.swing.*;

public class User {
    private String name;
    private String pwd;
    private String email;
    private int phoneNo;

    public User(String name, String pwd, String email, int phoneNo) {
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.phoneNo = phoneNo;
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
        String newName = JOptionPane.showInputDialog("Enter new name:", name);
        if (newName != null && !newName.trim().isEmpty()) {
            setName(newName);
        }

        String newPwd = JOptionPane.showInputDialog("Enter new password:");
        if (newPwd != null && !newPwd.trim().isEmpty()) {
            setPwd(newPwd);
        }

        String newEmail = JOptionPane.showInputDialog("Enter new email:", email);
        if (newEmail != null && !newEmail.trim().isEmpty()) {
            setEmail(newEmail);
        }

        String newPhoneStr = JOptionPane.showInputDialog("Enter new phone number:", phoneNo);
        if (newPhoneStr != null && !newPhoneStr.trim().isEmpty()) {
            try {
                int newPhoneNo = Integer.parseInt(newPhoneStr);
                setPhone(newPhoneNo);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid phone number. Please enter a valid number.");
            }
        }

        JOptionPane.showMessageDialog(null, "Profile updated successfully.");
    }

    public void dispProfile() {
        String profile = "Name: " + name + "\n" +
                         "Email: " + email + "\n" +
                         "Phone No: " + phoneNo;
        JOptionPane.showMessageDialog(null, profile);
    }

    // Getters
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
}
