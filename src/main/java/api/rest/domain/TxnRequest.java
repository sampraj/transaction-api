package api.rest.domain;

import api.rest.dto.Transaction;

public class TxnRequest {
    public Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
