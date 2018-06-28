package Entity;

public class Manager {
    private String tel;
    private String password;
    private String name;

    public Manager()
    {
        tel="";
        password="";
        name="";
    }

    public Manager(String tel,String password,String name)
    {
        this.tel=tel;
        this.password=password;
        this.name=name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
