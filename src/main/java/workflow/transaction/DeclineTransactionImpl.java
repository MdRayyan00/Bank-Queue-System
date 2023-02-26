package workflow.transaction;

import models.Transaction;

import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class DeclineTransactionImpl implements TransactionFlow {
    Queue<Transaction> allTransactions = new LinkedList<>();
    //dead letter queue
    Queue<Transaction> declinedTransactions = new LinkedList<>();

    Queue<Transaction> settledTransactions = new LinkedList<>();

    HashMap<String, Double> accountBalanceCheck = new HashMap<>(); //for storing account number and it's balance

    // Adding transaction in the Queue from the transactions file
    @Override
    public void enqueue(Transaction transaction) {
        allTransactions.offer(transaction);
    }

    // Each transaction is checked and then status of transaction is updated (Settled, Declined) according to the checks
    @Override
    public TransactionFlow processTransactions() {
        Iterator iteratorForBankBalance = allTransactions.iterator();
        while(iteratorForBankBalance.hasNext()){
            Transaction current = (Transaction) iteratorForBankBalance.next();
            if(!accountBalanceCheck.containsKey(current.getAccountNumber())){
                accountBalanceCheck.put(current.getAccountNumber(), current.getBalance());
            } 
        }
        Iterator iterator = allTransactions.iterator();
        while(iterator.hasNext()){
            Transaction current = (Transaction) iterator.next();
            String currentAccountNumber = current.getAccountNumber();
            Double currentBalance = accountBalanceCheck.get(currentAccountNumber);
            if(current.getWithdrawalAmount() == 0){
                //Deposit
                Double currentDeposit = current.getDepositAmount();
                Double newBalance = (currentBalance + currentDeposit);
                accountBalanceCheck.put(currentAccountNumber, newBalance);
                settledTransactions.add(current);
            } else {
                //Withdrawal
                Double currentWithdrawal = current.getWithdrawalAmount();
                if(currentBalance > currentWithdrawal){
                    Double newBalance = (currentBalance - currentWithdrawal);
                    accountBalanceCheck.put(currentAccountNumber, newBalance);
                    settledTransactions.add(current);
                } else {
                    //low balance 
                    System.out.println("Not enough Balance for transaction having ID " + current.getTransactionId());
                    declinedTransactions.add(current);
                }
            }
        }
        return this;
    }

    // Once all the transactions are processed the 'Declined' transactions are processed again in case there is bank failure or data issue.
    @Override
    public TransactionFlow processDeclineTransactions() {
        Instant startTime = Instant.now();
        int preProcessQueueSize = declinedTransactions.size();
        int diff = 0;
        System.out.println("Number of Declined Transactions before processing declined transactions = " + preProcessQueueSize);
        Iterator iterator = declinedTransactions.iterator();
        while(iterator.hasNext()){
            Transaction currentTransaction = (Transaction) iterator.next();
            Double currentWithdrawalAmount = currentTransaction.getWithdrawalAmount();
            String currentAccountNumber = currentTransaction.getAccountNumber();
            Double accountBalance = accountBalanceCheck.get(currentAccountNumber);
            if (currentWithdrawalAmount < accountBalance){
                // transactions can be processed and marked settled
                Double newBalance = accountBalance - currentWithdrawalAmount;
                accountBalanceCheck.put(currentAccountNumber, newBalance);
                settledTransactions.add(currentTransaction);
                iterator.remove();
            }
        }
        int postProcessQueueSize = declinedTransactions.size();
        diff = preProcessQueueSize - postProcessQueueSize;
        System.out.println("Number of Declined Transactions after processing declined transactions = " + postProcessQueueSize);
        System.out.println("Number of settled transaction after processing the decline queue: "+diff);
        Instant endTime = Instant.now();
        System.out.println("Total time taken to process all the declined " + preProcessQueueSize + " records " + (endTime.toEpochMilli() - startTime.toEpochMilli()) + " ms");
        
        return this;
    }

    // All the 'Settled' transactions are displayed.
    @Override
    public void displayAll() {
        }
    }

