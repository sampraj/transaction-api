package api.rest.domain;

import java.util.Date;

public class HistoryResponse {

    private String id;
    private String type;
    private double amount;
    private Date txnDate;
    private String account;

    public HistoryResponse(String id,String type,double amount,Date txnDate, String account){
        this.id = id;
        this.type = type;
        this.account = account;
        this.txnDate = txnDate;
        this.amount = amount;
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

    public Date getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
