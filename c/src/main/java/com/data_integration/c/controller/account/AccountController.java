package com.data_integration.c.controller.account;

import com.data_integration.c.PO.Account;
import com.data_integration.c.VO.AccountLoginVO;
import com.data_integration.c.VO.AccountVO;
import com.data_integration.c.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/c/account")
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

    @PostMapping("/{source}/deleteAccount/{acc}")
    public boolean deleteAccount(@PathVariable String source, @PathVariable String acc){
        return accountService.deleteAccount(source,acc);
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
