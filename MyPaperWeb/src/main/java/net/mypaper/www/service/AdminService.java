package net.mypaper.www.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.mypaper.www.pojo.webusers;

import java.util.List;

public interface AdminService extends IService<webusers> {
    public void giveAccess(String uid);
    public void takeAccess(String uid);
    public void deleteOneUser(String uid);
    public List<webusers> getAllUsers();
    public boolean isAdmin(String uid);
}
