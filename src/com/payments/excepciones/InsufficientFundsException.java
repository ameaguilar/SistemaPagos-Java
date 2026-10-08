package com.payments.excepciones;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String message) {

        super(message);
    }
}// esta excepcion se ejecuta cuando: es pago es válido pero el saldo o credito no alcanza
