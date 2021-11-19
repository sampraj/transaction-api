package api.rest.domain;

import api.rest.dto.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TxnHistory extends TxnResponse{

    private List<HistoryResponse> history;

    public List<HistoryResponse> getHistory() {
        if(history == null){
            history = new ArrayList<HistoryResponse>();
        }
        return history;
    }

    public void setHistory(List<HistoryResponse> history) {
        this.history = history;
    }
}
