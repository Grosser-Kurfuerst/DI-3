package com.data_integration.a.service.account;

import com.data_integration.a.PO.Account;
import com.data_integration.a.VO.AccountLoginVO;
import com.data_integration.a.VO.AccountVO;
import com.data_integration.a.mapper.account.AccountMapper;
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
        Account account = accountMapper.getAccountByAcc(accountLoginVO.account);
        if(account==null || !account.password.equals(accountLoginVO.password))
            return null;

        return account;
    }

    public List<Account> getAllAccounts(){
        List<Account> accountList = accountMapper.getAllAccounts();
        return accountList;
    }

    public boolean deleteAccount(String source,String acc){
        if(accountMapper.getAccountByAcc(source).permission<2)
            return false;
        try {
            accountMapper.deleteAccount(acc);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean addAccount(String source,Account account){
        if(accountMapper.getAccountByAcc(source).permission<2)
            return false;
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
