package team.arcticfox.server.frms.core;

import team.arcticfox.server.frms.environment.Function;
import team.arcticfox.server.frms.environment.Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Command extends Thread {

    private static final Map<String, ICommandFunction> map = new HashMap<>() {
        {
            put("null", args -> {
                Function.printError("Wrong command!");
                return false;
            });
            put("exit", args -> true);
            put("help", args -> {
                System.out.println("------ Help List ------");
                // helpList()
                return false;
            });
            put("info", args -> {
                System.out.println(Variable.server_name + " - " + Variable.server_uuid);
                System.out.println("\tip:\t\t" + Variable.server_ip);
                System.out.println("\tport:\t" + Variable.server_port);
                return false;
            });

        }
    };

    private boolean sendCommand(String line) {
        String[] list = line.split(" ");
        String cmd = list[0];

        if (map.containsKey(cmd))
            return map.get(cmd).fun(list);
        else
            return map.get("null").fun(list);
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
