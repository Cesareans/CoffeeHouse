# CoffeeHouse
Created For Java In Semester

*****************************************
*****************************************
DBUser:

属性说明：
user共有8个属性：
1.tel：pk
2.password：not null
3.name
4.birthday:****-**-**格式
5.email
6.registerTime：yyyy-MM-dd HH:mm:ss格式
7.isactivate：boolean
8.gender：性别 female/male
接口说明：

public ArrayList<User> getAllUsers()
获取所有用户信息

public ArrayList<User> getUsersByName(String name)
获取名字为name的用户信息

public User getUserByTel(String tel)
获取电话号码为tel的用户信息

public boolean  matchUser(String tel,String key)
判断登陆是否成功

public boolean existUserName(String uname)
判断用户名是否已存在

public boolean existUserTel(String utel)
判断电话是否已存在

public boolean insertNewUser(String tel,String password)
插入 新用户

public boolean insertNewUser(String tel,String password,String name,String birthday,String email,boolean isactivate, String gender)
插入 新用户

public boolean deleteUser(String tel)
删除 电话为tel的用户

public boolean updateUser(User u)
更新用户信息，用户电话号码不可通过此方法更新

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

public boolean updateGender(String tel,String newgender)
修改/设置 电话号码为tel的用户的性别改为newgender

public boolean updateActivate(String tel,boolean ac)
修改/设置 电话号码为tel的用户的激活状态


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

public Menu getSNmenu(String SN)
获取SN号菜品的信息

String getNewSN()
获取一个新的序列号

public ArrayList<Menu> getMenuByName(String name) 
获取关键字为name的菜品信息

public ArrayList<Menu> getMenuByType(String type)
获取所有类型为type的菜品

public boolean insertNewmenu(String serial,String name,double price,int qty,String type,String pictureUrl)
插入 新的一条菜单记录：餐品号，餐品名，单价，库存，类型，图片URL，销量默认为0

public boolean deleteMenu(String serial)
删除餐品号为serial的菜单记录

public boolean updateMenu(Menu m)
更新餐品信息，餐品号不可通过此方法更新

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

****************************************************************
****************************************************************
DBOrder:

属性说明：
1.cartSN: 订单号
2.userTel： 用户电话号码
3.mealSerialNumber：餐品号
4.mealName：订单下单时刻餐品的名字
5.mealPrice：订单下单时刻单价
6.qty：用户购买件数
7.date：下单时间 格式：****-**-** **：**：**.* 

接口说明：
public ArrayList<Order> getAllOrders()
获取全部历史订单（所有用户）

public ArrayList<Order> getOrderByUser(String usertel)
获取电话号码为userTel的用户的历史订单

public ArrayList<Order> getOrderByDate(String from, String to)
获取日期从from到to的订单
from/to格式：****-**-**

public Order getOrderBySerial(String sn)
根据订单号获取订单

public boolean insertNewOrder(String cartSN,String userTel,String mealSerialNumber,int qty,String date)
插入新的历史订单记录，参数：订单号，用户电话号，餐品号，购买数量，日期
备注：下单时刻的餐品名称及价格自动生成

public boolean deleteOrder(String cartSN)
删除订单号为orderSN的历史订单记录

public boolean updateOrderQty(String cartSN,String mealSerialNumber,int newQty)
更新订单号为orderSN,餐品号为mealSerialNumber的订单的购买数量为newQty

public boolean updateOrderDate(String cartSN,String mealSerialNumber,String newdate)
更新订单号为orderSN，餐品号为mealSerialNumber的订单的购买日期为newdate

备注说明：
1.订单号一旦生成不可更改
2.当用户信息修改时，历史订单中的用户信息会随之更改
3.当餐品号更改时，历史订单中的餐品号会自动随之修改
4.餐品名称以及餐品单价一旦生成，不可更改


****************************************************************
****************************************************************

DBCart:

属性说明：
1.cartSN: 订单号
2.userTel： 用户电话号码
3.mealSerialNumber：餐品号
4.qty：用户购买件数
5.date：下单时间 格式：****-**-** **：**：**.* 

接口说明：
public ArrayList<Order> getUserCart(String usertel)
获取电话号码为usertel的用户的购物车所有信息

public boolean insertNewOrder(String cartSN,String userTel,String mealSerialNumber,int qty,String date)
像购物车中插入新物品

public boolean deleteOrder(String cartSN)
删除订单号为orderSN的订单

public boolean updateOrderQty(String cartSN,String mealSerialNumber,int newQty)
更新订单号为orderSN,餐品号为mealSerialNumber的订单的购买数量为newQty

public boolean updateOrderDate(String cartSN,String mealSerialNumber,String newdate)
更新订单号为orderSN,餐品号为mealSerialNumber的订单的购买日期为newdate


统一方法：
public void close()








