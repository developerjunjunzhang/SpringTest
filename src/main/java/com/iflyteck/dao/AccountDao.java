package com.iflyteck.dao;

import com.iflyteck.domain.Account;

import java.util.List;

public interface AccountDao {
    Account findAccountById(Integer id);
    void updateAccount(Account account);
    Account findAccountByName(String name);
}
