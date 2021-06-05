package com.data_integration.a.controller.account;

import com.data_integration.a.PO.Account;
import com.data_integration.a.VO.AccountLoginVO;
import com.data_integration.a.VO.AccountVO;
import com.data_integration.a.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/a/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/login")
    public Account accountLogin(@RequestBody AccountLoginVO accountLoginVO){
        return accountService.accountLogin(accountLoginVO);
    }

    @GetMapping("/getAllAccounts")
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @PostMapping("/{source}/deleteAccount/{account}")
    public boolean deleteAccount(@PathVariable String source, @PathVariable String account){
        return accountService.deleteAccount(source,account);
    }

    @PostMapping("/{source}/addAccount")
    public boolean addAccount(@PathVariable String source, @RequestBody Account account){
        return accountService.addAccount(source,account);
    }

    @PostMapping("/updateAccount")
    public void updateAccount(@RequestBody Account account){
        accountService.updateAccount(account);
    }
}
