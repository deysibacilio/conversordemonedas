import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException{

        ConversorDeMonedas conversor = new ConversorDeMonedas("c659c453decb309ff0b4fc67");
        Scanner scanner = new Scanner(System.in);

        // Se Crea una lista para almacenar el historial de conversiones
        ArrayList<String> historial = new ArrayList<>();

        Map<String, String> mapaMonedas = new HashMap<>();
        mapaMonedas.put("dolares", "USD");
        mapaMonedas.put("euros", "EUR");
// Agrega más monedas al mapa según sea necesario

        // Bucle infinito para mantener el programa en ejecución hasta que el usuario decida salir
        while (true) {
            // Imprimir las opciones para el usuario
            System.out.println("************************************");
            System.out.println("Bienvenido/a al conversor de monedas, por favor, selecciona una opción:");
            System.out.println("1. Convertir moneda");
            System.out.println("2. Ver historial de conversiones");
            System.out.println("3. Salir");
            System.out.println("************************************");

            // Lee la opción seleccionada por el usuario
            int opcion = scanner.nextInt();

            // Se Ejecuta diferentes acciones dependiendo de la opción seleccionada
            switch (opcion) {
                case 1:
                    // Se Solicita al usuario la moneda de origen, la moneda de destino y la cantidad a convertir
                    System.out.println("Por favor, introduce la moneda de origen:");
                    System.out.println("(Debes introducir el código de la moneda. Por ejemplo: 'USD' para dólares, 'EUR' para euro, 'PEN' para soles, etc.)");
                    String monedaOrigen = scanner.next();

                    System.out.println("Por favor, introduce la moneda de destino:");
                    System.out.println("(Debes introducir el código de la moneda. Por ejemplo: 'USD' para dólares, 'EUR' para euro, 'PEN' para soles, etc.)");
                    String monedaDestino = scanner.next();

                    System.out.println("Por favor, introduce la cantidad a convertir:");
                    double cantidad = scanner.nextDouble();

                    // Se convertira la cantidad de la moneda de origen a la moneda de destino
                    double resultado = conversor.convertirMoneda(monedaOrigen, monedaDestino, cantidad);
                    System.out.println("El valor de " + cantidad + " " + monedaOrigen + " corresponde al valor final de =>> " + resultado + " " + monedaDestino);

                    // Se añadira la conversión al historial con una marca de tiempo real
                    String registro = LocalDateTime.now() + ": Convertido " + cantidad + " " + monedaOrigen + " a " + monedaDestino + ". Resultado: " + resultado + " " + monedaDestino;
                    historial.add(registro);
                    break;
                case 2:
                    // historial de conversiones
                    System.out.println("Historial de conversiones realizadas:");
                    for (String r : historial) {
                        System.out.println(r);
                    }
                    break;
                case 3:
                    // Si el usuario selecciona la opción 2.
                    System.out.println("¡Gracias por usar nuestro aplicativo!");
                    System.exit(0);
                default:
                    // Si el usuario selecciona una opción no válida.
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }
}
