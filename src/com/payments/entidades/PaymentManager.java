package com.payments.entidades;
import java.util.ArrayList;
import java.util.List;


public class PaymentManager {

    // Array para guardar pagos de cualquier subclase: credit, paypal o banktransfer
    public ArrayList<Payment> payments = new ArrayList<>();


    //Registrar pago, mostrar pago, buscar pago, mostrar el total



    public void registerPayment(Payment payment) {
        if (payment.getStatus() == PaymentStatus.APPROVED && findPaymentById(payment.getId()) == null) {
            payments.add(payment);
            System.out.println("Pago registrado exitosamente. ID: " + payment.getId());
        }
    } //Metodo para registrar un pago en el Array


    public void showAllPayments() {
        if (payments.isEmpty()) {
            System.out.println("No hay pagos registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE PAGOS REGISTRADOS ---");
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    } // Metodo que muestra todos los pagos almacenados


    public Payment findPaymentById(String id) {
        for (Payment payment : payments) {
            if (payment.getId().equalsIgnoreCase(id)) {
                return payment;
            }
        }
        return null;
    } // Metodo que busca  ID


    public double getTotalProcessedAmount() {
        double total = 0;

        for (Payment payment : payments) {
            total += payment.getAmount();
        }

        return total;

    } // metodo que calcula el monto total de pagos realizados

}// class PaymentManager
