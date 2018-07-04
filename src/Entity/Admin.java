package Entity;

public class Admin {
    private String admin;
    private String password;

    public Admin()
    {
        admin="";
        password="";
    }

    public Admin(String admin,String password,String name)
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
