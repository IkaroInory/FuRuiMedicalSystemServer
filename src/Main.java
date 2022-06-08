import team.arcticfox.frms.server.core.thread.SignInServer;
import team.arcticfox.frms.server.environment.Variable;


public class Main {
    private static void initialize() {
        Variable.initialize();
    }

    public static void main(String[] args) {
        /*
        initialize();
        System.out.println(Constant.Message.getWelcomeWords());
        Command command = new Command();
        command.start();

         */

        test();
    }

    private static void test() {
        initialize();
        SignInServer server = new SignInServer();
        server.start();
    }
}
