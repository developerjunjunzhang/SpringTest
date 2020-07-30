package com.iflyteck.service;

import com.iflyteck.domain.Account;

import java.util.List;

public interface AccountService {

    Account findOneAccount(Integer id);

    void updateAccount(Account account);

    void transfer(String sourceName, String targetName, Float money);
}
