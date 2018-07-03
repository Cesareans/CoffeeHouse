package Entity;
public class User {
    private String tel;
    private String uname;
    private String upassword;
    private String email;
    private String birthday;
    private String registerTime;
    private boolean isactivate;
    private String gender;

    public User()
    {
        tel="";
        uname="";
        upassword="";
        email="";
        birthday="";
        registerTime="";
        isactivate=false;
        gender="male";
    }

    public User(String tel,String uname,String upassword,String registerTime,boolean isactivate,String gender)
    {
        this.tel = tel;
        this.uname = uname;
        this.upassword = upassword;
        this.registerTime=registerTime;
        this.isactivate=isactivate;
        this.gender=gender;
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

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setIsactivate(boolean isactivate) {
        this.isactivate = isactivate;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getGender() {
        return gender;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public boolean isActivate() {
        return isactivate;
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
