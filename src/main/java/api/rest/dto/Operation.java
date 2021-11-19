package api.rest.dto;

import api.rest.domain.TxnResponse;
import api.rest.exception.ValidationException;

import java.util.Optional;

public interface Operation {
    public TxnResponse build(Optional<Transaction> txn) throws ValidationException;
}
