import team.arcticfox.server.frms.config.Config;
import team.arcticfox.server.frms.config.ServerConfig;
import team.arcticfox.server.frms.environment.Constant;

import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("FuRui Medical System Server 2022");

        test();

        // run core code.

    }

    private static void test() throws Exception {
        System.out.println(new Config());
        FileInputStream fin = new FileInputStream(Constant.CONFIG_CONFIG_JSON);
        String text = new String(fin.readAllBytes());
        fin.close();
        System.out.println(ServerConfig.parse(text));
    }
}
