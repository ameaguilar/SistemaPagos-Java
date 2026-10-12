package com.payments.entidades;
import com.payments.excepciones.InsufficientFundsException;
import com.payments.excepciones.InvalidPaymentException;
import com.payments.interfaces.Refundable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PayPalPayment extends Payment implements Refundable {

    private String email;
    private double balance;

    public PayPalPayment(String id, double amount, String email, double balance){
        super(id, amount);

        if(!validateEmail(email)){
            throw new InvalidPaymentException("El formato del correo electrónico es incorrecto");
        }
        this.email = email;

        if(balance < 0){
            throw new InvalidPaymentException("El saldo inicial no puede ser negativo");
        }
        this.balance = balance;
    }// constructor PayPalPayment

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+", Pattern.CASE_INSENSITIVE);
    private static boolean validateEmail(String emailStr) {
        if(emailStr == null){return false;}
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }// validateEmail

    @Override
    public boolean processPayment() throws InsufficientFundsException, InvalidPaymentException {

        if(getStatus() == PaymentStatus.PENDING){
            if(getAmount() <= balance){
                setBalance(balance - getAmount());
                setStatus(PaymentStatus.APPROVED);
                return true;
            } else {
                setStatus(PaymentStatus.REJECTED);
                throw new InsufficientFundsException("Saldo insuficiente");
            }
        } else if (getStatus() == PaymentStatus.APPROVED) {
            throw new InvalidPaymentException("La transacción ya fue realizada");
        } else if (getStatus() == PaymentStatus.REJECTED) {
            throw new InvalidPaymentException("La transacción fue rechazada");
        } else if (getStatus() == PaymentStatus.REFUNDED) {
            throw new InvalidPaymentException("La transacción fue reembolsada");
        }
        return false;

    }// constructor processPayment

    @Override
    public boolean refund(){

        if(getStatus() == PaymentStatus.APPROVED){
            setBalance(balance + getAmount());
            setStatus(PaymentStatus.REFUNDED);
            return true;
        }

        System.out.println("No se puede realizar un reembolso de transacciones pendientes, rechazadas o ya reembolsadas.");
        return false;

    }// refund

    public String getEmail() {
        return email;
    }// getEmail

    public void setEmail(String email){
        if(!validateEmail(email)){
            throw new InvalidPaymentException("El formato del correo electrónico es incorrecto");
        }
        this.email = email;
    }// setEmail

    public double getBalance() {
        return balance;
    }// getBalance

    public void setBalance(double balance) {
        if(balance < 0){
            throw new InvalidPaymentException("El saldo en la cuenta debe ser mayor a 0");
        }
        this.balance = balance;
    }// setBalance

    @Override
    public String toString() {
        return "PayPalPayment{" +
                "email='" + email + '\'' +
                ", balance=" + balance +
                "} " + super.toString();
    }// toString

    public void mostrarInfo(){
        System.out.println("=============== Información de transacción ===============");
        System.out.println("ID: " + getId());
        System.out.println("Monto: $" + getAmount());
        System.out.println("Estatus: " + getStatus());
        System.out.println("Correo electrónico: " + getEmail());
        System.out.println("Saldo: $" + getBalance());
        System.out.println("==========================================================");
    }// mostrarInfo

}
