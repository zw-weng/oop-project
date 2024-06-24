public abstract class User {
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

    public String[] updateProfileData() {
        return new String[] {name, pwd, email, String.valueOf(phoneNo)};
    }

    public void updateProfile(String newName, String newPwd, String newEmail, String newPhoneStr) {
        if (newName != null && !newName.trim().isEmpty()) {
            setName(newName);
        }

        if (newPwd != null && !newPwd.trim().isEmpty()) {
            setPwd(newPwd);
        }

        if (newEmail != null && !newEmail.trim().isEmpty()) {
            setEmail(newEmail);
        }

        if (newPhoneStr != null && !newPhoneStr.trim().isEmpty()) {
            try {
                int newPhoneNo = Integer.parseInt(newPhoneStr);
                setPhone(newPhoneNo);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid phone number. Please enter a valid number.");
            }
        }
    }

    public String dispProfile() {
        return "Name: " + name + "\n" +
               "Email: " + email + "\n" +
               "Phone No: " + phoneNo;
    }

    // Abstract methods to ensure polymorphism
    public abstract void menu(BusReservationSystem system);

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
