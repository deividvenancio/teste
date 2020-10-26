package br.com.teste.demo.exception;

public class BusinessRuleException extends RuntimeException  {

    public BusinessRuleException(String message, String ... value) {
        super(java.text.MessageFormat.format(message, value));
    }

    public BusinessRuleException(String message) {
        super(message);
    }

}
