package com.payments.entidades;

import com.payments.excepciones.InsufficientFundsException;
import  com.payments.excepciones.InvalidPaymentException;
import com.payments.interfaces.Refundable;

public class CreditCardPayment extends Payment implements Refundable{

    private String cardNumber;
    private String cardHolder;
    private double availableLimit;

    public CreditCardPayment (String id, double amount, String cardNumber, String cardHolder, double availableLimit ){
        super(id, amount); // ejecuta constructor de Payment
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.availableLimit = availableLimit;
    } // constructor

    @Override
    public boolean processPayment() throws InsufficientFundsException, InvalidPaymentException {
        //1. Validar número de tarjeta
        if (cardNumber == null ||cardNumber.isBlank() || cardNumber.length()<16){
            setStatus(PaymentStatus.REJECTED);
            throw new InvalidPaymentException("Número de tarjeta inválido. Debe contener al menos 16 dígitos");
        } //primer if

        //2. Validar monto a pagar con el disponible
        if (getAmount()>availableLimit){
            setStatus(PaymentStatus.REJECTED);
            throw new InsufficientFundsException("El monto $" + getAmount() + " supera el límite disponible: $" + availableLimit);
        }//segundo if

        availableLimit -= getAmount();
        setStatus(PaymentStatus.APPROVED);
        return true;
    } //override boolean

    @Override
    public boolean refund(){
        if (getStatus() == PaymentStatus.APPROVED){ // solo se procesa si el pago ya fue aprobado
            availableLimit += getAmount(); // se devuelve el monto al límite de la tarjeta
            setStatus(PaymentStatus.REFUNDED);
            System.out.println("Reembolso procesado: +$" + getAmount());
            return true;
        }//if
        System.out.println("No se puede reembolsar un pago que no ha sido procesado");
        return false;
    } //override boolean refund

    public String getCardNumber() {
        return cardNumber;
    } // getcardNumber

    public String getCardHolder() {
        return cardHolder;
    } // getcardHolder

    public double getAvailableLimit() {
        return availableLimit;
    }//getAvailableLimit

    @Override
    //por seguridad, al momento de mostrar la información de pago solo se muestran los últimos 4 dígitos de la tarjeta
    public String toString() {
        String lastDigits = (cardNumber != null && cardNumber.length() >= 4)
                ? cardNumber.substring(cardNumber.length() - 4)
                : "****";
        return super.toString() + " | Tarjeta: **** " + lastDigits + " | Titular: " + cardHolder;
    } // override toString

} // class
