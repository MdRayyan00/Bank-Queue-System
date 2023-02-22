package queue;

import models.Transaction;

import java.util.List;

public interface TransactionQueue {
    void push(Transaction transaction);

    void processTransactions();

    List<Transaction> getTransactionsByStatus(String status);

    List<Transaction> removeTransactionsByStatus(String status);

    List<Transaction> displayAll();

}
