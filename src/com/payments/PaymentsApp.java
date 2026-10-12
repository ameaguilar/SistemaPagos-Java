package com.payments;

import com.payments.entidades.*;
import com.payments.excepciones.*;

public class PaymentsApp {


    public static void main(String[] args) {


        PaymentManager manager = new PaymentManager();

        System.out.println("=== SISTEMA DE PROCESAMIENTO DE PAGOS ===");

        // métodos de pago
        // Se crea un metodo de pago de cada uno

        PayPalPayment pay1 = new PayPalPayment("A001", 550, "descamillaa98@gmail.com", 1800);
        Payment creditCard = new CreditCardPayment("PAG-001", 1200.0, "1234-5678-9012-3456", "Juan Pérez", 5000.0);
        Payment paypal = new PayPalPayment("PAG-002", 450.0, "juan@example.com", 500.0);
        Payment bankTransfer = new BankTransferPayment("PAG-003", 2500.0, 1, "BBVA", 1000.0); // Este fallará por saldo insuficiente
        BankTransferPayment bank2 = new BankTransferPayment("BT002", 4500.00, 987654321, "Santander", 20000.00);
        CreditCardPayment credit2 = new CreditCardPayment("CC002", 2500.00, "5425233430109903", "María López", 15000.00);



        System.out.println("\n--- PROCESANDO PAGOS ---");

        processAndRegister(creditCard, manager);
        processAndRegister(paypal, manager);
        processAndRegister(paypal, manager);
        processAndRegister(bankTransfer, manager); // Intentará procesar y capturará la excepción sin tumbar el sistema
        processAndRegister(pay1, manager);
        processAndRegister(bank2, manager);
        processAndRegister(credit2, manager);

        System.out.println("\n--- PAGOS REEMBOLSADOS ---");
        pay1.refund(); //Se realiza reembolso por metodo de pago PayPal
        bank2.refund();
        credit2.refund();



        // Mostrar pagos registrados
        manager.showAllPayments();
        System.out.println("Monto total registrado: $" + manager.getTotalProcessedAmount());

    }


    private static void processAndRegister(
            Payment payment,
            PaymentManager manager
    ) {
        try {
            payment.processPayment();
        } catch (InsufficientFundsException e) {
            System.out.println("Fondos insuficientes: " + e.getMessage());
        } catch (InvalidPaymentException e) {
            System.out.println("Pago inválido: " + e.getMessage());
        } finally {
            manager.registerPayment(payment);
        }
    } // Metodo que procesa un pago y luego lo registra en PaymentApp


}//class PaymentApp