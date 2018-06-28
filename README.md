# CoffeeHouse
Created For Java In Semester
DB_user:

属性说明：
user共有五个属性：
1.tel：pk
2.password：not null
3.name
4.birthday:****-**-**格式
5.email

接口说明：
public void setLocation(String ip)
默认为本机（如果不调用此函数的话）


public void displayUserInfo()
显示所有用户信息

public boolean isSuccess(String tel,String key)
判断登陆是否成功

public boolean insertNewUser(String tel,String password)
插入 新用户

public boolean insertNewUser(String tel,String password,String name,String birthday,String email)
插入 新用户

public boolean deleteUser(String tel)
删除 电话为tel的用户

public boolean updateTel(String oldtel,String newtel)
修改 原来电话号码为oldtel的用户的电话号码为newtel

public boolean updatePassword(String tel,String newpassword)
修改 电话号码为tel的用户的密码为newpassword

public boolean updateUserName(String tel,String newname)
修改/设置 电话号码为tel的用户的姓名改为newname

public boolean updateUserBirthday(String tel,String birthday)
修改/设置 电话号码为tel的用户的生日改为birthday

public boolean updateUserEmail(String tel,String email)
修改/设置 电话号码为tel的用户的邮箱改为email






