package com.data_integration.b.dao.admin;


import com.data_integration.b.pojo.account.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminDao {

    Account getAccountByNameAndPwd(String aname, String password);

    List<Account> getAllAdmins();

    Account getAdminByName(String sourceAname);

    int deleteAccountByName(String targetAname);

    int addAdmin(String aname, String password, int power_grade);

    int updateAccount(Account account);

    Account getAccountBySid(String sid);
}
