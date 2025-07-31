package com.pionw.eve_store.http_eve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


/**
 * Возвращает прочитанную строку из потока по URL
 */
public class ReadTextFromURL {
    public static String readString( String urlString) {
        String line = "";
        try {
            URL url = new URL(urlString);
            InputStream inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            line = reader.readLine();
            reader.close();
            inputStream.close();

        } catch (IOException e) {
            System.err.println("Ошибка при чтении данных из URL: " + e.getMessage());
        }
        return line;
    }
}
