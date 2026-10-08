package com.payments;

import com.payments.entidades.PayPalPayment;
import com.payments.entidades.Payment;
import com.payments.excepciones.InsufficientFundsException;
import com.payments.excepciones.InvalidPaymentException;

public class PaymentsApp {
    public static void main(String[] args) {

        //Pruebas PayPalPayment
        Payment pay1 = new PayPalPayment("A001", 550, "descamillaa98@gmail.com", 1800);
        Payment pay2 = new PayPalPayment("A002", 550, "descamillaa98@gmail.com", 500);
        Payment pay4 = new PayPalPayment("A004", 550, "descamillaa98@gmail.com", 1800);

    }
}
