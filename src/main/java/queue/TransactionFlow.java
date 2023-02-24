package queue;

import models.Transaction;

import java.util.List;

public interface TransactionFlow {
    void enqueue(Transaction transaction);

    void processTransactions();

    void processDeclineTransactions();

    List<Transaction> displayAll();

}
