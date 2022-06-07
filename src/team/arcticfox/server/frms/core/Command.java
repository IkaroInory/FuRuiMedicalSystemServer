package team.arcticfox.server.frms.core;

import team.arcticfox.server.frms.environment.Function;

import java.util.Scanner;

public class Command extends Thread {
    private void main() {
        Scanner cin = new Scanner(System.in);
        do {
            String cmd = cin.next();
            if (cmd.equals("exit")) {
                // System.exit(0);
                break;
            }
            if (cmd.equals("?") || cmd.equals("help")) {
                System.out.println("------ Help List ------");
                // helpList()
                continue;
            }
            Function.printError("Wrong command!");
        } while (true);
        Function.printInfo("Command line closed.");
    }

    @Override
    public void run() {
        main();
    }

    @Override
    public synchronized void start() {
        Function.printInfo("Command line started.");
        super.start();
    }
}
