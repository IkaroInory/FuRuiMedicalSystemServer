package team.arcticfox.frms.server.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class ServerList {
    @JSONField(name = "signIn")
    public Address signInServer;
    @JSONField(name = "register", ordinal = 1)
    public Address registerServer;

    public ServerList(Address signInServer, Address registerServer) {
        this.signInServer = signInServer;
        this.registerServer = registerServer;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
