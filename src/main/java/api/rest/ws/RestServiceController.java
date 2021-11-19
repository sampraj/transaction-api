package api.rest.ws;

import api.rest.domain.InitializeData;
import api.rest.domain.TxnRequest;
import api.rest.domain.TxnResponse;
import api.rest.exception.ValidationException;
import api.rest.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class RestServiceController {
	private static final Logger LOGGER = LogManager.getLogger(RestServiceController.class);

	@Autowired
	private TransactionService service;

	/**
	 * --> Initializing the player data and account, to have the in memory data to start with.
	 * --> This data will ensure the player and account information is already loaded to start with the transactions
	 * @param request request body
	 * @return status
	 * @throws Exception
	 */
	@PostMapping("/api/initialize")
	public Optional<String> initialize(
			@RequestBody InitializeData request) {
		LOGGER.info("Initializing the players info...");
		return service.init(request);
	}

	/**
	 *
	 * @param request --> body contain a JSON input for credit account
	 * @param id --> should be the one of the playerID that is given as initialization data
	 * @return transaction status
	 */
	@PostMapping("/api/credit/{id}")
	public TxnResponse depositOperation(
			@RequestBody TxnRequest txnRequest, @PathVariable String id) throws ValidationException {
		if (isValidRequest(txnRequest)){
			return new TxnResponse("Request body is invalid, please pass a valid request");
		}
		return service.depositAmt(id, txnRequest.transaction.getTransaction());
	}

	@PostMapping("/api/debit/{id}")
	public TxnResponse withdrawalOperation(
			@RequestBody TxnRequest txnRequest, @PathVariable String id) throws ValidationException {
		if (isValidRequest(txnRequest)){
			return new TxnResponse("Request body is invalid, please pass a valid request");
		}
		return service.withdrawAmt(id,txnRequest.transaction.getTransaction());
	}

	@GetMapping("/api/balance/{id}")
	public TxnResponse checkBalance(@PathVariable String id) throws ValidationException {
		if (id==null){
			return new TxnResponse("Please pass a valid request input");
		}
		return service.checkBalance(id);
	}

	@GetMapping("/api/history/{id}")
	public TxnResponse findTxnHistory( @PathVariable String id) throws ValidationException {
		if (id==null){
			return new TxnResponse("Please pass a valid request input");
		}
		return service.txnHistory(id);
	}

	private boolean isValidRequest(TxnRequest txnRequest) {
		return txnRequest == null || txnRequest.transaction.getTransaction() == null;
	}
}
