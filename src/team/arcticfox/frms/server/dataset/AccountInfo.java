package team.arcticfox.frms.server.dataset;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import team.arcticfox.frms.server.environment.Constant;
import team.arcticfox.frms.server.security.Base64;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountInfo implements IJson {
    @JSONField(name = "id")
    public int id;
    @JSONField(name = "username", ordinal = 1)
    public String username;
    @JSONField(name = "email", ordinal = 2)
    public String email;
    @JSONField(name = "password", serialize = false)
    public String password;
    @JSONField(name = "permission", ordinal = 3)
    public String permission;
    @JSONField(name = "registration-time", ordinal = 4, serializeUsing = DateTimeSerializer.class)
    public DateTime registrationTime;
    @JSONField(name = "destruction-time", ordinal = 5, serializeUsing = DateTimeSerializer.class)
    public DateTime destructionTime;
    @JSONField(name = "last-login-time", ordinal = 6, serializeUsing = DateTimeSerializer.class)
    public DateTime lastLoginTime;


    public AccountInfo() {
        this(0, "", "", "", "", DateTime.now(), DateTime.now(), DateTime.now());
    }

    public AccountInfo(int id, String username, String email, String password, String permission, DateTime registrationTime, DateTime destructionTime, DateTime lastLoginTime) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.permission = permission;
        this.registrationTime = registrationTime;
        this.destructionTime = destructionTime;
        this.lastLoginTime = lastLoginTime;
    }


    public static AccountInfo parse(ResultSet rs) {
        int id = 0;
        String username = "";
        String email = "";
        String password = "";
        String permission = "";
        DateTime registrationTime = null;
        DateTime destructionTime = null;
        DateTime lastLoginTime = null;
        try {
            id = rs.getInt(Constant.COLUMN_ID);
            username = Base64.decode(rs.getString(Constant.COLUMN_USERNAME));
            email = Base64.decode(rs.getString(Constant.COLUMN_EMAIL));
            password = Base64.decode(rs.getString(Constant.COLUMN_PASSWORD));
            permission = rs.getString(Constant.COLUMN_PERMISSION);
            registrationTime = DateTime.parse(rs.getString(Constant.COLUMN_REGISTRATION_TIME));
            destructionTime = DateTime.parse(rs.getString(Constant.COLUMN_DESTRUCTION_TIME));
            lastLoginTime = DateTime.parse(rs.getString(Constant.COLUMNLABEL_LASTLOGIN_TIME));

        } catch (SQLException e) {
            // Do nothing.
        }
        return new AccountInfo(id, username, email, password, permission, registrationTime, destructionTime, lastLoginTime);
    }

    @Override
    public String toJsonString() {
        return JSON.toJSONString(this);
    }
}
