package runner;

import models.Transaction;
import util.ExcelUtil;
import util.TransactionUtil;
import workflow.transaction.TransactionFlow;
import workflow.transaction.TransactionFlowListImpl;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class TransactionSettlementProcessUsingList {
    public static void main(String[] args) throws IOException {
        TransactionUtil.enqueueRecords(5000, new TransactionFlowListImpl())
                .processTransactions()
                .processDeclineTransactions()
                .displayAll();

        System.out.println("------------------");

        enqueueRecords(30000)
                .processTransactions()
                .processDeclineTransactions();
        //.displayAll();

        System.out.println("------------------");

        enqueueRecords(116000)
                .processTransactions()
                .processDeclineTransactions();
        //.displayAll();
    }

    public static TransactionFlow enqueueRecords(long count) throws IOException {
        List<Transaction> allTransactions = new ExcelUtil().getFromExcel();
        TransactionFlow transactionFlow = new TransactionFlowListImpl();

        Instant startTime = Instant.now();
        for (int i = 0; i < count; i++) {
            transactionFlow.enqueue(allTransactions.get(i));
        }
        Instant endTime = Instant.now();

        System.out.println("Total time taken to enqueue " + count + " records " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

        return transactionFlow;
    }
}