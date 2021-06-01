package com.data_integration.b.controller.admin;


import com.data_integration.b.pojo.account.Account;
import com.data_integration.b.service.admin.AdminService;
import com.data_integration.b.service.student.StudentService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/b/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 管理员登录并返回管理员信息
     */
    @PostMapping("/login")
    public Account login(@RequestBody Account account) {
        return adminService.getAccountByNameAndPwd(account.getAname(), account.getPassword());
    }


    /**
     * 获取所有管理员类型的账户
     */
    @GetMapping("/getAllAdmins")
    public List<Account> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    /**
     * 删除管理员 (6级才可以)
     */
    @GetMapping("/deleteAccount/{sourceAname}/{targetAname}")
    public String deleteAdmin(@PathVariable String sourceAname, @PathVariable String targetAname) {
        return adminService.deleteAccount(sourceAname, targetAname);
    }

    /**
     * 添加管理员 （6级才可以）
     */
    @PostMapping("/{source}/addAccount")
    public String addAccount(@PathVariable String source, @RequestBody Account account) {
        return adminService.addAccount(source, account);
    }


    /**
     * 更新管理员信息
     */
    @PostMapping("/updateAccount")
    public String updateAdmin(@RequestBody Account account) {
        return adminService.updateAdmin(account);
    }

}
