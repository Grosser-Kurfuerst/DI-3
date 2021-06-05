package com.data_integration.a.mapper.account;

import com.data_integration.a.PO.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountMapper {
    Account getAccountByAcc(String account);
    List<Account> getAllAccounts();
    void deleteAccount(String acc);
    void addAccount(Account account);
    void updateAccount(Account account);
}
