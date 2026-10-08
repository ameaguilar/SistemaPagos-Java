package com.payments.entidades;

import com.payments.excepciones.InsufficientFundsException;
import com.payments.excepciones.InvalidPaymentException;

public abstract class Payment {
    private String id;
    private double monto;
    private EstadoPago estado; // enum

    public Payment(String id, double monto){
        if (monto <=0){
            // throw -> detiene la creación de un objeto inválido
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }//if

        this.id = id;
        this.monto = monto;
        this.estado = EstadoPago.PENDING; // el pago siempre inicia en pending
    } // constructor

    // métod de pagos que lanza las excepciones personalizadas
    public abstract boolean processPayment() throws InsufficientFundsException, InvalidPaymentException;


    public String getId() {
        return id;
    } //getid

    public double getMonto() {
        return monto;
    } //getmonto

    public EstadoPago getEstado() {
        return estado;
    } //getestado

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }// setestado -> actualiza el estado cuando se procesa o reembolsa

    @Override
    public String toString(){
        return "ID: " + id +  "\nMonto: " + monto + "\nEstado: " + estado;
    }// override
} // class Payment
