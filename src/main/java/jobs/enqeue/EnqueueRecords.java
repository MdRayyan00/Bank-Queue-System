package jobs.enqeue;

import models.Transaction;
import queue.TransactionQueue;
import queue.TransactionQueueImpl;
import util.ExcelUtil;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class EnqueueRecords {
    public static void main(String[] args) throws IOException {
        enqueueRecords(5000);

        enqueueRecords(30000);

        enqueueRecords(116000);
    }

    public static TransactionQueue enqueueRecords(long count) throws IOException {
        List<Transaction> allTransactions = new ExcelUtil().getFromExcel();
        TransactionQueue queue = new TransactionQueueImpl();

        Instant startTime = Instant.now();
        for (int i = 0; i < count; i++) {
            queue.enqueue(allTransactions.get(i));
        }
        Instant endTime = Instant.now();

        System.out.println("Total time taken to enqueue " + count + " records " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

        return queue;
    }
}
