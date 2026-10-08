package com.payments.entidades;

import com.payments.excepciones.InsufficientFundsException;
import com.payments.excepciones.InvalidPaymentException;

public class PayPalPayment extends Payment{

    private String email;
    private double saldo;

    public PayPalPayment(String id, double monto, String email, double saldo) {
        super(id, monto);
        this.email = email;
        this.saldo = saldo;
    }// constructor PayPalPayment

    @Override
    public boolean processPayment() throws InsufficientFundsException, InvalidPaymentException {
        return false;
    }
}
