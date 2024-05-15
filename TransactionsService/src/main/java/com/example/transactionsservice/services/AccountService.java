package com.example.transactionsservice.services;

import com.example.transactionsservice.entities.Account;
import com.example.transactionsservice.entities.Category;
import com.example.transactionsservice.entities.Client;
import com.example.transactionsservice.entities.Transaction;
import com.example.transactionsservice.exceptions.IllegalOperationException;
import com.example.transactionsservice.exceptions.ResourceNotFoundException;
import com.example.transactionsservice.model.Limit;
import com.example.transactionsservice.repositories.AccountRepository;
import com.example.transactionsservice.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientService clientService;
    private final Utils utils;
    private final BigDecimal DEFAULT_LIMIT_BALANCE = new BigDecimal("1000.00");

    @Transactional
    public Account createNewAccount(Account acc) {

        clientService.findById(acc.getClient().getId());

        List<Account> accounts = accountRepository.findByAccNo(acc.getAccNo());

        for (Account exstAcc : accounts) {
            if (exstAcc.getCategory().getCtgyName().getCtgValue() == acc.getCategory().getCtgyName().getCtgValue()) {
                throw new IllegalOperationException("Given category already exists for this account No." + acc.getAccNo());
            }
        }

        Account newAccount = createSingleAccount(acc);

        newAccount.setLimitSet(acc.getLimitSet().signum() == 0 ? DEFAULT_LIMIT_BALANCE : acc.getLimitSet());
        newAccount.setLimitBalance(acc.getLimitSet().signum() == 0 ? DEFAULT_LIMIT_BALANCE : acc.getLimitSet());

        return accountRepository.save(newAccount);
    }

    @Transactional
    public Account setMonthLimit(Limit limit) {

        List<Account> accounts = accountRepository.findByAccNo(limit.getAccNo());

        if (accounts.isEmpty()) throw new ResourceNotFoundException("Account No." + limit.getAccNo() + " not found");

        for (Account acc : accounts) {

            if (acc.getCategory().getCtgyId() == limit.getCtgyName().getCtgValue()) {

                Account accountWithNewLimitBalance = createSingleAccount(acc);

                accountWithNewLimitBalance.setLimitSet(limit.getLimitSum());
                accountWithNewLimitBalance.setLimitBalance(
                        utils.setLimitByValue(limit.getLimitSum(),limit.getLimitSum().add(acc.getLimitBalance())));

                return accountRepository.save(accountWithNewLimitBalance);
            }
        }
        throw new ResourceNotFoundException("No category set for account No." + limit.getAccNo());
    }

    @Transactional
    public Account updateAccount(Account acc, Transaction txn, BigDecimal currSumInUsd) {

        Account updatedAccount = createSingleAccount(acc);

        updatedAccount.setAccNo(txn.getAccFrom());
        updatedAccount.setBalance(utils.checkOperationValue(acc.getBalance().subtract(currSumInUsd)));
        updatedAccount.setLimitDate(acc.getLimitDate());
        updatedAccount.setLimitBalance(acc.getLimitBalance().subtract(currSumInUsd));

        return updatedAccount;
    }


    public Account createSingleAccount(Account account) {

        return Account.builder()
                .accId(account.getAccId())
                .client(Client.builder()
                        .id(account.getClient().getId())
                        .build())
                .accNo(account.getAccNo())
                .category(Category.builder()
                        .ctgyId(account.getCategory().getCtgyName().getCtgValue())
                        .build())
                .balance(utils.checkOperationValue(account.getBalance()))
                .limitSet(utils.checkOperationValue(account.getLimitSet()))
                .limitDate(ZonedDateTime.now())
                .limitBalance(account.getLimitSet())
                .build();
    }
}

