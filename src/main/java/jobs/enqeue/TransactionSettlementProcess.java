package jobs.enqeue;

import models.Transaction;
import queue.TransactionFlow;
import queue.TransactionFlowImpl;
import queue.DeclineTransactionImpl;
import util.ExcelUtil;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class TransactionSettlementProcess {
    public static void main(String[] args) throws IOException {
        long count[] = new long[] {5000, 30000, 116000};
        boolean runExperiment = false;
        for(int i = 0; i< count.length; i++){
        Instant startTime = Instant.now();
        enqueueRecords(count[i], runExperiment)
                .processTransactions()
                .processDeclineTransactions()
                .displayAll();

        System.out.println("------------------");
        Instant endTime = Instant.now();
        System.out.println("Total time taken to process " + count[i] + " records " + (endTime.toEpochMilli() - startTime.toEpochMilli())+ " ms");
        }
    }

    public static TransactionFlow enqueueRecords(long count, boolean runExperiment) throws IOException {

        List<Transaction> allTransactions = new ExcelUtil().getFromExcel();
        TransactionFlow transactionFlow;
        if(runExperiment){
            transactionFlow = new DeclineTransactionImpl();
        } else {
            transactionFlow = new TransactionFlowImpl();
        }
        Instant startTime = Instant.now();
        for (int i = 0; i < count; i++) {
            transactionFlow.enqueue(allTransactions.get(i));
        }
        Instant endTime = Instant.now();

        System.out.println("Total time taken to enqueue " + count + " records " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

        return transactionFlow;
    }
}