package Entity;

public class User {
    private String tel;
    private String uname;
    private String upassword;
    private String email;
    private String birthday;

    public User()
    {
        tel="";
        uname="";
        upassword="";
        email="";
        birthday="";
    }

    public User(String tel,String uname,String upassword)
    {
        this.tel = tel;
        this.uname = uname;
        this.upassword = upassword;
    }

    public void setTel(String tel)
    {
        this.tel=tel;
    }

    public String getTel() {
        return tel;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }
}
