package team.arcticfox.frms.server.core.thread;

import com.alibaba.fastjson.JSON;
import team.arcticfox.frms.server.database.Database;
import team.arcticfox.frms.server.dataset.AccountInfo;
import team.arcticfox.frms.server.dataset.DateTime;
import team.arcticfox.frms.server.dataset.SignInInfo;
import team.arcticfox.frms.server.environment.Function;
import team.arcticfox.frms.server.environment.Variable;
import team.arcticfox.frms.server.log.Log;
import team.arcticfox.frms.server.security.MD5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInServer extends Thread {
    private ServerSocket server;
    private Socket socket;

    public SignInServer() {
        super();
        try {
            server = new ServerSocket(Variable.server_signIn_port);
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
     * Listen for account information that be sent from client.
     *
     * @param
     * @return void
     * @author Guanyu Hu
     * @date 2022/6/8 15:47
     */
    private void monitor() {
        String message;
        while (true) {
            try {
                socket = server.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                message = in.readUTF();

                SignInInfo info = JSON.parseObject(message, SignInInfo.class);
                DateTime dateTime = DateTime.now();
                String sessionUUID = Function.getTimeStamp(dateTime);
                String statueCode = "NULL";
                Function.printSession("Login session: " + info.username + " (" + dateTime + ")");
                Function.printSession("Session UUID: " + sessionUUID);

                statueCode = signIn(info.username, info.password);

                out.writeUTF(statueCode);

                AccountInfo accountInfo = getAccountInfo(info.username);
                if (accountInfo == null)
                    out.writeUTF("");
                else
                    out.writeUTF(getAccountInfo(info.username).toJsonString());
                out.flush();

                out.close();
                in.close();
                socket.close();

                Log.createSignInServerLog(info.username, sessionUUID, dateTime, statueCode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private AccountInfo getAccountInfo(String username) {
        Database db = new Database(Variable.config.database.name);
        db.open();
        ResultSet rs = db.sqlQuery(Function.getSQL_ByName(username));
        AccountInfo accountInfo = null;
        try {
            if (rs.first())
                accountInfo = AccountInfo.parse(rs);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return accountInfo;
    }

    private String signIn(String username, String password) {
        AccountInfo accountInfo = getAccountInfo(username);
        if (accountInfo == null) return "AC1001";
        if (!MD5.encode(password).equals(accountInfo.password)) return "AC1002";

        Database db = new Database(Variable.config.database.name);
        db.open();
        db.sqlUpdate(Function.getSQL_UpdateLastLoginTime(accountInfo.id));
        db.close();

        return "NULL";
    }


    @Override
    public void run() {
        monitor();
        end();
    }
}
