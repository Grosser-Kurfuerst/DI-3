package com.data_integration.c.service.account;

import com.data_integration.c.PO.Account;
import com.data_integration.c.VO.AccountLoginVO;
import com.data_integration.c.VO.AccountVO;
import com.data_integration.c.mapper.account.AccountMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    public Account accountLogin(AccountLoginVO accountLoginVO){
        Account account = accountMapper.getAccountByAcc(accountLoginVO.acc);
        if(account==null || !account.passwd.equals(accountLoginVO.passwd))
            return null;
        return account;
    }

    public List<Account> getAllAccounts(){
        return accountMapper.getAllAccounts();
    }

    public boolean deleteAccount(String source,String acc){
//        if(accountMapper.getAccountByAcc(source).permission<2)
//            return false;
        try {
            accountMapper.deleteAccount(acc);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean addAccount(String source,Account account){
//        if(accountMapper.getAccountByAcc(source).permission<2)
//            return false;
        try {
            accountMapper.addAccount(account);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public void updateAccount(Account account){
        accountMapper.updateAccount(account);
    }
}
