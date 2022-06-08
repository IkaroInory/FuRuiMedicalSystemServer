import team.arcticfox.frms.server.config.Config;
import team.arcticfox.frms.server.core.signIn.SignInServer;
import team.arcticfox.frms.server.environment.Constant;
import team.arcticfox.frms.server.environment.Function;
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
        Config config = Config.parse(Function.readFile(Constant.CONFIG_CONFIG_JSON));
        System.out.println(config);
        System.out.println(config.server.list.signInServer);
        System.out.println(Variable.server_signIn_port);

        SignInServer server = new SignInServer();
        server.start();
    }
}
