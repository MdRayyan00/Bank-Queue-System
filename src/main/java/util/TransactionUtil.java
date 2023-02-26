package util;

import models.Transaction;
import workflow.transaction.TransactionFlow;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class TransactionUtil {
    public static TransactionFlow enqueueRecords(long count, TransactionFlow transactionFlow) throws IOException {
        List<Transaction> allTransactions = new ExcelUtil().getFromExcel();

        Instant startTime = Instant.now();
        for (int i = 0; i < count; i++) {
            transactionFlow.enqueue(allTransactions.get(i));
        }
        Instant endTime = Instant.now();

        System.out.println("Total time taken to enqueue " + count + " records " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

        return transactionFlow;
    }
}
