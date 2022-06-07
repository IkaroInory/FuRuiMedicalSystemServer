import team.arcticfox.server.frms.config.Config;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("FuRui Medical System Server 2022");

        test();

        // run core code.

    }

    private static void test() {
        System.out.println(new Config());
    }
}
