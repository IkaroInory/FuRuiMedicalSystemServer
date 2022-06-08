package team.arcticfox.frms.server.config;

import com.alibaba.fastjson.annotation.JSONField;

public class DatabaseConfig {
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "ip", ordinal = 1)
    public String ip;
    @JSONField(name = "port", ordinal = 2)
    public int port;
    @JSONField(name = "root-username", ordinal = 3)
    public String rootUsername;
    @JSONField(name = "root-password", ordinal = 4)
    public String rootPassword;


    public DatabaseConfig(String name, String ip, int port, String rootUsername, String rootPassword) {
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.rootUsername = rootUsername;
        this.rootPassword = rootPassword;
    }
}
