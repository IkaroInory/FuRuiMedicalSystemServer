package team.arcticfox.frms.server.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class Address {
    @JSONField(name = "ip")
    public final String ip;
    @JSONField(name = "port", ordinal = 1)
    public final int port;

    public Address(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
