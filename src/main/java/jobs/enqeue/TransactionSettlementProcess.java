package jobs.enqeue;

import models.Transaction;
import queue.TransactionFlow;
import queue.TransactionFlowImpl;
import util.ExcelUtil;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class TransactionSettlementProcess {
    public static void main(String[] args) throws IOException {
        long count[] = new long[] {5000, 30000, 116000};
        enqueueRecords(count[0])
                .processTransactions()
                .processDeclineTransactions()
                .displayAll();

        System.out.println("------------------");

        enqueueRecords(count[1])
                .processTransactions()
                .processDeclineTransactions()
                .displayAll();

        System.out.println("------------------");
        Instant startTime = Instant.now();
        enqueueRecords(count[2])
                .processTransactions()
                .processDeclineTransactions()
                .displayAll();
        Instant endTime = Instant.now();
        System.out.println("Total time taken to process " + count[2] + " records " + (endTime.toEpochMilli() - startTime.toEpochMilli())+ " ms");
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