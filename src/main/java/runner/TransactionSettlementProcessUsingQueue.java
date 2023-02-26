package runner;

import models.Transaction;
import util.ExcelUtil;
import workflow.transaction.TransactionFlowQueueImpl;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import static util.TransactionUtil.enqueueRecords;

public class TransactionSettlementProcessUsingQueue {
    public static void main(String[] args) throws IOException {
        List<Transaction> allTransactions = new ExcelUtil().getFromExcel();

        long[] count = new long[]{5000, 30000, 116000};
        for (long l : count) {
            Instant startTime = Instant.now();
            enqueueRecords(l, new TransactionFlowQueueImpl(), allTransactions)
                    .processTransactions()
                    .processDeclineTransactions()
                    .displayAll();

            Instant endTime = Instant.now();
            System.out.println("Total time taken to process " + l + " records " + (endTime.toEpochMilli() - startTime.toEpochMilli()) + " ms");
            System.out.println("------------------");
        }
    }
}