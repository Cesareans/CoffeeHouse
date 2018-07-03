# CoffeeHouse
Created For Java In Semester

*****************************************
*****************************************
DBUser:

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

public boolean matchUser(String tel,String key)
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

public boolean existUserName(String uname)
是否存在名字为uname的用户 存在：true 不存在：false

public boolean existUserTel(String utel)
是否已存在电话为utel的用户 存在：true 不存在：false

****************************************************************
****************************************************************

DBMenu:

属性说明：
1.serialNumber:餐品号 pk
2.name:餐品名称
3.price：餐品价格
4.qty：餐品库存
5.type：餐品类型
6.sales:餐品销量，默认为0
6.pictureUrl：餐品图片的URL


接口说明：
public ArrayList<Menu> getAllmenu()
获取菜单全部信息

public ArrayList<Menu> getTypeMenu(String type)
获取所有类型为type的菜品

public boolean insertNewmenu(String serial,String name,double price,int qty,String type,String pictureUrl)
插入 新的一条菜单记录：餐品号，餐品名，单价，库存，类型，图片URL，销量默认为0

public boolean deleteMenu(String serial)
删除餐品号为serial的菜单记录

public boolean updateSerial(String oldserial,String newserial)
更新餐品号

public boolean updateMenuName(String serial,String newname)
更新餐品名

public boolean updateMenuPrice(String serial,double newprice)
更新餐品单价

public boolean updateMenuQty(String serial,int newqty)
更新餐品库存

public boolean updateMenuType(String serial,String newtype)
更新餐品类型

public boolean updateMenuPictureUrl(String serial,String newUrl)
更新餐品图片url

public boolean updateMenuSales(String serial,int newsale)
更新餐品的销量

****************************************************************
****************************************************************

DBAdmin:

属性说明：
1.adminName：管理员口令 pk
2.password：密码 not null

接口说明：
public ArrayList<Admin> getAllAdmins()
获取所有管理员信息

public boolean matchAdmin(String adminName,String key)
判断管理员是否登陆成功

public boolean existAdmin(String adminName)
判断该管理员是否存在

public boolean insertNewAdmin(String adminName,String password)
插入新管理员

public boolean deleteAdmin(String adminName)
删除管理员

public boolean updateAdmin(String oldadmin,String newadmin)
更新管理员名字

public boolean updatePassword(String adminName,String newpassword)
更新管理员密码








