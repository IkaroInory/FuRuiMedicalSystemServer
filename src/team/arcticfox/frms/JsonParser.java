package team.arcticfox.frms;

import java.io.FileInputStream;
import java.io.IOException;

@Deprecated
public class JsonParser {
    private final String filePath;
    private String jsonText;

    JsonParser(String filePath) {
        this.filePath = filePath;
        flush();
    }

    public String getJsonText() {
        return jsonText;
    }

    void flush() {
        try {
            FileInputStream fin = new FileInputStream(filePath);
            jsonText = new String(fin.readAllBytes());
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
