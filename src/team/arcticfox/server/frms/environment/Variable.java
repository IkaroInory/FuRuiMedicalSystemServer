package team.arcticfox.server.frms.environment;

import team.arcticfox.server.frms.config.BasicConfig;

public class Variable {
    private static BasicConfig config;

    public static String server_ip;
    public static int server_port;

    private static void loadConfig() {
        config = BasicConfig.parse(Function.readFile(Constant.CONFIG_CONFIG_JSON));
    }

    public static void initialize() {
        loadConfig();

        server_ip = config.server.ip;
        server_port = config.server.port;
    }
}
