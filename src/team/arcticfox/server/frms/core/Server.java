package team.arcticfox.server.frms.core;

import team.arcticfox.server.frms.environment.Variable;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread {
    ServerSocket server;

    Server() {
        try {
            server = new ServerSocket(Variable.server_port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
