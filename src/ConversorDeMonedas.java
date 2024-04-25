// Importar las bibliotecas necesarias para la aplicación
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

// se define la clase ConversorDeMonedas
public class ConversorDeMonedas {
    private String claveApi;
    private Gson gson;

    // Constructor para la clase ConversorDeMonedas
    public ConversorDeMonedas(String claveApi) {
        this.claveApi = claveApi;
        this.gson = new Gson();
    }

    // Método para obtener los tipos de cambio
    public JsonObject obtenerTiposDeCambio(String monedaBase) {
        JsonObject tiposDeCambio = null;
        try {
            // Construir la URL para la solicitud de la API
            String url = "https://v6.exchangerate-api.com/v6/" + claveApi + "/latest/" + monedaBase;

            // Crear un nuevo cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Envia la solicitud y obtiene la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200){
                tiposDeCambio = gson.fromJson(response.body(), JsonObject.class);
            } else {
                System.out.println("La solicitud GET no funcionó");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tiposDeCambio;
    }

    // Método para convertir la moneda
    public double convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad){
        double resultado = 0.0;
        try {
            // Se Obtiene los tipos de cambio para la moneda de origen
            JsonObject tiposDeCambioOrigen = obtenerTiposDeCambio(monedaOrigen);
            // Se Obtiene el tipo de cambio para la moneda de destino
            double tipoDeCambioDestino = tiposDeCambioOrigen.getAsJsonObject("conversion_rates").get(monedaDestino).getAsDouble();
            // Calculo del resultado
            resultado = cantidad * tipoDeCambioDestino;
        } catch (Exception e) {
            System.out.println("Ocurrió un error al convertir la moneda: " + e.getMessage());
        }
        return resultado;
    }
}