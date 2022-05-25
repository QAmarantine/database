package net.mypaper.www.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.mypaper.www.pojo.webusers;



public interface UserService extends IService<webusers> {
    public webusers registerUser(webusers user) ;

    public webusers login(webusers user);

}
