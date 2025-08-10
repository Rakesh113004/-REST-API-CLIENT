package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherAPIClient {
    public static void main(String[] args) {
        try {
            String city = "London"; // You can take input from user
            String apiKey = "1dda63f58ec031d79ca4a8511b04c8f1"; // Replace with your key
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q="
                    + city + "&appid=" + apiKey + "&units=metric";

            // Create URL object
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response
            JSONObject jsonObj = new JSONObject(response.toString());

            String weatherDescription = jsonObj.getJSONArray("weather")
                    .getJSONObject(0)
                    .getString("description");

            double temperature = jsonObj.getJSONObject("main").getDouble("temp");
            int humidity = jsonObj.getJSONObject("main").getInt("humidity");

            // Display data
            System.out.println("Weather in " + city + ":");
            System.out.println("Description: " + weatherDescription);
            System.out.println("Temperature: " + temperature + " °C");
            System.out.println("Humidity: " + humidity + "%");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}