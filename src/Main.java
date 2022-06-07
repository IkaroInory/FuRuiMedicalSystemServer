import team.arcticfox.server.frms.config.BasicConfig;
import team.arcticfox.server.frms.environment.Constant;
import team.arcticfox.server.frms.environment.Variable;

import java.io.FileInputStream;

public class Main {
    private static void initialize() {
        Variable.initialize();
    }

    public static void main(String[] args) {
        initialize();
        System.out.println(Constant.Message.getWelcomeWords());

        // test();

        // run core code.

    }

    private static void test() throws Exception {
        FileInputStream fin = new FileInputStream(Constant.CONFIG_CONFIG_JSON);
        String text = new String(fin.readAllBytes());
        fin.close();

        System.out.println(BasicConfig.parse(text));
    }
}
