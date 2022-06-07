package team.arcticfox.server.frms.environment;

public class Constant {
    private static final String CONFIG = "./resources/config/";
    private static final String FILE_CONFIG_JSON = "config.json";

    public static final String CONFIG_CONFIG_JSON = CONFIG + FILE_CONFIG_JSON;

    public static class Message {
        private static final String WELCOME_WORDS = "FuRui Medical System Server 2022 - [%ip%:%port%]";

        public static String getWelcomeWords() {
            String s = WELCOME_WORDS;
            s = s.replaceAll("%ip%", Variable.server_ip);
            s = s.replaceAll("%port%", String.valueOf(Variable.server_port));
            return s;
        }
    }
}
