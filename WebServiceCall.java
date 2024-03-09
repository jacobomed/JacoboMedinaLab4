import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebServiceCall {
    public static void main(String[] args) {
        try {
            // Specify the URL of the web service
            String url = "http://localhost:8000/pizza";

            // Create a URL object
            URL obj = new URL(url);

            // Open a connection to the URL
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set the request method
            con.setRequestMethod("GET");

            // Set request headers if needed
            con.setRequestProperty("Content-Type", "application/json");

            // Get the response code
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            // Read the response from the web service
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response1 = new StringBuilder();
            StringBuilder response2 = new StringBuilder();

            boolean isFirstResponse = true; // Flag to differentiate responses

            while ((inputLine = in.readLine()) != null) {
                if (isFirstResponse) {
                    // Skip the "Pizza JSON: " prefix
                    int startIndex = inputLine.indexOf("{");
                    if (startIndex != -1) {
                        response1.append(inputLine.substring(startIndex));
                        isFirstResponse = false;
                    }
                } else {
                    response2.append(inputLine);
                }
            }
            in.close();

            // Print the responses
            System.out.println("Response 1 (JSON): " + response1.toString());
            System.out.println("Response 2 (Deserialized Pizza): " + response2.toString());

            // Deserialize the JSON response into a Pizza object
            ObjectMapper objectMapper = new ObjectMapper();
            Pizza deserializedPizza = objectMapper.readValue(response1.toString(), Pizza.class);

            // Access the properties of the deserialized Pizza object
            System.out.println("\nDeserialized Pizza:");
            System.out.println("Size: " + deserializedPizza.getSize());
            System.out.println("Id: " + deserializedPizza.getId());
            System.out.println("Toppings: " + deserializedPizza.getToppings());
            System.out.println("Price: " + deserializedPizza.getPrice());

            // Parse the JSON response as needed

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}