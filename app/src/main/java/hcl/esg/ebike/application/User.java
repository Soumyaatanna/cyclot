package hcl.esg.ebike.application;

public class User {
    private String emp_id;
    private String email;
    private String name;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String emp_id, String email,String name) {
        this.emp_id = emp_id;
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setame(String eName) {
        this.name = eName;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
