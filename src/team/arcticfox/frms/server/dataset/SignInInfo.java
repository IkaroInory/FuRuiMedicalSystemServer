package team.arcticfox.frms.server.dataset;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class SignInInfo implements IJson {
    @JSONField(name = "username")
    public String username;
    @JSONField(name = "password")
    public String password;

    SignInInfo() {
        this("", "");
    }

    SignInInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toJsonString() {
        return JSON.toJSONString(this);
    }
}
