package team.arcticfox.frms.server.core.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.Feature;
import team.arcticfox.frms.exception.FuRuiException;
import team.arcticfox.frms.exception.NullException;
import team.arcticfox.frms.server.database.Database;
import team.arcticfox.frms.server.dataset.AccountInfo;
import team.arcticfox.frms.server.dataset.DateTime;
import team.arcticfox.frms.server.dataset.IJson;
import team.arcticfox.frms.server.environment.Function;
import team.arcticfox.frms.server.environment.Variable;
import team.arcticfox.frms.server.log.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


class RegisterInfo implements IJson {
    @JSONField(name = "username")
    public String username;
    @JSONField(name = "email", ordinal = 1)
    public String email;
    @JSONField(name = "password", ordinal = 2)
    public String password;

    RegisterInfo() {
        this("", "", "");
    }

    RegisterInfo(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toJsonString() {
        return JSON.toJSONString(this);
    }
}


public class RegisterServer extends Thread {
    private ServerSocket server;

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

    private void monitor() {
        while (true) {
            try {
                Socket socket = server.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                String msg = in.readUTF();
                RegisterInfo info = JSON.parseObject(msg, RegisterInfo.class);

                DateTime dateTime = DateTime.now();
                String uuid = Function.getTimeStamp(dateTime);

                Function.printSession("Register session: " + socket.getRemoteSocketAddress());
                Function.printSession("Session UUID: " + uuid);

                String exceptionCode = register(info);

                out.writeUTF(exceptionCode);
                out.flush();

                if (exceptionCode.equals(new NullException().code))
                    Function.printSession("Register successful.");
                else
                    Function.printSession("Register failed. " + FuRuiException.parse(exceptionCode).getMessage());

                out.close();
                in.close();
                socket.close();

                Log.createRegisterLog(uuid, dateTime, socket.getRemoteSocketAddress(), exceptionCode, JSON.parseObject(msg, Feature.OrderedField));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String register(RegisterInfo info) {
        AccountInfo accountInfo = AccountInfo.getAccountInfo(info.username);
        if (accountInfo != null) return "AC2001";
        Database db = new Database(Variable.config.database.name);
        db.open();
        db.sqlUpdate(Function.getSQL_InsertUser(info.username, info.email, info.password));
        db.close();

        return AccountInfo.getAccountInfo(info.username) != null ? "NULL" : "AC2002";
    }

    @Override
    public void run() {
        monitor();
        end();
    }
}
