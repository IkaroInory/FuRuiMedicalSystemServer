package team.arcticfox.frms.server.core.thread;

import team.arcticfox.frms.server.dataset.DateTime;
import team.arcticfox.frms.server.environment.Function;
import team.arcticfox.frms.server.environment.Variable;
import team.arcticfox.frms.server.log.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RegisterServer extends Thread {
    private ServerSocket server;
    private Socket socket;

    public RegisterServer() {
        super();
        try {
            server = new ServerSocket(Variable.config.server.list.registerServer.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void end() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO
     *
     * @param
     * @return void
     * @author Guanyu Hu
     * @date 2022/6/8 23:54
     */
    private void monitor() {
        String msg = null;
        String username = null;
        String sessionUUID;
        DateTime dateTime;
        String statueCode = null;

        while (true) {
            try {
                socket = server.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                msg = in.readUTF();
                dateTime = DateTime.now();
                sessionUUID = Function.getTimeStamp(dateTime);

                register(username);

                socket.close();

                Log.createRegisterLog(username, sessionUUID, dateTime, statueCode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String register(String username) {

    }

    @Override
    public void run() {
        super.run();
    }
}
