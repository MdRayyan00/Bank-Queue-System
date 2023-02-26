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
        for(int i = 0; i< count.length; i++){
        Instant startTime = Instant.now();
        enqueueRecords(count[i])
                .processTransactions()
                .processDeclineTransactions()
                .displayAll();

        System.out.println("------------------");
        Instant endTime = Instant.now();
        System.out.println("Total time taken to process " + count[i] + " records " + (endTime.toEpochMilli() - startTime.toEpochMilli())+ " ms");
        }
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