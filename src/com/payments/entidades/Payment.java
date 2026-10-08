package com.payments.entidades;

import com.payments.excepciones.InsufficientFundsException;
import com.payments.excepciones.InvalidPaymentException;

public abstract class Payment {
    private String id;
    private double amount;
    private PaymentStatus status; // enum

    public Payment(String id, double amount){
        if (amount <=0){
            // throw -> detiene la creación de un objeto inválido
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }//if

        this.id = id;
        this.amount = amount;
        this.status = PaymentStatus.PENDING; // el pago siempre inicia en pending
    } // constructor

    // métod de pagos que lanza las excepciones personalizadas
    public abstract boolean processPayment() throws InsufficientFundsException, InvalidPaymentException;


    public String getId() {
        return id;
    } //getid

    public double getAmount() {
        return amount;
    } //getmonto

    public PaymentStatus getStatus() {
        return status;
    } //getestado

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }// setestado -> actualiza el estado cuando se procesa o reembolsa

    @Override
    public String toString(){
        return "ID: " + id +  "\nMonto: " + amount + "\nEstado: " + status;
    }// override
} // class Payment
