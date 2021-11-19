package api.rest.service;

import api.rest.domain.InitializeData;
import api.rest.domain.TxnRequest;
import api.rest.domain.TxnResponse;
import api.rest.dto.Operation;
import api.rest.dto.Transaction;
import api.rest.exception.ValidationException;

import java.util.Optional;

public interface TransactionService {

    public Optional<String> init(InitializeData data);

    public TxnResponse depositAmt(String playerId, Operation creditTxn) throws ValidationException;

    public TxnResponse withdrawAmt(String playerId, Operation creditTxn) throws ValidationException;

    public TxnResponse checkBalance(String player) throws ValidationException;

    public TxnResponse txnHistory(String player) throws ValidationException;

}
