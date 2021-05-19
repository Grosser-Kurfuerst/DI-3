package com.data_integration.c.mapper.account;

import com.data_integration.c.PO.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest {
    @Autowired
    AccountMapper accountMapper;

    @Test
    public void testGetAccountByAcc() throws Exception{
        Account account = accountMapper.getAccountByAcc("a");
        Assert.assertEquals("a",account.acc);
    }

    @Test
    public void testGetAllAccounts() throws Exception{
        List<Account> accountList = accountMapper.getAllAccounts();
        for (Account account:accountList)
            System.out.println(account.acc);
    }

    @Test
    public void testDeleteAccount() throws Exception{
        accountMapper.deleteAccount("a");
        Account account = accountMapper.getAccountByAcc("a");
        Assert.assertTrue(account == null);
    }

    @Test
    public void testAddAccount() throws Exception{
        accountMapper.addAccount(new Account(){{this.acc="zzzzzzzzzzzz";
            this.passwd="000";
            this.permission=2;
        }});
        Account account = accountMapper.getAccountByAcc("zzzzzzzzzzzz");
        Assert.assertEquals("000",account.passwd);
        Assert.assertEquals(Integer.valueOf(2),account.permission);
    }

    @Test
    public void testUpdateAccount() throws Exception{
        Account account = new Account();
        account.acc="b";
        account.passwd="123";
        account.permission=3;
        accountMapper.updateAccount(account);
        account = accountMapper.getAccountByAcc("b");
        Assert.assertEquals("123",account.passwd);
        Assert.assertEquals(Integer.valueOf(3),account.permission);
    }
}
