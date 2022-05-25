package net.mypaper.www.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class webusers {
    @TableId(type = IdType.ASSIGN_ID)
    private String uid;
    private String account;
    private String password;
    private boolean access;
    private String email;
    @TableLogic
    private Integer deleted;


    public void setAccess(boolean access ) {
        this.access = access;
    }

    public boolean isAccess() {
        return access;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public String getUid() {
        return uid;
    }

    public String getAccount() {
        return account;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "webusers{" +
                "uid=" + uid +
                ", account=" + account +
                ", password=" + password +
                ", access=" + access +
                ", email=" + email + '\'' +
                '}';
    }

}
