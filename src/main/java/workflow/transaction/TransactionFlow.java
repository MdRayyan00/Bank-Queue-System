package workflow.transaction;

import models.Transaction;

public interface TransactionFlow {
    void enqueue(Transaction transaction);

    TransactionFlow processTransactions();

    TransactionFlow processDeclineTransactions();

    void displayAll();
}
