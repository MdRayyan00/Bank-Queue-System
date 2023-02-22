package queue;

import models.Transaction;

import java.util.LinkedList;
import java.util.List;

public class TransactionQueueImpl implements TransactionQueue {
    LinkedList<Transaction> queue = new LinkedList<>();

    // Adding transaction in the Queue from the transactions file
    @Override
    public void enqueue(Transaction transaction) {
        queue.offer(transaction);
    }

    // Each transaction is checked and then status of transaction is updated (Settled, Declined) according to the checks
    @Override
    public void processTransactions() {

    }

    // The transactions which are marked as Declined are retrieved and are processed again
    @Override
    public List<Transaction> getTransactionsByStatus(String status) {
        return null;
    }

    // Once all the transactions are processed the 'Declined' transactions are removed from the Queue.
    @Override
    public List<Transaction> removeTransactionsByStatus(String status) {
        return null;
    }

    // All the 'Settled' transactions are displayed.
    @Override
    public List<Transaction> displayAll() {
        return null;
    }
}
