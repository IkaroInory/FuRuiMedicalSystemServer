import team.arcticfox.frms.server.core.Command;
import team.arcticfox.frms.server.core.thread.RegisterServer;
import team.arcticfox.frms.server.core.thread.SignInServer;
import team.arcticfox.frms.server.environment.Constant;
import team.arcticfox.frms.server.environment.Variable;


public class Main {
    private static Command command;
    private static SignInServer signInServer;
    private static RegisterServer registerServer;

    private static void initialize() {
        Variable.initialize();

        command = new Command();
        signInServer = new SignInServer();
        registerServer = new RegisterServer();

        command.start();
        signInServer.start();
        registerServer.start();
    }


    public static void main(String[] args) {
        initialize();

        System.out.println(Constant.Message.getWelcomeWords());
    }

    private static void test() {
    }
}
