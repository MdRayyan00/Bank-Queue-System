package models;

import java.util.Date;

public class Transaction {
    private String transactionId;
    private String accountNumber;
    private Date date;
    private String transactionDetails;
    private Double withdrawalAmount;
    private Double depositAmount;
    private Double balance;
    private String status;

    public Transaction() {
    }

    public Transaction(String transactionId, String accountNumber, Date date, String transactionDetails, Double withdrawalAmount, Double depositAmount, Double balance, String status) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.date = date;
        this.transactionDetails = transactionDetails;
        this.withdrawalAmount = withdrawalAmount;
        this.depositAmount = depositAmount;
        this.balance = balance;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public Double getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(Double withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public Double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
