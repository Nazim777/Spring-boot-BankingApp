package com.demo.bankingapp.controller;

import com.demo.bankingapp.dto.AccountDto;
import com.demo.bankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }



    // create account
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }


    // get single account
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return  ResponseEntity.ok(accountDto);
    }

    // deposit
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    // withdraw
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    // get all account
    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccount(){
        List<AccountDto> accountDtos = accountService.getAllAccount();
        return ResponseEntity.ok(accountDtos);
    }

    // delete account
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully!");
    }



}
