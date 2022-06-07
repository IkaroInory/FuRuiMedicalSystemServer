package team.arcticfox.server.frms.core;

import team.arcticfox.server.frms.environment.Function;
import team.arcticfox.server.frms.environment.Variable;

import java.util.Scanner;

public class Command extends Thread {
    private boolean sendCommand(String line) {
        String[] list = line.split(" ");
        String cmd = list[0];
        if (cmd.equals("exit")) {
            // System.exit(0);
            return true;
        }
        if (cmd.equals("?") || cmd.equals("help")) {
            System.out.println("------ Help List ------");
            // helpList()
            return false;
        }
        if (cmd.equals("info")) {
            System.out.println(Variable.server_name + " - " + Variable.server_uuid);
            System.out.println("\tip:\t\t" + Variable.server_ip);
            System.out.println("\tport:\t" + Variable.server_port);
            return false;
        }
        Function.printError("Wrong command!");
        return false;
    }

    private void main() {
        Scanner cin = new Scanner(System.in);
        do {
            String cmd = cin.nextLine();
            if (sendCommand(cmd))
                break;
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
