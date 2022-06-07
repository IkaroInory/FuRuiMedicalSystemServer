package team.arcticfox.server.frms.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class ServerConfig {
    private static final String DEFAULT_NAME = "FRMS-Server-01";
    private static final String DEFAULT_UUID = "be69c309-5bb2-4eb4-be5b-6d4046677d19";
    private static final String DEFAULT_IP = "localhost";
    private static final int DEFAULT_PORT = 25565;

    @JSONField(name = "name")
    public String name;
    @JSONField(name = "uuid", ordinal = 1)
    public String uuid;
    @JSONField(name = "ip", ordinal = 2)
    public String ip;
    @JSONField(name = "port", ordinal = 3)
    public int port;


    public ServerConfig(String ip, int port) {
        this(DEFAULT_NAME, DEFAULT_UUID, ip, port);
    }

    public ServerConfig(String name, String uuid, String ip, int port) {
        this.name = name;
        this.uuid = uuid;
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
