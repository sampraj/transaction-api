package api.rest.domain;

import api.rest.dto.Transaction;

public class TxnResponse {

    private String statusMessage;
    private String statusCode;

    public TxnResponse() {
    }
    public TxnResponse(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    public TxnResponse(String statusMessage,String code) {
        this.statusMessage = statusMessage;
        this.statusCode = code;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

}
