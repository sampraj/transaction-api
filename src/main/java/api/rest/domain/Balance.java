package api.rest.domain;

import api.rest.dto.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class Balance extends TxnResponse{

    private double balance;
    private String date;
    private String account;
    private String player;
    private String transaction;

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Current date and time
     * @return date
     */
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public TxnResponse build(Optional<Transaction> response){
        Balance res = new Balance();
        if(response.isPresent()) {
            res.setBalance(response.get().getAccount().getBalance());
            res.setAccount(response.get().getAccount().getAccountId());
            res.setStatusCode("0");
            res.setDate(formatDate(new Date()));
            res.setStatusMessage("Balance enquiry Successful");
            res.setPlayer(response.get().getAccount().getClient().getId());
            res.setTransaction("-");
        }else {
            res.setStatusCode("4004");
            res.setStatusMessage("No date found!");
        }
        return res;
    }
    public String formatDate(Date date){
       return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
    }
}
