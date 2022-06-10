package team.arcticfox.frms.server.environment;

import team.arcticfox.frms.server.dataset.DateTime;
import team.arcticfox.frms.server.security.Base64;
import team.arcticfox.frms.server.security.MD5;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Function {
    public static void printInfo(String info) {
        System.out.println("[Info]\t\t" + info);
    }

    public static void printError(String error) {
        System.err.println("[Error]\t\t" + error);
    }

    public static void printSession(String info) {
        System.out.println("[Session]\t[" + DateTime.now() + "]\t" + info);
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

    public static String getSQL_UpdateLastLoginTime(int id) {
        final String PATH_TABLE_ACCOUNT = "`" + Variable.config.database.name + "`.`" + Constant.TABLE_ACCOUNT_INFO + "`";
        return "UPDATE " + PATH_TABLE_ACCOUNT + " SET `Last Login Time` = NOW() WHERE `Id` = " + id;
    }

    public static String getSQL_InsertUser(String username, String email, String password) {
        final String PATH_TABLE_ACCOUNT = "`" + Variable.config.database.name + "`.`" + Constant.TABLE_ACCOUNT_INFO + "`";
        return "INSERT INTO " + PATH_TABLE_ACCOUNT + " (" +
                "`" + Constant.COLUMN_USERNAME + "`, " +
                "`" + Constant.COLUMN_EMAIL + "`, " +
                "`" + Constant.COLUMN_PASSWORD + "`, " +
                "`" + Constant.COLUMN_PERMISSION + "`" +
                ") VALUES (" +
                "'" + Base64.encode(username) + "', " +
                "'" + Base64.encode(email) + "', " +
                "'" + Base64.encode(MD5.encode(password)) + "', " +
                "'" + "User" + "'" +
                ")";
    }

    public static String getTimeStamp(DateTime dateTime) {
        String no = "";
        int[] list = {
                dateTime.month, dateTime.day,
                dateTime.hour, dateTime.minute, dateTime.second
        };
        no += dateTime.year;
        for (int i : list) {
            if (i < 10)
                no += '0';
            no += i;
        }
        Random random = new Random(dateTime.timeToLong());
        no += 1000 + Math.abs(random.nextInt()) % 9000;
        return no;
    }
}
