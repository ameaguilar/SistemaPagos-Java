package com.payments;

import com.payments.entidades.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentManager {

    private List<Payment> payments;

    public PaymentManager() {
        this.payments = new ArrayList<>();
    }

    public void registerPayment(Payment payment) {
        if (payment != null) {
            payments.add(payment);
            System.out.println("Pago registrado exitosamente. ID: " + payment.getId());
        }
    }

    // Muestra todos los pagos almacenados
    public void showAllPayments() {
        if (payments.isEmpty()) {
            System.out.println("No hay pagos registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE PAGOS REGISTRADOS ---");
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    // Busca  ID
    public Payment findPaymentById(String id) {
        for (Payment payment : payments) {
            if (payment.getId().equalsIgnoreCase(id)) {
                return payment;
            }
        }
        return null;
    }


}