package com.suman.crm.service;

import com.suman.crm.model.Account;
import com.suman.crm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Long id, Account updatedAccount) {
        return accountRepository.findById(id).map(account -> {
            account.setName(updatedAccount.getName());
            account.setIndustry(updatedAccount.getIndustry());
            account.setWebsite(updatedAccount.getWebsite());
            account.setOwner(updatedAccount.getOwner());
            return accountRepository.save(account);
        }).orElseGet(() -> {
            updatedAccount.setId(id);
            return accountRepository.save(updatedAccount);
        });
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

   public Map<String, Long> getAccountsByIndustry() {
        return accountRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        account -> account.getIndustry() != null ? account.getIndustry() : "Unknown",
                        Collectors.counting()
                ));
    }
}
