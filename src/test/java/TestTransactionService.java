
import api.rest.dao.TransactionDao;

import api.rest.domain.InitializeData;
import api.rest.dto.Transaction;
import api.rest.exception.ValidationException;
import api.rest.service.TransactionService;
import api.rest.service.TransactionServiceImpl;
import com.google.gson.Gson;

import org.easymock.EasyMock;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.easymock.EasyMockUnitils;
import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.InjectInto;
import org.unitils.inject.annotation.TestedObject;

import java.util.Optional;

public class TestTransactionService extends UnitilsJUnit4 {

    @TestedObject
    TransactionService service = new TransactionServiceImpl();
    @Mock
    @InjectInto(target= "service", property = "transactionDao")
    TransactionDao transactionDaoMock;

    Transaction transaction;
    Gson gson;

    @Test
    public void testCredit() throws ValidationException {
        gson = new Gson();
        InitializeData data = new InitializeData();
        service.init(gson.fromJson(initJson, InitializeData.class));
        transaction = gson.fromJson(depositJson, Transaction.class);
        EasyMock.expect(transactionDaoMock.deposit( transaction)).andReturn(Optional.of( transaction));
        EasyMockUnitils.replay();
        service.depositAmt("123",transaction);
    }

    @Test
    public void testDebit() throws ValidationException {
        gson = new Gson();
        InitializeData data = new InitializeData();
        service.init(gson.fromJson(initJson, InitializeData.class));
        transaction = gson.fromJson(withdrawJson, Transaction.class);
        EasyMock.expect(transactionDaoMock.withdraw( transaction)).andReturn(Optional.of(  transaction));
        EasyMockUnitils.replay();
        service.withdrawAmt("123",transaction);
    }

    @Test
    public void testBalance() throws ValidationException {
        gson = new Gson();
        InitializeData data = new InitializeData();
        service.init(gson.fromJson(initJson, InitializeData.class));
        transaction = gson.fromJson(depositJson, Transaction.class);
        EasyMock.expect(transactionDaoMock.balance( "123")).andReturn(Optional.of( transaction));
        EasyMockUnitils.replay();
        service.checkBalance("123");
    }

    /**
     * Static test data
     */
    private static final String depositJson = "{\"id\":\"123456678\",\"type\":\"C\",\"amount\":11.0,\"account\":{\"accountId\":\"12345\"}}";
    private static final String initJson = "{\"players\":[{\"id\":\"123\",\"account\":{\"balance\":10.0,\"accountId\":\"12345\"},\"name\":\"Sam\"},{\"id\":\"456\",\"account\":{\"balance\":20.0,\"accountId\":\"6789\"},\"name\":\"Raj\"}] }";
    private static final String withdrawJson = "{\"id\":\"123456491\",\"type\":\"C\",\"amount\":1.0,\"account\":{\"accountId\":\"12345\"}}";

}
