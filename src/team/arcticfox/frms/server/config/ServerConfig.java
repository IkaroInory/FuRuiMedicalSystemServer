package team.arcticfox.frms.server.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class ServerConfig {
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "uuid", ordinal = 1)
    public String uuid;
    @JSONField(name = "ip", ordinal = 2)
    public String ip;
    @JSONField(name = "port", ordinal = 3)
    public int port;
    @JSONField(name = "list", ordinal = 4)
    public ServerList list;


    public ServerConfig(String name, String uuid, String ip, int port, ServerList list) {
        this.name = name;
        this.uuid = uuid;
        this.ip = ip;
        this.port = port;
        this.list = list;
    }


    public static ServerConfig parse(String text) {
        return JSON.parseObject(text, ServerConfig.class);
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
