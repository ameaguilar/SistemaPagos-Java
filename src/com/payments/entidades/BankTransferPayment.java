package com.payments.entidades;

import com.payments.excepciones.InsufficientFundsException;
import com.payments.excepciones.InvalidPaymentException;
import com.payments.interfaces.Refundable;

public class BankTransferPayment extends Payment implements Refundable {

    private int accountNumber;
    private String bank;
    private double balance;

    public BankTransferPayment(String id, double amount, int accountNumber, String bank, double balance) {
        super(id, amount);

        if(accountNumber <= 0){
            throw new InvalidPaymentException("El formato de el número de cuenta es invalido");
        }

        this.accountNumber = accountNumber;
        this.bank = bank;

        if(balance < 0){
            throw new InvalidPaymentException("El saldo en la cuenta debe ser mayor a 0");
        }
        this.balance = balance;

    }// constructor BankTransferPayment


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

    }// processPayment

    public int getAccountNumber() {
        return accountNumber;
    }// processPayment

    public boolean refund(){

        System.out.println("Este método de pago no permite reembolsos");
        return false;

    }

    public void setAccountNumber(int accountNumber) {
        if(accountNumber <= 0){
            throw new InvalidPaymentException("El formato de el número de cuenta es invalido");
        }
        this.accountNumber = accountNumber;
    }// setAccountNumber

    public String getBank() {
        return bank;
    }// getBank

    public void setBank(String bank) {
        this.bank = bank;
    }// setBank

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
        return "BankTransferPayment{" +
                "accountNumber=" + accountNumber +
                ", bank='" + bank + '\'' +
                ", balance=" + balance +
                "} " + super.toString();
    }// toString

    public void mostrarInfo(){
        System.out.println("=============== Información de transacción ===============");
        System.out.println("ID: " + getId());
        System.out.println("Monto: $" + getAmount());
        System.out.println("Estatus: " + getStatus());
        System.out.println("Número de cuenta: " + getAccountNumber());
        System.out.println("Banco: " + getBank());
        System.out.println("Saldo: $" + getBalance());
        System.out.println("==========================================================");
    }// mostrarInfo

}
