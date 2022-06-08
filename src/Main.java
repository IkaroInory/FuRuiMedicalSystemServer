import team.arcticfox.frms.server.core.Command;
import team.arcticfox.frms.server.core.thread.SignInServer;
import team.arcticfox.frms.server.environment.Constant;
import team.arcticfox.frms.server.environment.Variable;

import java.io.File;


public class Main {
    private static void initialize() {
        Variable.initialize();
    }


    public static void main(String[] args) {
        initialize();
        System.out.println(Constant.Message.getWelcomeWords());
        Command command = new Command();
        command.start();
        SignInServer server = new SignInServer();
        server.start();
    }

    private static void test() {
        File file = new File("logs/sign in/");
        // new File("/logs").mkdir();
        System.out.println(file.getAbsoluteFile());
    }
}
