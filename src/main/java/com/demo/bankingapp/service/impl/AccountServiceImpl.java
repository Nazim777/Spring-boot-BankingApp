package com.demo.bankingapp.service.impl;

import com.demo.bankingapp.dto.AccountDto;
import com.demo.bankingapp.dto.mapper.AccountMapper;
import com.demo.bankingapp.entity.Account;
import com.demo.bankingapp.repository.AccountRepository;
import com.demo.bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("User not found!"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("User not found!"));
        Double total = account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("User not found!"));
        Double prevBalance = account.getBalance();
        if(prevBalance<amount){
            throw new  RuntimeException("insufficient Balance!");
        }
        Double total = account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
       return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("User not found!"));
        accountRepository.deleteById(id);

    }
}
