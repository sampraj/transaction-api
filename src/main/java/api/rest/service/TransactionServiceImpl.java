package api.rest.service;

import api.rest.dao.DAOUtil;
import api.rest.dao.TransactionDao;
import api.rest.domain.*;
import api.rest.dto.*;

import api.rest.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TransactionServiceImpl implements TransactionService{

    public static final String CODE_5002 = "5002";
    private static final Logger LOGGER = LogManager.getLogger(TransactionServiceImpl.class);
    private static final String DUPLICATE_TXN_ERR = "ERROR : Duplicate transaction reference..";
    public static final String NO_SUCH_RECORD_FOUND = "No such record found!";
    public static final String CODE_4004 = "4004";

    @Autowired
    TransactionDao transactionDao;

    @Override
    public Optional<String> init(InitializeData data) {

        if(data == null){
            return Optional.of("Input data cannot be null, please provide the request body JSON in the format : " + Constants.SAMPLE_INPUT);
        }
        try {
            data.getPlayers().forEach(player -> DAOUtil.getPlayers().put(player.getId(),player));
            data.getPlayers().forEach(player -> DAOUtil.getAccounts().put(player.getAccount().getAccountId(),player.getAccount()));
            data.getPlayers().forEach(player -> LOGGER.info(player.toString()));
        }catch (Exception e){
            return Optional.of(Constants.ERROR_MSG);

        }
        return Optional.of(Constants.SUCCESS_MSG);
    }

    @Override
    public TxnResponse depositAmt(String player, Operation creditTxn) throws ValidationException {
       final Transaction depositTxn =  ((Transaction)creditTxn);
       if (!isValidTransaction(depositTxn)){
            return new TxnResponse(DUPLICATE_TXN_ERR);
       }
       if(!isValidCustomer(player)){
           return new TxnResponse(NO_SUCH_RECORD_FOUND, CODE_4004);
       }
        depositTxn.setAccount(DAOUtil.getPlayers().get(player).getAccount());
        depositTxn.getAccount().setClient(DAOUtil.getPlayers().get(player));
        Optional<Transaction> response = transactionDao.deposit(depositTxn);
        return buildCreditResponse(response);
    }

    private boolean isValidCustomer(String player) {
        if(DAOUtil.getPlayers().get(player) != null){
            return true;
        }
        return false;
    }

    private boolean isValidTransaction(Transaction depositTxn) {
        final String txnId = depositTxn.getId();
        final boolean[] status = new boolean[1];
        status[0] = true;
        DAOUtil.getOpertions().forEach((k, v) -> {
            v.forEach(txn -> {
                final String id = ((Transaction)txn).getId();
                if (id.equals(txnId)){
                    status[0] = false;
                }
            });
        });
        return status[0];
    }

    private TxnResponse buildCreditResponse(Optional<Transaction> response) throws ValidationException {
        return new Credit().build(response);
    }

    @Override
    public TxnResponse withdrawAmt(String player, Operation debitTxn) throws ValidationException {
        final Transaction withdrawTxn =  ((Transaction)debitTxn);
        if (!isValidTransaction(withdrawTxn)){
            return new TxnResponse(DUPLICATE_TXN_ERR,CODE_5002);
        }
        if(!isValidCustomer(player)){
            return new TxnResponse(NO_SUCH_RECORD_FOUND, CODE_4004);
        }
        withdrawTxn.setAccount(DAOUtil.getPlayers().get(player).getAccount());
        withdrawTxn.getAccount().setClient(DAOUtil.getPlayers().get(player));
        Optional<Transaction> response = null;
        try {
            response = transactionDao.withdraw(withdrawTxn);
        } catch (ValidationException e) {
            LOGGER.error(e);
            return new TxnResponse(e.getMessage(),e.getCode());
        }
        return buildDebitResponse(response);
    }

    private TxnResponse buildDebitResponse(Optional<Transaction> response) throws ValidationException {
        return new Debit().build(response);
    }

    @Override
    public TxnResponse checkBalance(String player) throws ValidationException {
        if(!isValidCustomer(player)){
            return new TxnResponse(NO_SUCH_RECORD_FOUND, CODE_4004);
        }
        Optional<Transaction> response = transactionDao.balance(player);
        return buildBalanceResponse(response);
    }

    private TxnResponse buildBalanceResponse(Optional<Transaction> response) {
        return new Balance().build(response);
    }

    @Override
    public TxnResponse txnHistory(String player) throws ValidationException {
        if(!isValidCustomer(player)){
            return new TxnResponse(NO_SUCH_RECORD_FOUND, CODE_4004);
        }
        List<Operation> response = transactionDao.transactionHistory(player);
        TxnHistory history = new TxnHistory();
        response.forEach(txn ->{
            Transaction t = (Transaction)txn;
            history.getHistory().add(buildHistoryResponse(Optional.of(t)));
        });
        history.setStatusCode("200 OK");
        history.setStatusMessage("Success");
        return history;
    }

    private HistoryResponse buildHistoryResponse(Optional<Transaction> txn) {
        final Transaction t = txn.get();
        return new HistoryResponse(
                t.getId(),t.getType(),t.getAmount(),t.getTxnDate(),t.getAccount().getAccountId());
    }
}
