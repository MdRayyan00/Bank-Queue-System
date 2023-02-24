package queue;

import models.Transaction;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TransactionQueueImpl implements TransactionQueue {
    Queue<Transaction> allTransactions = new LinkedList<>();

    //dead letter queue
    Queue<Transaction> declinedTransactions = new LinkedList<>();

    // Adding transaction in the Queue from the transactions file
    @Override
    public void enqueue(Transaction transaction) {
        allTransactions.offer(transaction);
    }

    // Each transaction is checked and then status of transaction is updated (Settled, Declined) according to the checks
    @Override
    public void processTransactions() {
        int i=0;
        Instant startTime = Instant.now();
        for(Transaction currTransaction : allTransactions){
            if(i%2==0){
                currTransaction.setStatus("Settled");
            }else{
                currTransaction.setStatus("Declined");
            }
            ++i;
        }
        Instant endTime = Instant.now();
        System.out.println("Total time taken to process " + i + " records= " + (endTime.toEpochMilli() - startTime.toEpochMilli()));
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
