package br.com.smartinvest.smart_invest_api.exceptions;

public class RequestIsNullException extends RuntimeException{

    public RequestIsNullException(String message) {
        super(message + " este request está nulo, tente com um válido.");
    }
}
