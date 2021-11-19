package api.rest.dto;


import api.rest.domain.TxnResponse;
import api.rest.exception.ValidationException;

import java.util.Date;
import java.util.Optional;

public class Debit extends Transaction{

    public Debit(){
        super();
    }
    public Debit(String txnId, Account account, Date date, double amount,String type){
        super(txnId,account,date,amount,type);
    }

    @Override
    public TxnResponse build(Optional<Transaction> response) throws ValidationException {
        TxnResponse res = new Transaction().build(response);
        res.setStatusMessage("Withdrawal Successful");
        return res;
    }
}
