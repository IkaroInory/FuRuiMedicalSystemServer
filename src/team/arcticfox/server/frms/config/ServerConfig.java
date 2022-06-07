package team.arcticfox.server.frms.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class ServerConfig {
    private static final String DEFAULT_IP = "localhost";
    private static final int DEFAULT_PORT = 25565;

    @JSONField(name = "name")
    public String name = "FRMS-Server-01";
    @JSONField(name = "uuid", ordinal = 1)
    public String uuid = "be69c309-5bb2-4eb4-be5b-6d4046677d19";
    @JSONField(name = "ip", ordinal = 2)
    public String ip;
    @JSONField(name = "port", ordinal = 3)
    public int port;


    public ServerConfig() {
        this(DEFAULT_IP, DEFAULT_PORT);
    }

    public ServerConfig(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public static ServerConfig parse(String text) {
        return JSON.parseObject(text, ServerConfig.class);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
