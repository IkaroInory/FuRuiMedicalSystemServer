package team.arcticfox.server.frms.core;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    ServerSocket server;

    Server() {
        try {
            server = new ServerSocket(25562);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
