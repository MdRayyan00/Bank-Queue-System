package queue;

import models.Transaction;

import java.util.List;

public interface TransactionFlow {
    void enqueue(Transaction transaction);

    TransactionFlow processTransactions();

    TransactionFlow processDeclineTransactions();

    void displayAll();
}
