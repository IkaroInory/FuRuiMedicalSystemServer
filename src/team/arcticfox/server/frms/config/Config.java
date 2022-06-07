package team.arcticfox.server.frms.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class Config {
    private static final String DEFAULT_CONFIG_VERSION = "1.0.6.7";

    @JSONField(name = "config-version")
    public final String configVersion;
    @JSONField(name = "server", ordinal = 1)
    public final ServerConfig server;

    public Config() {
        this(new ServerConfig());
    }

    public Config(ServerConfig server) {
        this.configVersion = DEFAULT_CONFIG_VERSION;
        this.server = server;
    }

    public static Config parse(String text) {
        return JSON.parseObject(text, Config.class);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
