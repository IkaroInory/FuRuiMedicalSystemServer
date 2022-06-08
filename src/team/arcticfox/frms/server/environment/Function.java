package team.arcticfox.frms.server.environment;

import team.arcticfox.frms.server.security.Base64;

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

    public static String getSQL_ByName(String username) {
        final String PATH_TABLE_ACCOUNT = "`" + Variable.config.database.name + "`.`" + Constant.TABLE_ACCOUNT_INFO + "`";
        return "SELECT * FROM " + PATH_TABLE_ACCOUNT + " WHERE `username` = '" + Base64.encode(username) + "'";
    }
    public static String getSQL_UpdateLastLoginTime(int id){
        final String PATH_TABLE_ACCOUNT = "`" + Variable.config.database.name + "`.`" + Constant.TABLE_ACCOUNT_INFO + "`";
        return "UPDATE " + PATH_TABLE_ACCOUNT + " SET `Last Login Time` = NOW() WHERE `Id` = " + id;
    }
}
