import team.arcticfox.server.frms.core.Command;
import team.arcticfox.server.frms.environment.Constant;
import team.arcticfox.server.frms.environment.Variable;

public class Main {
    private static void initialize() {
        Variable.initialize();
    }

    public static void main(String[] args) {
        initialize();
        System.out.println(Constant.Message.getWelcomeWords());
        Command command = new Command();
        command.start();

        // test();
    }

    private static void test() {

    }
}
