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

- **Lenguaje:** Java (JDK 21)
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

### *Clases Base y Entidades (`com.payments.entidades`)
* **`Payment` (Clase Abstracta):** Define la estructura común de un pago (`id`, `amount`, `status`) y declara el método abstracto `processPayment()`.
* **`CreditCardPayment` (Clase Concreta):** Representa pagos con tarjeta de crédito. Valida la longitud del plástico, verifica el límite disponible y enmascara los datos sensibles en su método `toString()`. Implementa `Refundable`.
* **`PayPalPayment` (Clase Concreta):** Modela pagos digitales mediante correo electrónico. Utiliza expresiones regulares (`Regex`) para validar el formato del correo y verifica el saldo de la cuenta. Implementa `Refundable`.
* **`BankTransferPayment` (Clase Concreta):** Modela transferencias bancarias. Valida la cuenta origen, el nombre de la institución financiera y el saldo en cuenta. No implementa `Refundable`.
* **`PaymentManager` (Clase Gestora):** Administra el registro, búsqueda y procesamiento de transacciones utilizando una colección `ArrayList<Payment>`. Aprovecha el polimorfismo para procesar cobros y calcular totales.**

---

### *Interfaces (`com.payments.interfaces`)
* **`Refundable` (Interfaz):** Define el contrato `refund()` para los métodos de pago que permiten devolver el dinero y restaurar el límite o saldo del usuario.

---

### *Enums (`com.payments.entidades`)
* **`PaymentStatus` (Enum):** Controla el ciclo de vida de la transacción mediante estados finitos: `PENDING`, `APPROVED`, `REJECTED` y `REFUNDED`.

---

### *Excepciones Personalizadas (`com.payments.excepciones`)
* **`InsufficientFundsException:`** Se dispara cuando el saldo o límite de crédito es menor al monto solicitado.
* **`InvalidPaymentException`:** Se dispara cuando los datos de entrada son inválidos (número de tarjeta corto, correo mal formado, campos vacíos o intentos de procesar transacciones ya finalizadas).

---

### *Clase Principal (`com.payments`)
* **`PaymentsApp`:** Contiene el método `main` y ejecuta los casos de prueba de integración (procesamiento exitoso, captura de excepciones, búsquedas por ID y ejecuciones de reembolso).