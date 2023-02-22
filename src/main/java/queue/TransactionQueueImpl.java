package queue;

import models.Transaction;

import java.util.LinkedList;
import java.util.List;

public class TransactionQueueImpl implements TransactionQueue {
    LinkedList<Transaction> queue = new LinkedList<>();

    @Override
    public void push(Transaction transaction) {
        queue.push(transaction);
    }

    @Override
    public void processTransactions() {

    }

    @Override
    public List<Transaction> getTransactionsByStatus(String status) {
        return null;
    }

    @Override
    public List<Transaction> removeTransactionsByStatus(String status) {
        return null;
    }

    @Override
    public List<Transaction> displayAll() {
        return null;
    }
}
