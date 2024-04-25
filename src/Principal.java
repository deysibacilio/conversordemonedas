// Importar las bibliotecas necesarias
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

// Definir la clase Principal
public class Principal {
    // Método principal que se ejecuta al iniciar el programa
    public static void main(String[] args) throws IOException, InterruptedException{

        // Crear un nuevo objeto de la clase ConversorDeMonedas con la clave de la API
        ConversorDeMonedas conversor = new ConversorDeMonedas("c659c453decb309ff0b4fc67");
        // Crear un nuevo objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Crear una lista para almacenar el historial de conversiones
        ArrayList<String> historial = new ArrayList<>();

        // Bucle infinito para mantener el programa en ejecución hasta que el usuario decida salir
        while (true) {
            // Imprimir las opciones para el usuario
            System.out.println("************************************");
            System.out.println("Bienvenido/a al conversor de monedas, por favor, selecciona una opción:");
            System.out.println("1. Convertir moneda");
            System.out.println("2. Ver historial de conversiones");
            System.out.println("3. Salir");
            System.out.println("************************************");

            // Leer la opción seleccionada por el usuario
            int opcion = scanner.nextInt();

            // Ejecutar diferentes acciones dependiendo de la opción seleccionada
            switch (opcion) {
                case 1:
                    // Solicitar al usuario la moneda de origen, la moneda de destino y la cantidad a convertir
                    System.out.println("Por favor, introduce la moneda de origen:");
                    String monedaOrigen = scanner.next();

                    System.out.println("Por favor, introduce la moneda de destino:");
                    String monedaDestino = scanner.next();

                    System.out.println("Por favor, introduce la cantidad a convertir:");
                    double cantidad = scanner.nextDouble();

                    // Convertir la cantidad de la moneda de origen a la moneda de destino
                    double resultado = conversor.convertirMoneda(monedaOrigen, monedaDestino, cantidad);
                    // Imprimir el resultado de la conversión
                    System.out.println("El resultado de convertir " + cantidad + " " + monedaOrigen + " a " + monedaDestino + " es: " + resultado);

                    // Añadir la conversión al historial con una marca de tiempo real
                    String registro = LocalDateTime.now() + ": Convertido " + cantidad + " " + monedaOrigen + " a " + monedaDestino + ". Resultado: " + resultado;
                    historial.add(registro);
                    break;
                case 2:
                    // Imprimir el historial de conversiones
                    System.out.println("Historial de conversiones realizadas:");
                    for (String r : historial) {
                        System.out.println(r);
                    }
                    break;
                case 3:
                    // Si el usuario selecciona la opción 2, agradecer al usuario y terminar el programa
                    System.out.println("¡Gracias por usar nuestro aplicativo!");
                    System.exit(0);
                default:
                    // Si el usuario selecciona una opción no válida, imprimir un mensaje de error
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }
}
