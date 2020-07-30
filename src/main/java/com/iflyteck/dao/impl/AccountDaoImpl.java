package com.iflyteck.dao.impl;

import com.iflyteck.dao.AccountDao;
import com.iflyteck.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Account findAccountById(Integer id) {
        List<Account> accounts = jdbcTemplate.query("select * from account where id = ?;", new BeanPropertyRowMapper<Account>(Account.class), id);
        return accounts.isEmpty() ? null : accounts.get(0);

    }

    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name = ? ,money = ? where id = ?;",account.getName(), account.getMoney(),account.getId());
    }

    public Account findAccountByName(String name) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name = ?;", new BeanPropertyRowMapper<Account>(Account.class), name);
        return accounts.isEmpty() ? null : accounts.get(0);
    }
}
