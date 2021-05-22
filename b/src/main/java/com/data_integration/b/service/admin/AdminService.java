package com.data_integration.b.service.admin;

import com.data_integration.b.pojo.account.Account;

import java.util.List;

public interface AdminService {

    /**根据账户的名称和密码获取管理员账户信息，管理员的权限登记 >= 5*/
    Account getAccountByNameAndPwd(String aname, String password);

    /**获取所有管理员类型的对象*/
    List<Account> getAllAdmins();

    /**删除管理员账户*/
    String deleteAccount(String sourceAname, String targetAname);

    /**添加管理员账户*/
    String addAccount(String source, Account account);

    /**更新账户信息*/
    String updateAdmin(Account account);
}
