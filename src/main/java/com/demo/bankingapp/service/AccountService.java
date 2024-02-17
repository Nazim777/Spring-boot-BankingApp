package com.demo.bankingapp.service;

import com.demo.bankingapp.dto.AccountDto;

import java.util.List;


public interface AccountService {
  AccountDto createAccount(AccountDto account);
  AccountDto getAccountById(Long id);

  AccountDto deposit(Long id, Double amount);
  AccountDto withdraw(Long id, Double amount);
  List<AccountDto> getAllAccount();

  void deleteAccount(Long id);
}
