package team.arcticfox.frms.server.environment;

public class Constant {
    private static final String CONFIG = "./config/";
    private static final String FILE_CONFIG_JSON = "config.json";

    public static final String CONFIG_CONFIG_JSON = CONFIG + FILE_CONFIG_JSON;

    public static final String TABLE_ACCOUNT_INFO = "Account Info";
    public static final String TABLE_MEDICINE_LIST = "Medicine List";

    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_PERMISSION = "Permission";
    public static final String COLUMN_REGISTRATION_TIME = "Registration Time";
    public static final String COLUMN_DESTRUCTION_TIME = "Destruction Time";
    public static final String COLUMNLABEL_LASTLOGIN_TIME = "Last Login Time";
    public static final String COLUMNLABEL_MEDICINENAME = "Medicine Name";
    public static final String COLUMNLABEL_APPROVALNO = "Approval No";
    public static final String COLUMNLABEL_MANUFACTURER = "Manufacturer";
    public static final String COLUMNLABEL_GRADE = "Grade";
    public static final String COLUMNLABEL_TYPE = "Type";
    public static final String COLUMNLABEL_SPECIFICATION = "Specification";
    public static final String COLUMNLABEL_FORSALE = "For Sale";
    public static final String COLUMNLABEL_PRICE = "Price";
    public static final String COLUMNLABEL_AMOUNT = "Amount";
    public static final String COLUMNLABEL_PUTAWAYTIME = "Putaway Time";
    public static final String COLUMNLABEL_IMAGENAME = "Image Name";

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
