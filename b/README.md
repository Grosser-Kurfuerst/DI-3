# 数据和权限约束
账户名长12位
密码长12位

学号长9位  
学生密码长6位  
性别使用 'M' 和 'F'

课程编号长5位
共享位使用 '0'（不共享） 和 '1'（共享）

学生的权限power_grade有1234，对应1234年级  
课程权限有01234，学生权限>=课程权限时可以选课  
管理人员权限有5 6
6级管理员才可以增删管理员

# 接口

## /election 部分

- /b/courseSelecting/addCourseSelecting 添加选课信息 (POST)
  - RequestBody
        - courseId: char(5)课程编号
        - studentId: char(9)学号
        - score: 分数
  - 返回
    - true
    - false
    
- /b/courseSelecting/addCourseSelectingXml 其它院系同学通过xml进行选课的接口 (POST)
  - RequestBody
        - String 学生和院系的xml
  - 返回String 
    - true
    - false

- /b/courseSelecting/delete/{cid}/{sid} 删除选课信息 (GET)
    - PathVariable1
      - cid: char(5)课程编号
      - sid: char(9)学号
    - 返回字符串
      - ”删除成功“
      - ”删除失败“

- /b/courseSelecting/getElectionsBySid/{sid} 查看某个学生的选课 (GET)
    - PathVariable1
        - sid: char(9)学号
    - 返回Election对象数组

- /b/courseSelecting/getElectionsByCid/{cid} 查看某门课被选情况 (GET)
    - PathVariable1
        - cid: char(5) 课程编号
    - 返回Election对象数组

- /b/courseSelecting/getElections 获取所有选课数据 (GET)
    - 无参数
    - 返回Election对象数组

- /b/courseSelecting/updateGrade/{cid}/{sid}/{score} 修改成绩 (GET)
    - PathVariable
        - cid: char(5) 课程编号
        - sid: char(9) 学号
        - score: integer 成绩
    - 无返回值


## /course 部分

- /b/course/getAllCourses 获取所有课程 (GET)
    - 无参数
    - 返回Course列表

- /b/course/updateCourse 修改课程信息 (POST)
    - RequestBody 
        - courseId: char(5) 课程编号 （必须要有）
        - courseName课程名 （可有可无）
        - courseTime课时 （可有可无）
        - score学分 （可有可无）
        - teacherName教师名 （可有可无）
        - teachingPlace上课地点 （可有可无）
        - shareFlag是否共享 （可有可无）
        - powerGrade权限 （可有可无）
    - 返回值：String
        - 更新成功
        - 更新失败

- /b/course/setShare/{flag}/{courseId} 修改课程共享标志
    - RequestParam1
        - flag: ”1“、”0“
    - RequestParam2
        - courseId：5位课程编号
    - 返回 String
        - "修改成功" / "修改失败"


## /student部分

- /b/student/login 学生登录并返回学生信息（POST）
    - RequestBody
      - aname: 账户名 （account表中的账户名）
      - password: 密码
    - 返回Student类型的对象

- /b/student/getAllStudents 获取所有学生
    - 无参数
    - 返回Student类型的对象的列表
    
- /b/student/update 更新学生信息（POST）
    - RequestBody
        - sid 学号 （必须）
        - sname (可有可无)
        - gender (可有可无) （”男/女“）
        - department (可有可无)
        - password (可有可无)
    - 返回更新后的Student对象


## /admin部分
- /b/admin/login 管理员登录并返回管理员信息（POST）
    - RequestBody
        - aname:  账户名
        - password: 密码
    - 返回Account类型的对象 （权限>4）的账户就是管理员账户

- /b/admin/getAllAdmins 获取所有管理员账号
    - 无参数
    - 返回Account对象数组

- /b/admin/deleteAccount/{sourceAname}/{targetAname} 删除账户 (GET)
    - PathVariable1
        - sourceAname: 操作者账户名
    - PathVariable2
        - targetAname: 要删除的账户名
    - 返回值String: "删除成功"、”当前账户不存在“、”管理员不存在“、 ”删除失败“

- /b/admin/{source}/addAccount 添加管理员
    - PathVariable1
        - source: varchar(12) 操作者账户名
    - RequestBody
        - aname: 12账户名
        - password: 12密码
        - power_grade: 0-6权限
    - 返回值String: “权限不够” 、 “添加成功”、“添加失败”

- /b/admin/updateAccount 更新管理员信息
    - RequestBody
        - aname: varchar(12) 账户名
        - password: varchar(12) 密码
        - power_grade：int 权限
    - 返回值String : “更新成功”，“更新失败”