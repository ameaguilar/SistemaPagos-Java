package com.payments;

import com.payments.entidades.*;
import com.payments.excepciones.*;

public class PaymentsApp {


    public static void main(String[] args) {


        PaymentManager manager = new PaymentManager();

        System.out.println("=== SISTEMA DE PROCESAMIENTO DE PAGOS ===");

        // métodos de pago
        // Se crea un metodo de pago de cada uno

        //Payment pay1 = new PayPalPayment("A001", 550, "descamillaa98@gmail.com", 1800);
        
        //Payment creditCard = new CreditCardPayment("PAG-001", 1200.0, "1234-5678-9012-3456", "Juan Pérez", 5000.0);
        //Payment paypal = new PayPalPayment("PAG-002", 450.0, "juan@example.com", 500.0);
        //Payment bankTransfer = new BankTransferPayment("PAG-003", 2500.0, 1, "BBVA", 1000.0); // Este fallará por saldo insuficiente



        System.out.println("\n--- PROCESANDO PAGOS ---");

        processAndRegister(pay1, manager);
        //processAndRegister(paypal, manager);
        //processAndRegister(bankTransfer, manager); // Intentará procesar y capturará la excepción sin tumbar el sistema

        // Mostrar pagos registrados
        manager.showAllPayments();
        System.out.println("Monto total registrado: $" + manager.getTotalProcessedAmount());

        // Busca por ID
        System.out.println("\n--- BÚSQUEDA DE PAGOS ---");
        Payment found = manager.findPaymentById("PAG-001");
        if (found != null) {
            System.out.println("Pago encontrado: " + found);
        } else {
            System.out.println("Pago no encontrado.");
        }

        Payment notFound = manager.findPaymentById("PAG-999");
        if (notFound == null) {
            System.out.println("Búsqueda PAG-999: El pago no existe (Manejo correcto).");
        }


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
    } // Metodo que procesa un pago y luego lo regista en PaymentApp


}//class PaymentApp




