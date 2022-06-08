package team.arcticfox.frms.server.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import team.arcticfox.frms.server.dataset.DateTime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Log {
    private static final String dir = "logs/";

    public static void createSignInServerLog(String username, String sessionUUID, DateTime time, String statueCode) {
        String fileName = dir + "sign in/" + sessionUUID + ".json";

        JSONObject log = new JSONObject(true);
        log.put("username", username);
        log.put("session-uuid", sessionUUID);
        log.put("time", time.toString());
        log.put("statueCode", statueCode);

        try {
            File file = new File(fileName);
            if (!file.exists())
                file.createNewFile();

            FileOutputStream out = new FileOutputStream(fileName);
            out.write(JSON.toJSONString(log, true).getBytes(StandardCharsets.UTF_8));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createRegisterLog(String username, String sessionUUID, DateTime time, String statueCode) {
        String fileName = dir + "register/" + sessionUUID + ".json";

        JSONObject log = new JSONObject(true);
        log.put("username", username);
        log.put("session-uuid", sessionUUID);
        log.put("time", time.toString());
        log.put("statueCode", statueCode);

        try {
            File file = new File(fileName);
            if (!file.exists())
                file.createNewFile();

            FileOutputStream out = new FileOutputStream(fileName);
            out.write(JSON.toJSONString(log, true).getBytes(StandardCharsets.UTF_8));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
