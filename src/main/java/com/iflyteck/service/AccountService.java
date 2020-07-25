package com.iflyteck.service;

import com.iflyteck.domain.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAllAccount();

    Account findOneAccount(Integer id);

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer id);
}
