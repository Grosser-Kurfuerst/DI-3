package com.data_integration.b.serviceImpl.admin;

import com.data_integration.b.dao.admin.AdminDao;
import com.data_integration.b.pojo.account.Account;
import com.data_integration.b.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl  implements AdminService {

    @Autowired
    AdminDao adminDao;

    /**根据账户的名称和密码获取管理员账户信息，管理员的权限登记 >= 5*/
    @Override
    public Account getAccountByNameAndPwd(String aname, String password) {
        return adminDao.getAccountByNameAndPwd(aname, password);
    }

    /**获取所有管理员类型的对象*/
    @Override
    public List<Account> getAllAdmins() {
        return adminDao.getAllAdmins();
    }

    /**
     * 删除管理员账户
     * 管理员权限为6才可以删除其它管理员账户
     * @param sourceAname 执行操作的管理员的账户名
     * @param targetAname 要删除的账户名
     * @return 删除成功、权限不够、删除失败
     */
    @Override
    public String deleteAdmin(String sourceAname, String targetAname) {
        Account sourceAdmin = adminDao.getAccountByName(sourceAname);
        if (sourceAdmin == null) return "当前账户不存在";
        else if(sourceAdmin.getPower_grade() <= 5) return "当前账户权限不够";
        else if (sourceAdmin.getPower_grade() == 6) {
            int deleteNum = adminDao.deleteAccountByName(targetAname);
            if (deleteNum == 1) return "删除成功";
            return "删除失败";
        }
        return "删除失败";
    }

    /**
     * 添加管理员账户
     *
     * @param source
     * @param account 当前
     * @return
     */
    @Override
    public String addAccount(String source, Account account) {
        Account currentAdmin = adminDao.getAccountByName(source);
        if (currentAdmin.getPower_grade() != 6) {
            return "权限不够";
        }
        int addNum = adminDao.addAdmin(account.getAname(), account.getPassword(), account.getPower_grade());
        if (addNum == 1) return "添加成功";
        return "添加失败";
    }

    /**
     * 更新账户信息
     */
    @Override
    public String updateAdmin(Account account) {
        int updateNum = adminDao.updateAccount(account);
        if (updateNum == 1) return "更新成功";
        return "更新失败";
    }
}
