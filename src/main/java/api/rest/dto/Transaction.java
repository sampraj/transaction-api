package api.rest.dto;

import api.rest.domain.Balance;
import api.rest.domain.HistoryResponse;
import api.rest.domain.TxnHistory;
import api.rest.domain.TxnResponse;
import api.rest.exception.ValidationException;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Transaction implements Operation{

    private String id;
    private String type;
    private double amount;
    private Date txnDate;
    private Account account;

    public Transaction(){
        super();
    }
    public Transaction(String txnId, Account account, Date date, double amount,String type){
        super();
        this.id = txnId;
        this.account = account;
        this.txnDate = date;
        this.amount = amount;
        this.type = type;
    }
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public Date getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Transaction getTransaction() {
        return this;
    }

    @Override
    public TxnResponse build(Optional<Transaction> txn) throws ValidationException {
        Balance res = new Balance();
        if(txn.isPresent()) {
            res.setBalance(txn.get().getTransaction().getAccount().getBalance());
            res.setAccount(txn.get().getAccount().getAccountId());
            res.setStatusCode("0");
            res.setTransaction(txn.get().getTransaction().getId());
            res.setPlayer(txn.get().getAccount().getClient().getId());
            res.setDate(res.formatDate(txn.get().getTxnDate()));
        }else{
            throw new ValidationException("Transaction data not found","4004");
        }
        return res;
    }
}
