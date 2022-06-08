package team.arcticfox.frms.server.core;

import team.arcticfox.frms.server.environment.Variable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket server;
    Socket socket;

    public Server() {
        super();
        try {
            server = new ServerSocket(Variable.server_port);
            socket = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
