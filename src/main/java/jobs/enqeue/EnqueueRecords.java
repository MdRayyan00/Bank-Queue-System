package jobs.enqeue;

import models.Transaction;
import queue.TransactionFlow;
import queue.TransactionFlowImpl;
import util.ExcelUtil;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class EnqueueRecords {
    public static void main(String[] args) throws IOException {
        enqueueRecords(5000).processTransactions();

        enqueueRecords(30000).processTransactions();

        enqueueRecords(116000).processTransactions();
    }

    public static TransactionFlow enqueueRecords(long count) throws IOException {
        List<Transaction> allTransactions = new ExcelUtil().getFromExcel();
        TransactionFlow transactionFlow = new TransactionFlowImpl();

        Instant startTime = Instant.now();
        for (int i = 0; i < count; i++) {
            transactionFlow.enqueue(allTransactions.get(i));
        }
        Instant endTime = Instant.now();

        System.out.println("Total time taken to enqueue " + count + " records " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

        return transactionFlow;
    }
}