package com.payments.excepciones;

public class InvalidPaymentException extends RuntimeException {

    public InvalidPaymentException(String message) {

        super(message);
    }
}//esta excepcion se ejecuta cuando: el número de tarjeta es vacío o datos bancarios incompletos
