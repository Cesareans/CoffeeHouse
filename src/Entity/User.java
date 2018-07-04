package Entity;

public class User {
    private String tel;
    private String name;
    private String password;
    private String email;
    private String birthday;
    private String registerTime;
    private boolean isactivate;
    private String gender;

    public User()
    {
        tel="";
        name ="";
        password ="";
        email="";
        birthday="";
        registerTime="";
        isactivate=false;
        gender="male";
    }

    public User(String tel, String name, String password, String registerTime, boolean isactivate, String gender)
    {
        this.tel = tel;
        this.name = name;
        this.password = password;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isIsactivate() {
        return isactivate;
    }

    public String getPassword() {
        return password;
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
