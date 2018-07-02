package Entity;

public class Manager {
    private String admin;
    private String password;

    public Manager()
    {
        admin="";
        password="";
    }

    public Manager(String admin, String password, String name)
    {
        this.admin=admin;
        this.password=password;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdmin() {
        return admin;
    }

    public String getPassword() {
        return password;
    }

}
