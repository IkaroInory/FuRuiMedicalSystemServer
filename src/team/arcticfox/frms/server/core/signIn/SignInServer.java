package team.arcticfox.frms.server.core.signIn;

import team.arcticfox.frms.server.environment.Variable;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SignInServer extends Thread {
    private ServerSocket signInServer;
    private Socket socket;

    public SignInServer() {
        super();
        try {
            signInServer = new ServerSocket(Variable.server_signIn_port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void end() {
        try {
            signInServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Listen for account information that be sent from client.
     *
     * @param
     * @return void
     * @author Guanyu Hu
     * @date 2022/6/8 15:47
     */
    private void accountInfoMonitor() {
        String message;
        while (true) {
            try {
                socket = signInServer.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());

                message = in.readUTF();
                System.out.println(message);

                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void signIn(String username, String password) {
        System.out.println(username + " " + password);
    }


    @Override
    public void run() {
        accountInfoMonitor();
        end();
    }
}
