package team.arcticfox.frms.server.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class Config {
    private static final String DEFAULT_CONFIG_VERSION = "1.0.6.7";

    @JSONField(name = "config-version")
    public final String configVersion;
    @JSONField(name = "server", ordinal = 1)
    public ServerConfig server;
    @JSONField(name = "database", ordinal = 2)
    public DatabaseConfig database;

    public Config(String configVersion, ServerConfig server, DatabaseConfig database) {
        this.configVersion = configVersion;
        this.server = server;
        this.database = database;
    }


    public static Config parse(String text) {
        return JSON.parseObject(text, Config.class);
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
