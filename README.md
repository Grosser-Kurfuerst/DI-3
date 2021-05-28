关于xml使用  
b.controller.test.TestController和integration.controller.TestController做了一个演示  
b发送xml到集成服务器，集成服务器使用xsd验证和xsl转换  

请在涉及各服务器url交互的地方标上TODO，以在演示时修改  
集成服务器直接交互的接口都是POST  

# 安排、接口名和代码大致逻辑
- 6月4号24点总ddl  
    - 5月29号24点前写完各自服务器向集成服务器发送课程列表xml的功能
        - 各院系服务器接口名为: 集成服务器/{a,b,c}/course/getAllCoursesXml
    - 5月31号24点前写完集成服务器获取其他院系课程再返回给本院系再用json返回给前端的功能  
        - 各院系服务器给前端的接口名为: 院系服务器/{a,b,c}/course/getOtherDepartmentCourses，该接口调用集成服务器接口  
        - 集成服务器的接口名为: 集成服务器/{a,b,c}/course/getOtherDepartmentCourses
            - 集成服务器/a/course/getOtherDepartmentCourses 调用bc服务器的getAllCoursesXml，返回给集成服务器
            - 集成服务器最终都转换为a的格式返回给a服务器
            - a服务器转化为对象，再通过springboot自动转为json返回给前端
            - 前端根据课程id中含有b或c，分离bc课程，显示
    - 6月2号24点前写完各服务器检测到选其他院的课后发给集成服务器的功能  
        - 修改原选课接口逻辑，如果是本院课程走原流程
        - 如果学生和课程院系不一致（学生一定是本院的学生），给集成服务器发送转换为集成格式的学生信息和选课信息
            - 集成服务器的接口名为: 集成服务器/{a,b,c}/courseSelecting/addCourseSelecting
            - 比如a检测到选b的课，就向 集成服务器/b/courseSelecting/addCourseSelecting 发送消息
        - 生成学生和选课的xml字符串，再拼接，头部加上<chooseOther>，尾部加上</chooseOther>，发送给集成服务器
    - 6月4号24点前写完集成服务器向其他院系选课的功能
        - 如上所述，集成服务器的接口名为: 集成服务器/{a,b,c}/courseSelecting/addCourseSelecting
        - 集成服务器去除头部的<chooseOther>和尾部的</chooseOther>，分割学生xml和选课xml
        - 将学生和选课转换为课程所在院系的格式，发送给课程所在院系的服务器
        - 院系服务器接口名为: 院系服务器/{a,b,c}/courseSelecting/addCourseSelectingXml
        - 院系服务器检验发过来的学生权限是否>=课程权限
        - 如果学生权限是否>=课程权限，数据库中插入选课并返回"true"，反之返回"false"
        - 集成服务器获得选课是否成功的信息，并原样返回
        - 发送请求的服务器获得集成服务器返回的是否成功的信息，如果成功，数据库中插入选课并返回"true"，反之返回"false"
        - 前端显示选课结果

# 抽象流程分析
以院系A为例  
获取其他院系课程：  
集成服务器调用B和C的接口，  
BC院系发送各自格式的课程，  
集成服务器验证（ABC的xsd）并转换为统一格式（ABCtoFormat的xsl），  
再转换为A格式给A（FormattoABC的xsl），  
A不存到自己的数据库

选择其他院系课程：  
A服务器检测到学生和课程院系不一致，  
A院系先生成自己的选课格式和学生格式的xml，  
再转化为统一格式发送到集成服务器（ABCtoFormat的xsl），  
集成服务器检验（format的xsd）并转化为B或C的选课和学生格式（ABCtoFormat的xsl），发送给B或C，  
B或C检查是否有权限，返回选课结果(“true” “false”)，  
选课信息同时存入两相关院系

# 数据格式
各院系的学号和课程号以A/B/C开头  
学生统一格式的sex用M和F  

# 端口
a服务器端口8086
b服务器端口8888
c服务器端口8085
集成服务器端口9000