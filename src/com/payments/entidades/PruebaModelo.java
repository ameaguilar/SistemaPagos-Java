import com.payments.entidades.CreditCardPayment;
import com.payments.excepciones.InsufficientFundsException;
import com.payments.excepciones.InvalidPaymentException;

public class PruebaModelo {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DEL MODELO DE PAGOS ===\n");

        // 1. Prueba de pago exitoso (Tarjeta con límite suficiente)
        try {
            System.out.println("--- Caso 1: Pago Exitoso ---");
            CreditCardPayment pagoExitoso = new CreditCardPayment(
                    "PAG-001",
                    1500.0,
                    "1234567812345678",
                    "Adriana Benítez",
                    5000.0
            );

            System.out.println("Estado inicial: " + pagoExitoso.getEstado()); // Debería ser PENDING
            System.out.println("Procesando pago...");

            boolean resultado = pagoExitoso.processPayment();

            System.out.println("¿Pago procesado?: " + resultado);
            System.out.println("Estado final: " + pagoExitoso.getEstado()); // Debería ser APPROVED
            System.out.println("Límite restante: $" + pagoExitoso.getLimiteDisponible());
            System.out.println("Info completa:\n" + pagoExitoso);

        } catch (Exception e) {
            System.out.println("Error inesperado en Caso 1: " + e.getMessage());
        }

        System.out.println("\n-----------------------------------\n");

        // 2. Prueba de fallo por Límite Insuficiente
        try {
            System.out.println("--- Caso 2: Fondo/Límite Insuficiente ---");
            CreditCardPayment pagoRechazado = new CreditCardPayment(
                    "PAG-002",
                    2000.0,
                    "1234567812345678",
                    "Adriana Benítez",
                    500.0 // Límite menor al monto
            );

            pagoRechazado.processPayment();

        } catch (InsufficientFundsException e) {
            System.out.println("✔ Excepción capturada correctamente: " + e.getMessage());
        } catch (InvalidPaymentException e) {
            System.out.println("Error: No debía fallar por datos de tarjeta.");
        }

        System.out.println("\n-----------------------------------\n");

        // 3. Prueba de fallo por Tarjeta Inválida
        try {
            System.out.println("--- Caso 3: Número de Tarjeta Inválido ---");
            CreditCardPayment tarjetaInvalida = new CreditCardPayment(
                    "PAG-003",
                    300.0,
                    "1234", // Tarjeta corta (< 16 dígitos)
                    "Adriana Benítez",
                    1000.0
            );

            tarjetaInvalida.processPayment();

        } catch (InvalidPaymentException e) {
            System.out.println("✔ Excepción capturada correctamente: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Error: No debía fallar por fondos.");
        }
    }
}