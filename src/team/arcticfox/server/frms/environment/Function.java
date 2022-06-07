package team.arcticfox.server.frms.environment;

import java.io.FileInputStream;
import java.io.IOException;

public class Function {
    public static String readFile(String path) {
        String s = "";
        try {
            FileInputStream fin = new FileInputStream(path);
            s = new String(fin.readAllBytes());
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
