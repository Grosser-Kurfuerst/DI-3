### 安装sqlserver

按照这个教程进行操作，下载的也是sqlserver 2019 developer，同样也下载ssms方便图形化界面管理

https://blog.csdn.net/CHQC388/article/details/104550963

#### sqlserver 远端登陆 and 账户名 密码登陆 配置

按照这个进行配置

https://blog.csdn.net/CHQC388/article/details/104565370

配置过后就可以通过账号名和密码，进行的远端登陆

我之后基本上 都是通过  DataGrip进行 数据修改添加，表结构修改等。感觉还是 DataGrip好用

### 数据库 建立

https://docs.microsoft.com/zh-cn/sql/ssms/quickstarts/ssms-connect-query-sql-server?view=sql-server-ver15

主要是 这个文章 创建数据库 这一步，，教你如何打开 ssms的 	Query console

但是首先需要右键 databases 创建一个新的 空database，然后再打开 Query console

复制黏贴 我resources 里面的sql 下面的 sql文件内容 进行执行

```
--  注意data_integration_a和创建的 数据库名一致，不然就全部替换成你自定义名字的数据库名字
```

测试过，我这里是可以把表创建起来的。。其他的就没啥的了

### springboot 进行配置

~~~
server:
  port: 8086

spring:
  datasource:
    url: jdbc:sqlserver://localhost:1433;databaseName=data_integration_a
    username: sa
    password: root
    driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver

mybatis:
  mapper-locations: classpath:mapper/*/*Mapper.xml
  identity: SQLSERVER
~~~

url： 1433好像是 sqlserver的默认端口，反正我直接用的。。

username  在sqlserver 配置账号的时候默认是sa，我也沿用了

其他的就没了