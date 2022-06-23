package me.cutenyami.lava.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
    public static String loadLines(String path) {
        try {
            InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            StringBuilder builder = new StringBuilder();
            if (resource == null)
                return builder.toString();
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
            String line;
            for (; (line = reader.readLine()) != null; builder.append(line).append(System.lineSeparator()));
            reader.close();
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
