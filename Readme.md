# Sistema de Procesamiento de Pagos

Un sistema dinámico y extensible desarrollado en Java que simula la gestión de transacciones de una tienda en línea. Este proyecto aplica los principios fundamentales de la **Programación Orientada a Objetos (POO)**, manejo de excepciones personalizadas, colecciones en Java y un flujo colaborativo profesional mediante Git y GitHub.

---

## Integrantes del Equipo

- **Adriana Benítez:** Desarrollo de la clase abstracta base `Payment` y la implementación de `CreditCardPayment`.
- **Dante Escamilla:** Desarrollo de los métodos de pago `PayPalPayment` y `BankTransferPayment`.
- **Amellali Mercado:** Creación de la interfaz `Refundable`, desarrollo de las excepciones personalizadas y reglas de validación.
- **Angélica Avendaño:** Desarrollo de `PaymentManager`, integración general del sistema, creación de `PaymentsApp` y ejecución de pruebas de integración.

---

## Tecnologías Utilizadas

- **Lenguaje:** Java (JDK 17+)
- **IDE:** IntelliJ IDEA
- **Control de Versiones:** Git & GitHub
- **Estructuras de Datos:** Java Collections Framework (`ArrayList`)

---

## Estructura del Proyecto

```text
src/
└── com/
    └── payments/
        ├── enums/
        │   └── PaymentStatus.java       # Estados: PENDING, APPROVED, REJECTED, REFUNDED
        ├── interfaces/
        │   └── Refundable.java          # Interfaz para métodos que admiten reembolsos
        ├── excepciones/
        │   ├── InsufficientFundsException.java # Excepción por saldo o límite insuficiente
        │   └── InvalidPaymentException.java    # Excepción por datos inválidos
        ├── entidades/
        │   ├── Payment.java             # Clase abstracta base
        │   ├── CreditCardPayment.java   # Subclase: Tarjeta de crédito
        │   ├── PayPalPayment.java       # Subclase: PayPal
        │   ├── BankTransferPayment.java # Subclase: Transferencia bancaria
        │   └── PaymentManager.java      # Administrador de pagos (Polimorfismo + ArrayList)
        └── PaymentsApp.java             # Clase principal y casos de prueba