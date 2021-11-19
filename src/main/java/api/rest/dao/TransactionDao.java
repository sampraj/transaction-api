package api.rest.dao;

import api.rest.dto.Operation;
import api.rest.dto.Transaction;
import api.rest.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface TransactionDao {

    public List<Operation> transactionHistory(final String player) throws ValidationException;

    public Optional<Transaction> deposit(Transaction deposit) throws ValidationException;

    public Optional<Transaction> withdraw(Transaction withdraw) throws ValidationException;

    public Optional<Transaction> balance(final String player) throws ValidationException;

}
