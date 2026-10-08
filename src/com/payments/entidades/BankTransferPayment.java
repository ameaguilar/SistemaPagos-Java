package com.payments.entidades;

import com.payments.excepciones.InsufficientFundsException;
import com.payments.excepciones.InvalidPaymentException;

public class BankTransferPayment extends Payment{

    private int accountNumber;
    private String bank;
    private int balance;

    public BankTransferPayment(String id, double amount, int accountNumber, String bank, int balance) {
        super(id, amount);
        this.accountNumber = accountNumber;
        this.bank = bank;
        this.balance = balance;
    }

    @Override
    public boolean processPayment() throws InsufficientFundsException, InvalidPaymentException {
        return false;
    }
}
