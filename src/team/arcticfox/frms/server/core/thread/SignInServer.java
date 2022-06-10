package team.arcticfox.frms.server.core.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.Feature;
import team.arcticfox.frms.exception.FuRuiException;
import team.arcticfox.frms.exception.NullException;
import team.arcticfox.frms.exception.account.PasswordIsWrongException;
import team.arcticfox.frms.exception.account.UserNotFoundException;
import team.arcticfox.frms.server.database.Database;
import team.arcticfox.frms.server.dataset.AccountInfo;
import team.arcticfox.frms.server.dataset.DateTime;
import team.arcticfox.frms.server.dataset.IJson;
import team.arcticfox.frms.server.environment.Function;
import team.arcticfox.frms.server.environment.Variable;
import team.arcticfox.frms.server.log.Log;
import team.arcticfox.frms.server.security.MD5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


class SignInInfo implements IJson {
    @JSONField(name = "username")
    public String username;
    @JSONField(name = "password", ordinal = 1)
    public String password;

    SignInInfo() {
        this("", "");
    }

    SignInInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toJsonString() {
        return JSON.toJSONString(this);
    }
}


public class SignInServer extends Thread {
    private ServerSocket server;

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

    private void monitor() {
        while (true) {
            try {
                Socket socket = server.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                String msg = in.readUTF();
                SignInInfo info = JSON.parseObject(msg, SignInInfo.class);

                DateTime dateTime = DateTime.now();
                String uuid = Function.getTimeStamp(dateTime);

                Function.printSession("Login session: " + socket.getRemoteSocketAddress());
                Function.printSession("Session UUID: " + uuid);

                String exceptionCode = signIn(info.username, info.password);

                out.writeUTF(exceptionCode);
                out.flush();

                AccountInfo accountInfo = AccountInfo.getAccountInfo(info.username);
                if (accountInfo == null)
                    out.writeUTF("");
                else
                    out.writeUTF(AccountInfo.getAccountInfo(info.username).toJsonString());
                out.flush();

                if (exceptionCode.equals(new NullException().code))
                    Function.printSession("Login successful.");
                else
                    Function.printSession("Login failed. " + FuRuiException.parse(exceptionCode).getMessage());

                out.close();
                in.close();
                socket.close();

                Log.createSignInServerLog(uuid, dateTime, socket.getRemoteSocketAddress(), exceptionCode, JSON.parseObject(msg, Feature.OrderedField));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String signIn(String username, String password) {
        AccountInfo accountInfo = AccountInfo.getAccountInfo(username);
        if (accountInfo == null) return new UserNotFoundException().code;
        if (!MD5.encode(password).equals(accountInfo.password)) return new PasswordIsWrongException().code;

        Database db = new Database(Variable.config.database.name);
        db.open();
        db.sqlUpdate(Function.getSQL_UpdateLastLoginTime(accountInfo.id));
        db.close();

        return new NullException().code;
    }

    @Override
    public void run() {
        monitor();
        end();
    }
}
