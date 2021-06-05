# 数据和权限约束
学号长9位  
学生密码长6位  
课程编号长4位  

性别用M和F  
共享标志用Y和N  
学生的权限有1234，对应1234年级  
课程权限有01234，学生权限>=课程权限时可以选课  
管理人员权限有12  
2级管理员才可以增删管理员  

# 接口
## /c/account部分
- POST /c/account/login 管理员登录并返回管理员信息
    - RequestBody
        - acc: varchar(12) 账户名
        - passwd: varchar(12) 密码
    - 返回对象
        - acc: varchar(12)
        - createDate：Timestamp（没有用，不用管）
        - passwd: varchar(12) 密码
        - permission：int 权限
- GET /c/account/getAllAccounts 获取所有管理员账号
    - 无参数
    - 返回数组
        - acc: varchar(12)
        - createDate：Timestamp（没有用，不用管）
        - passwd: varchar(12) 密码
        - permission：int
- POST /c/account/{source}/deleteAccount/{acc} 删除管理员
    - PathVariable1
        - source: varchar(12) 操作者账户名
    - PathVariable2
        - acc: varchar(12) 要删除的账户名
    - 返回值
        - true为成功
        - false为失败，可能是无权限或异常
- POST /c/account/{source}/addAccount 添加管理员
    - PathVariable1
        - source: varchar(12) 操作者账户名
    - RequestBody
        - acc: varchar(12) 账户名
        - passwd: varchar(12) 密码
        - permission：int 权限
    - 返回值
        - true为成功
        - false为失败，可能是无权限或异常
- POST /c/account/updateAccount 更新管理员信息
    - RequestBody
        - acc: varchar(12) 账户名
        - passwd: varchar(12) 密码
        - permission：int 权限
    - 无返回值
## /c/student部分
- POST /c/student/login 学生登录并返回学生信息
    - RequestBody
        - sno: char(9) 学号
        - pwd: char(6) 密码
    - 返回对象
        - sno: char(9) 学号
        - snm: varchar(10) 姓名
        - sex: varchar(1) 性别
        - sde: varchar(6) 部门
        - pwd: char(6) 密码  
        - permission: integer 权限
- GET /c/student/getAllStudents 获取所有学生
    - 无参数
    - 返回数组
        - sno: char(9) 学号
        - snm: varchar(10) 姓名
        - sex: varchar(1) 性别
        - sde: varchar(6) 部门
        - pwd: char(6) 密码  
        - permission: integer 权限
- POST /c/student/updateStudentInfo 更新学生信息
    - RequestBody
        - sno: char(9) 学号
        - snm: varchar(10) 姓名
        - sex: varchar(1) 性别
        - sde: varchar(6) 部门
        - pwd: char(6) 密码  
        - permission: integer 权限
## /c/course部分
- GET /c/course/getAllCourses 获取所有课程
    - 无参数
    - 返回数组
        - cno: char(4) 课程编号
        - cnm: varchar(10) 课程名
        - ctm: integer 课时
        - cpt: integer 学分
        - tec: varchar(20) 教师名
        - pla: varchar(18) 上课地点 
        - share: char(1) 是否共享
        - permission: integer 权限
- GET /c/course/getOtherDepartmentCourses 获取其他院系共享课程
    - 无参数
    - 返回数组
        - cno: char(4) 课程编号
        - cnm: varchar(10) 课程名
        - ctm: integer 课时 一定为null
        - cpt: integer 学分
        - tec: varchar(20) 教师名
        - pla: varchar(18) 上课地点
        - share: char(1) 是否共享 一定为null
        - permission: integer 权限 一定为null
- POST /c/course/updateCourseInfo 修改课程信息
    - RequestBody
        - cno: char(4) 课程编号
        - cnm: varchar(10) 课程名
        - ctm: integer 课时
        - cpt: integer 学分
        - tec: varchar(20) 教师名
        - pla: varchar(18) 上课地点
        - share: char(1) 是否共享
        - permission: integer 权限
    - 无返回值
- POST /c/course/updateCourseShare 修改课程共享标志
    - RequestParam1
        - cno: char(4) 课程编号
    - RequestParam2
        - share: char(1) 共享标志
    - 无返回值
## /c/courseSelecting部分
- GET /c/courseSelecting/getAllCourseSelecting 获取所有选课数据
    - 无参数
    - 返回数组
        - cno: char(4) 课程编号
        - sno: char(9) 学号
        - grd: integer 成绩，可能为null
- GET /c/courseSelecting/getCourseSelectingBySno/{sno} 查看某个学生的选课
    - PathVariable1
        - sno: char(9) 学号
    - 返回数组
        - cno: char(4) 课程编号
        - sno: char(9) 学号
        - grd: integer 成绩，可能为null
- GET /c/courseSelecting/getCourseSelectingByCno/{cno} 查看某门课被选情况
    - PathVariable1
        - cno: char(4) 课程编号
    - 返回数组
        - cno: char(4) 课程编号
        - sno: char(9) 学号
        - grd: integer 成绩，可能为null
- POST /c/courseSelecting/addCourseSelecting 增加一个选课
    - RequestBody
        - cno: string 课程编号
        - sno: char(9) 学号
        - grd: integer 成绩，可以为null
    - 返回值
        - true为成功
        - false为失败，可能是无权限或异常
- POST /c/courseSelecting/deleteCourseSelecting 删除一个选课
    - RequestBody
        - cno: char(4) 课程编号
        - sno: char(9) 学号
    - 无返回值
- POST /c/courseSelecting/updateGrade 修改成绩
    - RequestBody
        - cno: char(4) 课程编号
        - sno: char(9) 学号
        - grd: integer 成绩
    - 无返回值