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
```

## Conceptos de POO Aplicados

1. **Encapsulación:** Atributos privados (`private`) en todas las entidades con sus correspondientes métodos *getter* y *setter*, asegurando la integridad del estado de cada objeto (por ejemplo, impidiendo montos o saldos negativos).
2. **Abstracción:** Implementación de la clase base abstracta `Payment` para definir atributos comunes (`id`, `amount`, `status`) y forzar la firma del método `processPayment()`.
3. **Herencia:** Especialización de `Payment` a través de las subclases `CreditCardPayment`, `PayPalPayment` y `BankTransferPayment`.
4. **Polimorfismo:** La clase `PaymentManager` opera utilizando referencias abstractas `Payment`, permitiendo procesar y almacenar cualquier método de pago de manera uniforme.
5. **Interfaces:** Uso de la interfaz `Refundable` para acoplar la capacidad de reembolso únicamente a las clases que la soportan (`CreditCardPayment` y `PayPalPayment`), promoviendo el principio de segregación de interfaces.
6. **Manejo de Excepciones:** Lanzamiento y captura mediante un bloque `try-catch-finally` de excepciones personalizadas heredadas de `RuntimeException` (`InsufficientFundsException` e `InvalidPaymentException`), garantizando que la aplicación continúe su ejecución ante un cobro fallido.

---

## Decisiones de Diseño Destacadas

- **Control de Estado mediante `Enum`:** Se empleó `PaymentStatus` (`PENDING`, `APPROVED`, `REJECTED`, `REFUNDED`) para evitar errores por cadenas de texto sueltas y garantizar la consistencia en el ciclo de vida del pago.
- **Criterio de Reembolsos:** `Refundable` se aplicó únicamente a tarjetas de crédito y PayPal. Se excluyó `BankTransferPayment` debido a que las transferencias bancarias directas requieren procesos de compensación manuales o reglas de negocio distintas.
- **Seguridad en Visualización (`toString`):** En `CreditCardPayment`, se implementó el enmascaramiento automático de los dígitos del plástico, mostrando únicamente los últimos 4 por razones de privacidad.

---

## Flujo de Trabajo en Git y GitHub

El equipo adoptó un modelo basado en **Git Flow Simplificado**:

1. **`main`**: Rama con código estable, probado y listo para entrega.
2. **`develop`**: Rama base para la integración continua del equipo.
3. **`Ramas individuales por integrante`**: Cada colaborador trabajó en una rama personal nombrada con su propio nombre para desarrollar, probar y subir sus módulos asignados antes de crear sus Pull Requests hacia `develop`