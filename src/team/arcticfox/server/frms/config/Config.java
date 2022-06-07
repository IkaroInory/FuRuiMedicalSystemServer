package team.arcticfox.server.frms.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class Config {
    private static final String DEFAULT_CONFIG_VERSION = "1.0.6.7";
    private static final int DEFAULT_PORT = 25565;

    @JSONField(name = "config-version")
    private final String configVersion;
    @JSONField(name = "server-port")
    private final int serverPort;

    public Config() {
        this(DEFAULT_PORT);
    }

    public Config(int serverPort) {
        this.configVersion = DEFAULT_CONFIG_VERSION;
        this.serverPort = serverPort;
    }

    public String getConfigVersion() {
        return configVersion;
    }

    public int getServerPort() {
        return serverPort;
    }

    public JSONObject toJsonObject() {
        return JSONObject.parseObject(toString());
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
