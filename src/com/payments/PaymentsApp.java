package com.payments;

import com.payments.entidades.*;
import com.payments.excepciones.*;

public class PaymentsApp {


    public static void main(String[] args) {
        PaymentManager manager = new PaymentManager();

        System.out.println("=== SISTEMA DE PROCESAMIENTO DE PAGOS ===");

        // métodos de pago
        //  constructores
        Payment creditCard = new CreditCardPayment("PAG-001", 1200.0, "1234-5678-9012-3456", "Juan Pérez", 5000.0);
        Payment paypal = new PayPalPayment("PAG-002", 450.0, "juan@example.com", 500.0);
        Payment bankTransfer = new BankTransferPayment("PAG-003", 2500.0, "ES9121000418450000000000", "BBVA", 1000.0); // Este fallará por saldo insuficiente

        System.out.println("\n--- PROCESANDO PAGOS ---");

        processAndRegister(creditCard, manager);
        processAndRegister(paypal, manager);
        processAndRegister(bankTransfer); // Intentará procesar y capturará la excepción sin tumbar el sistema

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

    private static void processAndRegister(Payment, PaymentManager manager) {
    }


}