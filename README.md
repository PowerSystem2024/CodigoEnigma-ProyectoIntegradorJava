# 🎰 Proyecto de Ruleta en Java 🎲

Bienvenidos al proyecto de **Ruleta del Casino**. Este juego simula una versión de la ruleta y permite a los jugadores realizar diferentes tipos de apuestas, gestionar su saldo de fichas y probar su suerte en cada ronda. 

## 👥 Integrantes del Equipo
- Aguirre Cerullo, Rocío Belén
- Colombo, Agustín
- Dicalbo, Juan Cruz
- Dominguez, Eliana
- Elias, Francisco
- Ramos, Dilan
- Rojas, Arón
  
## 📝 Descripción del Proyecto

Este juego permite a los usuarios apostar a:
- **Números específicos** (con un pago de 35:1),
- **Colores** (rojo o negro, con un pago de 1:1),
- O hacer **apuestas combinadas** entre números y colores.

El jugador inicia con un saldo de fichas, que puede aumentar o perder en cada ronda.

## 📋 Características Principales

- **Ruleta**: Incluye los números del 0 al 36 con un único cero verde.
- **Apuestas a Color**: Permite apostar a rojo o negro, con una ganancia de 1:1.
- **Apuestas a Número**: Apuesta a números específicos con una ganancia de 35:1.
- **Apuestas Combinadas**: Apuesta tanto a números como a colores para más oportunidades de ganar.
- **Saldo de Fichas**: Cada jugador comienza con un saldo inicial ajustable de fichas.

## 🚀 Estructura del Código

- **Clases y Métodos Principales**:
  - **`main()`**: Controla el flujo del juego y gestiona el saldo del jugador.
  - **`parseNumeros(String input)`**: Convierte la entrada del usuario en una lista de números válidos para la apuesta.
  - **`parseColores(String input)`**: Procesa los colores seleccionados por el usuario para la apuesta.
  - **`obtenerCantidadApuesta(Scanner scanner, int saldo)`**: Verifica y valida la cantidad de fichas apostadas.

## 🎲 Instrucciones de Juego

1. **Configuración Inicial**: Asegúrate de tener Java instalado en tu sistema.
2. **Compila y Ejecuta el Juego**:
   ```bash
   javac Rulet_JavaConFichas.java
   java Rulet_JavaConFichas
   ```
3. **Opciones de apuesta**:
- Selecciona el tipo de apuesta entre números, colores o una combinación.
- Ingresa la cantidad de fichas que deseas apostar y observa el resultado de la ruleta.
4. **Resultado y Saldo**: Si ganas, el sistema incrementa tu saldo según el tipo de apuesta realizada.

## 🎲 Ejemplo de una Ronda de Apuestas

1. El jugador selecciona el tipo de apuesta e ingresa los números o colores deseados.
2. Luego, elige la cantidad de fichas que desea apostar.
3. La ruleta genera un número ganador y verifica si el jugador ha ganado.
4. Si el jugador acierta en su apuesta, el saldo se incrementa según el tipo de apuesta realizada.

  ## 📌 Ejemplo de Ejecución

```plaintext
Bienvenido a la Ruleta del Casino.
Tu saldo inicial es de 1000 fichas.

Seleccione su tipo de apuesta:
1. Apostar a uno o más números (0-36)
2. Apostar a uno o más colores (Rojo/Negro)
3. Apostar a números y colores
4. Salir
Ingrese la opción deseada: 1
```

## 📝 Notas Adicionales

- **Límite de Fichas**: El saldo no puede ser negativo. Si el jugador se queda sin fichas, el juego termina automáticamente.
- **Verificación de Apuestas**: El programa valida que las fichas apostadas no superen el saldo disponible del jugador.

---
by Código Enigma
