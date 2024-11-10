package proyecto_ruleta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;

public class Proyecto_Ruleta {
    // Clase Ruleta, que representa la ruleta del casino
    public static class Ruleta {
        private static final String[] numeros = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
            "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
            "28", "29", "30", "31", "32", "33", "34", "35", "36"
        };
        private static final String[] colores = {
            "Verde", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo",
            "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Rojo",
            "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro",
            "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro"
        };
        private final Random random = new Random();

        public String girarNumero() {
            return numeros[random.nextInt(numeros.length)];
        }

        public String obtenerColor(String numero) {
            int index = Integer.parseInt(numero);
            return colores[index];
        }
    }

    public static class Apuesta {
        private Map<Integer, Integer> numeros;
        private int cantidadColores;
        private String colorApostado;

        public Apuesta() {
            this.numeros = new HashMap<>();
            this.cantidadColores = 0;
            this.colorApostado = "";
        }

        public void agregarNumero(int numero, int cantidad) {
            if (numero >= 0 && numero <= 36 && cantidad > 0) {
                numeros.put(numero, cantidad);
            }
            
        }

        public void setColorApostado(String color, int cantidad) {
            if ((color.equalsIgnoreCase("Rojo") || color.equalsIgnoreCase("Negro")) && cantidad > 0) {
                this.colorApostado = color;
                this.cantidadColores = cantidad;
            }
        }

        public Map<Integer, Integer> getNumeros() {
            return numeros;
        }

        public int getCantidadColores() {
            return cantidadColores;
        }

        public String getColorApostado() {
            return colorApostado;
        }
    }

    public static class Jugador {
        private int saldo;

        public Jugador(int saldoInicial) {
            this.saldo = saldoInicial;
        }

        public int getSaldo() {
            return saldo;
        }

        public void modificarSaldo(int cantidad) {
            this.saldo += cantidad;
        }

        public boolean puedeApostar(int cantidad) {
            return cantidad > 0 && cantidad <= saldo;
        }
    }

    public static class JuegoRuleta {
        private final Ruleta ruleta;
        private final Jugador jugador;

        public JuegoRuleta(int saldoInicial) {
            this.ruleta = new Ruleta();
            this.jugador = new Jugador(saldoInicial);
        }

        public void iniciar() {
            JOptionPane.showMessageDialog(null, "Bienvenido a Juegos Enigma - La Ruleta del Casino.");
            JOptionPane.showMessageDialog(null, "Tu saldo inicial es de " + jugador.getSaldo() + " fichas.");
            boolean continuar = true;

            while (continuar && jugador.getSaldo() > 0) {
                mostrarMenu();
                int opcion = obtenerOpcion();

                if (opcion == 4) {
                    continuar = false;
                    JOptionPane.showMessageDialog(null, "Gracias por jugar. ¡Hasta luego!");
                    continue;
                }

                Apuesta apuesta = new Apuesta();
                realizarApuesta(opcion, apuesta);

                if (!apuesta.getNumeros().isEmpty() || !apuesta.getColorApostado().isEmpty()) {
                    jugarRonda(apuesta);
                }

                if (jugador.getSaldo() <= 0) {
                    JOptionPane.showMessageDialog(null, "Te has quedado sin fichas. ¡Gracias por jugar!");
                    break;
                }

                String respuesta = JOptionPane.showInputDialog("¿Deseas realizar otra apuesta? (si/no): ");
                if (respuesta == null || !respuesta.equalsIgnoreCase("si")) {
                    continuar = false;
                    JOptionPane.showMessageDialog(null, "Gracias por jugar. ¡Hasta luego!");
                }
            }
        }

        private void mostrarMenu() {
            JOptionPane.showMessageDialog(null, "Saldo actual: " + jugador.getSaldo() + " fichas.\n"
                + "Seleccione su tipo de apuesta:\n"
                + "1. Apostar a uno o más números (0-36)\n"
                + "2. Apostar a un color (Rojo/Negro)\n"
                + "3. Apostar a números y colores\n"
                + "4. Salir");
        }

        private int obtenerOpcion() {
            String opcionStr = JOptionPane.showInputDialog("Ingrese la opción deseada: ");
            try {
                return Integer.parseInt(opcionStr);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Intente de nuevo.");
                return -1;
            }
        }

        private void realizarApuesta(int opcion, Apuesta apuesta) {
            if (opcion == 1 || opcion == 3) {
                String numerosInput = JOptionPane.showInputDialog("Ingrese los números a apostar entre 0-36 (separados por comas): ");
                
                // Convertir los números ingresados a una lista de enteros después de validar
                String[] partes = numerosInput.split(",");
                List<Integer> numerosApostados = new ArrayList<>();
                
                for (String parte : partes) {
                    try {
                        int numero = Integer.parseInt(parte.trim());
                        if (numero < 0 || numero > 36) {
                            JOptionPane.showMessageDialog(null, "Número fuera de rango: " + numero + ". Todos los números deben estar entre 0 y 36.");
                            return; // Salir si cualquier número es inválido
                        }
                        numerosApostados.add(numero);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Entrada no válida: " + parte + ". Todos los valores deben ser números enteros.");
                        return; // Salir si cualquier entrada es inválida
                    }
                }
                
                // Pedir la cantidad de fichas para cada número válido
                for (int numero : numerosApostados) {
                    try {
                        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de fichas para el número " + numero));
                        if (jugador.puedeApostar(cantidad)) {
                            apuesta.agregarNumero(numero, cantidad);
                            jugador.modificarSaldo(-cantidad);
                        } else {
                            JOptionPane.showMessageDialog(null, "Saldo insuficiente para apostar esa cantidad.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Cantidad no válida para el número " + numero);
                    }
                }
            }
        
            if (opcion == 2 || opcion == 3) {
                String colorApostado;
                while (true) {
                    colorApostado = JOptionPane.showInputDialog("Ingrese el color a apostar (Rojo/Negro): ");
                    if (colorApostado.equalsIgnoreCase("Rojo") || colorApostado.equalsIgnoreCase("Negro")) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Color no válido. Por favor ingrese 'Rojo' o 'Negro'.");
                    }
                }
        
                int cantidad;
                try {
                    cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de fichas para el color " + colorApostado));
                    if (jugador.puedeApostar(cantidad)) {
                        apuesta.setColorApostado(colorApostado, cantidad);
                        jugador.modificarSaldo(-cantidad);
                    } else {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente para apostar esa cantidad.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Cantidad no válida. No se realizará la apuesta en color.");
                }
            }
        }

        private void jugarRonda(Apuesta apuesta) {
            String numeroGanador = ruleta.girarNumero();
            String colorGanador = ruleta.obtenerColor(numeroGanador);

            JOptionPane.showMessageDialog(null, "La ruleta gira...\n"
                + "Número ganador: " + numeroGanador + " (" + colorGanador + ")");
            int ganancias = 0;
            int numeroGanadorInt = Integer.parseInt(numeroGanador);

            if (apuesta.getNumeros().containsKey(numeroGanadorInt)) {
                ganancias += apuesta.getNumeros().get(numeroGanadorInt) * 35;
                JOptionPane.showMessageDialog(null, "¡Has ganado apostando al número " + numeroGanadorInt + "!");
            }

            if (apuesta.getColorApostado().equalsIgnoreCase(colorGanador)) {
                ganancias += apuesta.getCantidadColores() * 2;
                JOptionPane.showMessageDialog(null, "¡Has ganado apostando al color " + colorGanador + "!");
            }

            jugador.modificarSaldo(ganancias);
            JOptionPane.showMessageDialog(null, "Ganancias de la ronda: " + ganancias + " fichas.\n"
                + "Saldo actualizado: " + jugador.getSaldo() + " fichas.");
        }
    }
    public static void main(String[] args) {
        String saldoInicialStr = JOptionPane.showInputDialog("Ingrese su saldo inicial: ");
        int saldoInicial = Integer.parseInt(saldoInicialStr);
        JuegoRuleta juego = new JuegoRuleta(saldoInicial);
        juego.iniciar();
    }
}
