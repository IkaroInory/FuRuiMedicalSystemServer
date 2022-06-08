package team.arcticfox.frms.server.environment;

import team.arcticfox.frms.server.config.Config;

public class Variable {
    private static Config config;

    public static String server_name;
    public static String server_uuid;
    public static String server_ip;
    public static int server_port;

    public static String server_signIn_ip;
    public static int server_signIn_port;
    public static String server_register_ip;
    public static int server_register_port;

    private static void loadConfig() {
        config = Config.parse(Function.readFile(Constant.CONFIG_CONFIG_JSON));
    }

    public static void initialize() {
        loadConfig();

        server_name = config.server.name;
        server_ip = config.server.ip;
        server_uuid = config.server.uuid;
        server_port = config.server.port;

        server_signIn_ip = config.server.list.signInServer.ip;
        server_signIn_port = config.server.list.signInServer.port;
        server_register_ip = config.server.list.registerServer.ip;
        server_register_port = config.server.list.registerServer.port;
    }
}
