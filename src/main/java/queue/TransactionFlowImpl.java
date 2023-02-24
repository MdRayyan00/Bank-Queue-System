package queue;

import models.Transaction;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TransactionFlowImpl implements TransactionFlow {
    Queue<Transaction> allTransactions = new LinkedList<>();
    //dead letter queue
    Queue<Transaction> declinedTransactions = new LinkedList<>();

    Queue<Transaction> settledTransactions = new LinkedList<>();


    // Adding transaction in the Queue from the transactions file
    @Override
    public void enqueue(Transaction transaction) {
        allTransactions.offer(transaction);
    }

    // Each transaction is checked and then status of transaction is updated (Settled, Declined) according to the checks
    @Override
    public void processTransactions() {
        Instant startTime = Instant.now();
        int queueLength = allTransactions.size();
        for (int i =0 ; i < queueLength; i++) {
            Transaction currTransaction = allTransactions.poll();

            // This checkmark is temporary to process the transaction.
            // In real, there are various checks on each transaction for secure processing
            if (i % 2 == 0) {
                currTransaction.setStatus("Settled");
                settledTransactions.offer(currTransaction);
            } else {
                currTransaction.setStatus("Declined");
                declinedTransactions.offer(currTransaction);
            }
        }

        Instant endTime = Instant.now();
        System.out.println("Total time taken to process " + queueLength + " records= " + (endTime.toEpochMilli() - startTime.toEpochMilli()));
    }

    // Once all the transactions are processed the 'Declined' transactions are processed again in case there is bank failure or data issue.
    @Override
    public void processDeclineTransactions() {
    }

    // All the 'Settled' transactions are displayed.
    @Override
    public List<Transaction> displayAll() {
        return null;
    }
}
