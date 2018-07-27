package lv.sh;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataProvider {

    public static String readFromFile(String fileName) {

        InputStream inputStream =
                DataProvider.class.getClassLoader().getResourceAsStream(fileName);

        try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
