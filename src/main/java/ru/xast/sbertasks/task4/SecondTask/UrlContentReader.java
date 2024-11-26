package ru.xast.sbertasks.task4.SecondTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class UrlContentReader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url;

        while (true) {
            System.out.println("Введите URL ресурса (или 'exit' для выхода):");
            url = scanner.nextLine();

            if (url.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                readContent(url);
                break;
            } catch (MalformedURLException e) {
                System.out.println("Неверный формат URL. Пожалуйста, попробуйте еще раз.");
            } catch (IOException e) {
                System.out.println("Нет доступа к указанному ресурсу. Пожалуйста, попробуйте еще раз.");
            }
        }

        scanner.close();
    }

    public static void readContent(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // 200
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine).append("\n");
            }

            in.close();

            System.out.println("Содержимое сайта:");
            System.out.println(content.toString());
        } else {
            throw new IOException("Ошибка: " + responseCode);
        }
    }
}
