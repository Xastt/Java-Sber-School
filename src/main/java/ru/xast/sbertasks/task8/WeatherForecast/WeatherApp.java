package ru.xast.sbertasks.task8.WeatherForecast;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Scanner;

public class WeatherApp {
    private static final String API_KEY = "aba361e23521468babd83914241112";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/current.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your location: ");
        String location = scanner.nextLine();
        String url = String.format("%s?key=%s&q=%s&aqi=no", BASE_URL, API_KEY, location);
        try{
            String jsonResponse = getWeatherData(url);
            WeatherResponse weatherResponse = parseWeatherData(jsonResponse);
            double temperature = weatherResponse.getCurrent().getTempC();
            System.out.println("Current temperature: " + temperature);
            String condition = weatherResponse.getCurrent().getCondition().getText();
            System.out.println("Condition: " + condition);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }finally {
            scanner.close();
        }
    }

    private static String getWeatherData(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }

    private static WeatherResponse parseWeatherData(String json) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, WeatherResponse.class);
    }
}
