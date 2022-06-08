package team.arcticfox.frms.server.environment;

import java.io.FileInputStream;
import java.io.IOException;

public class Function {
    public static void printInfo(String info) {
        System.out.println("[Info]\t" + info);
    }

    public static void printError(String error) {
        System.err.println("[Error]\t" + error);
    }

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
