package workflow.transaction;

import models.Transaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TransactionFlowListImpl implements TransactionFlow {
    List<Transaction> transactionList = new ArrayList<>();
    List<Transaction> declinedTransactionList = new ArrayList<>();
    List<Transaction> settleTransactionList = new ArrayList<>();

    @Override
    public void enqueue(Transaction transaction) {
        transactionList.add(transaction);
    }

    @Override
    public TransactionFlow processTransactions() {
        int i = 0;
        ListIterator<Transaction> transactionListIterator = transactionList.listIterator();

        Instant startTime = Instant.now();
        while (transactionListIterator.hasNext()) {
            Transaction transaction = transactionListIterator.next();
            transactionListIterator.remove();
            if (i++ % 2 == 0) {
                transaction.setStatus("Settled");
                settleTransactionList.add(transaction);
            } else {
                transaction.setStatus("Declined");
                declinedTransactionList.add(transaction);
            }
        }

        Instant endTime = Instant.now();
        System.out.println("Total time taken to check all transaction " + i + " records " + (endTime.toEpochMilli() - startTime.toEpochMilli()) + " ms");
        return this;
    }

    @Override
    public TransactionFlow processDeclineTransactions() {
        int i = 0;
        ListIterator<Transaction> transactionListIterator = declinedTransactionList.listIterator();

        Instant startTime = Instant.now();
        while (transactionListIterator.hasNext()) {
            Transaction transaction = transactionListIterator.next();
            transactionListIterator.remove();
            if (i++ % 2 == 0) {
                transaction.setStatus("Settled");
                settleTransactionList.add(transaction);
            }
        }

        Instant endTime = Instant.now();
        System.out.println("Total time taken to process " + i + " declined records " + (endTime.toEpochMilli() - startTime.toEpochMilli()) + " ms");
        return this;
    }

    @Override
    public void displayAll() {
        Instant startTime = Instant.now();
        int size = settleTransactionList.size();
        ListIterator<Transaction> transactionListIterator = settleTransactionList.listIterator();
        while (transactionListIterator.hasNext()) {
            Transaction transaction = transactionListIterator.next();
            transactionListIterator.remove();
        }
        Instant endTime = Instant.now();
        System.out.println("Total time taken to remove " + size + " settled transactions" + (endTime.toEpochMilli() - startTime.toEpochMilli()) + " ms");
    }
}
