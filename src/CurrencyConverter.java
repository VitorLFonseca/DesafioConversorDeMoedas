import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CurrencyConverter {

    private static final String API_KEY = ""; //sua chave API aqui
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    private static final Set<String> SUPPORTED_CURRENCIES = new HashSet<>(Arrays.asList(
            "ARS", "BOB", "BRL", "CLP", "COP", "USD"
    ));

    public double convert(String fromCurrency, String toCurrency, double amount) {
        if (!SUPPORTED_CURRENCIES.contains(fromCurrency) || !SUPPORTED_CURRENCIES.contains(toCurrency)) {
            System.out.println("Moeda não suportada. Moedas disponíveis: " + SUPPORTED_CURRENCIES);
            return -1;
        }

        try {
            String urlStr = API_URL + fromCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            int status = request.getResponseCode();
            if (status != 200) {
                System.out.println("Erro na requisição: código " + status);
                return -1;
            }

            try (InputStream responseStream = request.getInputStream();
                 InputStreamReader reader = new InputStreamReader(responseStream)) {

                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                JsonObject rates = json.getAsJsonObject("conversion_rates");

                if (!rates.has(toCurrency)) {
                    System.out.println("Moeda de destino não encontrada na resposta da API.");
                    return -1;
                }

                double rate = rates.get(toCurrency).getAsDouble();
                return amount * rate;
            }

        } catch (Exception e) {
            System.out.println("Erro ao converter moeda: " + e.getMessage());
            return -1;
        }
    }
}