package runner;

import models.Transaction;
import workflow.transaction.TransactionFlow;
import workflow.transaction.TransactionFlowQueueImpl;
import util.ExcelUtil;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class TransactionSettlementProcessUsingQueue {
    public static void main(String[] args) throws IOException {
        enqueueRecords(5000)
                .processTransactions()
                .processDeclineTransactions()
                .displayAll();

        System.out.println("------------------");

        enqueueRecords(30000)
                .processTransactions()
                .processDeclineTransactions()
                .displayAll();

        System.out.println("------------------");

        enqueueRecords(116000)
                .processTransactions()
                .processDeclineTransactions()
                .displayAll();
    }

    public static TransactionFlow enqueueRecords(long count) throws IOException {
        List<Transaction> allTransactions = new ExcelUtil().getFromExcel();
        TransactionFlow transactionFlow = new TransactionFlowQueueImpl();

        Instant startTime = Instant.now();
        for (int i = 0; i < count; i++) {
            transactionFlow.enqueue(allTransactions.get(i));
        }
        Instant endTime = Instant.now();

        System.out.println("Total time taken to enqueue " + count + " records " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

        return transactionFlow;
    }
}