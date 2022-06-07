package team.arcticfox.server.frms.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicConfig {
    private static final String DEFAULT_CONFIG_VERSION = "1.0.6.7";

    @JSONField(name = "config-version")
    public final String configVersion;
    @JSONField(name = "server", ordinal = 1)
    public final ServerConfig server;


    public BasicConfig(ServerConfig server) {
        this(DEFAULT_CONFIG_VERSION, server);
    }

    public BasicConfig(String configVersion, ServerConfig server) {
        this.configVersion = configVersion;
        this.server = server;
    }


    public static BasicConfig parse(String text) {
        return JSON.parseObject(text, BasicConfig.class);
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
