package com.example.transactionsservice.services;

import com.example.transactionsservice.entities.Account;
import com.example.transactionsservice.exceptions.ResourceNotFoundException;
import com.example.transactionsservice.entities.Client;
import com.example.transactionsservice.entities.Transaction;
import com.example.transactionsservice.repositories.AccountRepository;
import com.example.transactionsservice.repositories.TransactionRepository;
import com.example.transactionsservice.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor

public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final Utils utils;

    @Transactional
    public Transaction createNewTransaction(Transaction txn) {

        utils.checkOperationValue(txn.getTxnSum());
        utils.checkDate(txn.getTxnDate());

        BigDecimal currSumInUsd = utils.convertToUSD(txn.getTxnSum(), txn.getCurrShrtName());

        List<Account> accounts = accountRepository.findByAccNo(txn.getAccFrom());

        if (accounts.isEmpty()) throw new ResourceNotFoundException("Account No." + txn.getAccFrom() + " not found");

        for (Account acc : accounts) {

            if (acc.getCategory().getCtgyId() == txn.getExpsCtgy().getCtgValue()) {

                Account currAcc = accountService.updateAccount(acc, txn, currSumInUsd);
                accountRepository.save(currAcc);

                Transaction newTxn = Transaction.builder()
                        .txnClientId(Client.builder()
                                .id(currAcc.getClient().getId())
                                .build())
                        .accFrom(txn.getAccFrom())
                        .accTo(txn.getAccTo())
                        .currShrtName(txn.getCurrShrtName())
                        .txnSum(txn.getTxnSum())
                        .txnSumUsd(currSumInUsd)
                        .expsCtgy(txn.getExpsCtgy())
                        .txnDate(txn.getTxnDate())
                        .monthLimit(currAcc.getLimitSet())
                        .limitLeft(currAcc.getLimitBalance())
                        .limitExcd(currAcc.getLimitBalance().signum() < 0)
                        .build();

                return transactionRepository.save(newTxn);
            }
        }
        throw new ResourceNotFoundException("Given category is not set for account No.:" + txn.getAccFrom());
    }
}


