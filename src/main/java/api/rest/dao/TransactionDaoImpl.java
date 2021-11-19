package api.rest.dao;

import api.rest.dto.*;
import api.rest.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Component
public class TransactionDaoImpl implements TransactionDao{

    public static final String NO_RECORD_FOUND = "No record found!";
    public static final String ERR_4002 = "ERR_4002";

    @Override
    public Optional<Transaction> deposit(Transaction deposit) throws ValidationException {
        if(DAOUtil.getAccounts().size() == 0){
            throw new ValidationException(NO_RECORD_FOUND,ERR_4002);
        }
        Credit credit = new Credit(deposit.getId(),deposit.getAccount(),new Date(),deposit.getAmount(),deposit.getType());
        Account account = DAOUtil.getAccounts().get(deposit.getAccount().getAccountId());
        double balance = account.getBalance() + deposit.getAmount();
        DAOUtil.getAccounts().get(deposit.getAccount().getAccountId()).setBalance(balance);
        return updateTransaction(credit);
    }

    private Optional<Transaction> updateTransaction(Transaction txn) {

        final Player player = txn.getAccount().getClient();
        List<Operation> operations = DAOUtil.getOpertions().get(player.getId());
        if (operations == null){
            operations = new ArrayList<Operation>();
        }
        operations.add(txn);
        DAOUtil.getOpertions().put(player.getId(),operations);
        return Optional.of(txn);
    }

    @Override
    public Optional<Transaction> withdraw(Transaction withdraw) throws ValidationException {
        if(DAOUtil.getAccounts().size() == 0){
            throw new ValidationException(NO_RECORD_FOUND,ERR_4002);
        }
        Debit debit = new Debit(withdraw.getId(),withdraw.getAccount(),new Date(),withdraw.getAmount(),withdraw.getType());
        Account account = DAOUtil.getAccounts().get(withdraw.getAccount().getAccountId());
        if(account.getBalance() < withdraw.getAmount()){
            throw new ValidationException("ERROR: Insufficient balance.","ERR_5001");
        }
        double balance = account.getBalance() - withdraw.getAmount();
        DAOUtil.getAccounts().get(withdraw.getAccount().getAccountId()).setBalance(balance);
        return updateTransaction(debit);
    }

    @Override
    public Optional<Transaction> balance(String player) throws ValidationException {
        if(DAOUtil.getPlayers().get(player) == null){
            throw new ValidationException(NO_RECORD_FOUND,ERR_4002);
        }
        final Account account = DAOUtil.getPlayers().get(player).getAccount();
        account.setClient(DAOUtil.getPlayers().get(player) );
        final Transaction transaction = new Transaction();
        transaction.setAccount(account);
        return Optional.of(transaction);
    }

    @Override
    public List<Operation> transactionHistory(String player) throws ValidationException {
        if(DAOUtil.getPlayers().size() == 0 || DAOUtil.getOpertions().size() == 0){
            throw new ValidationException(NO_RECORD_FOUND, ERR_4002);
        }
        final Account account = DAOUtil.getPlayers().get(player).getAccount();
        final List<Operation> transactions = DAOUtil.getOpertions().get(player);
        return transactions;
    }
}
