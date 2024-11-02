import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Rulet_JavaConFichas {
    // Crear los números de la ruleta europea (con un solo cero)
    private static final String[] numerosRuleta = {
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", 
        "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", 
        "28", "29", "30", "31", "32", "33", "34", "35", "36"
    };

    // Crear los colores de cada número: verde (cero), rojo y negro
    private static final String[] coloresRuleta = {
        "Verde", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", 
        "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Rojo", 
        "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", 
        "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Bienvenido a la Ruleta del Casino.");

        // Inicializar el saldo de fichas
        int saldo = 1000; // Puedes cambiar el saldo inicial según prefieras
        System.out.println("Tu saldo inicial es de " + saldo + " fichas.");

        boolean continuar = true;

        while (continuar && saldo > 0) {
            System.out.println("\nSaldo actual: " + saldo + " fichas.");

            // Mostrar opciones de apuesta
            System.out.println("\nSeleccione su tipo de apuesta:");
            System.out.println("1. Apostar a uno o más números (0-36)");
            System.out.println("2. Apostar a uno o más colores (Rojo/Negro)");
            System.out.println("3. Apostar a números y colores");
            System.out.println("4. Salir");

            System.out.print("Ingrese la opción deseada: ");
            int opcion = 0;
            try {
                opcion = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada no válida. Intente de nuevo.");
                scanner.nextLine(); // Limpiar el buffer
                continue;
            }
            scanner.nextLine(); // Consumir la nueva línea

            List<Integer> apuestaNumeros = new ArrayList<>();
            List<String> apuestaColores = new ArrayList<>();
            int apuestaCantidadNumeros = 0;
            int apuestaCantidadColores = 0;

            switch (opcion) {
                case 1:
                    // Apuesta a múltiples números
                    System.out.print("Ingrese los números a apostar (separados por comas): ");
                    String numerosInput = scanner.nextLine();
                    apuestaNumeros = parseNumeros(numerosInput);
                    if (apuestaNumeros.isEmpty()) {
                        System.out.println("No se ingresaron números válidos.");
                        break;
                    }

                    // Solicitar cantidad de fichas para la apuesta a números
                    System.out.print("Ingrese la cantidad de fichas a apostar en números: ");
                    apuestaCantidadNumeros = obtenerCantidadApuesta(scanner, saldo);
                    if (apuestaCantidadNumeros == -1) break; // Error en la apuesta
                    saldo -= apuestaCantidadNumeros;
                    break;

                case 2:
                    // Apuesta a múltiples colores
                    System.out.print("Ingrese los colores a apostar (Rojo/Negro, separados por comas): ");
                    String coloresInput = scanner.nextLine();
                    apuestaColores = parseColores(coloresInput);
                    if (apuestaColores.isEmpty()) {
                        System.out.println("No se ingresaron colores válidos.");
                        break;
                    }

                    // Solicitar cantidad de fichas para la apuesta a colores
                    System.out.print("Ingrese la cantidad de fichas a apostar en colores: ");
                    apuestaCantidadColores = obtenerCantidadApuesta(scanner, saldo);
                    if (apuestaCantidadColores == -1) break; // Error en la apuesta
                    saldo -= apuestaCantidadColores;
                    break;

                case 3:
                    // Apuesta a números y colores
                    System.out.print("Ingrese los números a apostar (separados por comas): ");
                    String numeros = scanner.nextLine();
                    apuestaNumeros = parseNumeros(numeros);
                    if (apuestaNumeros.isEmpty()) {
                        System.out.println("No se ingresaron números válidos.");
                        break;
                    }

                    System.out.print("Ingrese los colores a apostar (Rojo/Negro, separados por comas): ");
                    String colores = scanner.nextLine();
                    apuestaColores = parseColores(colores);
                    if (apuestaColores.isEmpty()) {
                        System.out.println("No se ingresaron colores válidos.");
                        break;
                    }

                    // Solicitar cantidad de fichas para la apuesta a números
                    System.out.print("Ingrese la cantidad de fichas a apostar en números: ");
                    apuestaCantidadNumeros = obtenerCantidadApuesta(scanner, saldo);
                    if (apuestaCantidadNumeros == -1) break; // Error en la apuesta
                    saldo -= apuestaCantidadNumeros;

                    // Solicitar cantidad de fichas para la apuesta a colores
                    System.out.print("Ingrese la cantidad de fichas a apostar en colores: ");
                    apuestaCantidadColores = obtenerCantidadApuesta(scanner, saldo);
                    if (apuestaCantidadColores == -1) break; // Error en la apuesta
                    saldo -= apuestaCantidadColores;
                    break;

                case 4:
                    // Salir del programa
                    continuar = false;
                    System.out.println("Gracias por jugar. ¡Hasta luego!");
                    continue;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }

            if (!apuestaNumeros.isEmpty() || !apuestaColores.isEmpty()) {
                // Generar número ganador
                int numeroGanador = random.nextInt(37);  // Generar número entre 0 y 36
                String colorGanador = coloresRuleta[numeroGanador];

                System.out.println("\nLa ruleta gira...");
                System.out.println("Número ganador: " + numerosRuleta[numeroGanador] + 
                                   " (" + colorGanador + ")");

                // Inicializar ganancias
                int ganancias = 0;

                // Verificar apuesta a números
                if (!apuestaNumeros.isEmpty()) {
                    if (apuestaNumeros.contains(numeroGanador)) {
                        int gananciaNumeros = apuestaCantidadNumeros * 35;
                        ganancias += gananciaNumeros;
                        System.out.println("¡Has ganado apostando a los números! Ganaste " + gananciaNumeros + " fichas.");
                    } else {
                        System.out.println("No has ganado en la apuesta a números.");
                    }
                }

                // Verificar apuesta a colores
                if (!apuestaColores.isEmpty()) {
                    if (apuestaColores.contains(colorGanador)) {
                        int gananciaColores = apuestaCantidadColores * 1;
                        ganancias += gananciaColores;
                        System.out.println("¡Has ganado apostando a los colores! Ganaste " + gananciaColores + " fichas.");
                    } else {
                        System.out.println("No has ganado en la apuesta a colores.");
                    }
                }

                // Actualizar saldo con las ganancias
                if (ganancias > 0) {
                    saldo += ganancias;
                }

                // Mostrar el saldo actualizado
                System.out.println("Saldo actualizado: " + saldo + " fichas.");
            }

            if (saldo <= 0) {
                System.out.println("Te has quedado sin fichas. ¡Gracias por jugar!");
                break;
            }

            System.out.print("\n¿Deseas realizar otra apuesta? (s/n): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {
                continuar = false;
                System.out.println("Gracias por jugar. ¡Hasta luego!");
            }
        }

        scanner.close();
    }

    /**
     * Método para parsear una cadena de números separados por comas y convertirlos en una lista de enteros válidos.
     */
    private static List<Integer> parseNumeros(String input) {
        List<Integer> numeros = new ArrayList<>();
        String[] partes = input.split(",");
        for (String parte : partes) {
            try {
                int numero = Integer.parseInt(parte.trim());
                if (numero >= 0 && numero <= 36) {
                    if (!numeros.contains(numero)) { // Evitar duplicados
                        numeros.add(numero);
                    }
                } else {
                    System.out.println("Número fuera de rango (0-36): " + numero);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida para número: " + parte.trim());
            }
        }
        return numeros;
    }

    /**
     * Método para parsear una cadena de colores separados por comas y convertirlos en una lista de colores válidos.
     */
    private static List<String> parseColores(String input) {
        List<String> colores = new ArrayList<>();
        String[] partes = input.split(",");
        for (String parte : partes) {
            String color = parte.trim().toLowerCase();
            if (color.equals("rojo") || color.equals("negro")) {
                // Convertir la primera letra a mayúscula para consistencia
                colores.add(color.substring(0, 1).toUpperCase() + color.substring(1));
            } else {
                System.out.println("Color no válido: " + parte.trim());
            }
        }
        return colores;
    }

    /**
     * Método para obtener la cantidad de fichas a apostar, asegurando que el usuario no apueste más de lo que tiene.
     * Devuelve la cantidad apostada o -1 si hay un error.
     */
    private static int obtenerCantidadApuesta(Scanner scanner, int saldo) {
        int cantidad = 0;
        try {
            cantidad = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea
            if (cantidad <= 0) {
                System.out.println("La cantidad de fichas debe ser mayor que 0.");
                return -1;
            }
            if (cantidad > saldo) {
                System.out.println("No tienes suficientes fichas para apostar esa cantidad.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Entrada no válida para la cantidad de fichas.");
            scanner.nextLine(); // Limpiar el buffer
            return -1;
        }
        return cantidad;
    }
}