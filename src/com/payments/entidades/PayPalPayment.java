package com.payments.entidades;

public class PayPalPayment extends Payment{

    private String email;
    private double saldo;

    public PayPalPayment(long id, double monto, String estadoPago, String email, double saldo) {
        super(id, monto, estadoPago);
        this.email = email;
        this.saldo = saldo;
    }// constructor PayPalPayment

    public void procesarPago(){

        if(this.saldo > this.monto){
            this.saldo -= this.monto;
        }

        System.out.println("\n========= Detalles de la compra =========");
        System.out.println("ID de transacción: " + this.id);
        System.out.println("==========================================");
    }
}
