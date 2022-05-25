package net.mypaper.www.service.impl;

import JwtUtils.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mypaper.www.mapper.WebusersMapper;
import net.mypaper.www.pojo.webusers;
import net.mypaper.www.service.UserService;
import org.springframework.stereotype.Service;


import static net.mypaper.www.md5Utils.PasswordUtil.generate;
import static net.mypaper.www.md5Utils.PasswordUtil.verify;


@Service
public class UserServiceImpl extends ServiceImpl<WebusersMapper, webusers> implements UserService {


    public webusers registerUser(webusers user)
    {
        WebusersMapper userMapper = this.baseMapper;
        QueryWrapper<webusers> wrapper = new QueryWrapper<>();
        wrapper.eq("email",user.getEmail());
        if(userMapper.selectOne(wrapper)==null) {
            user.setDeleted(0);
            user.setAccess(false);
            String pw = generate(user.getPassword());
            user.setPassword(pw);
            userMapper.insert(user);
            return user;
        }
        else return null;       //邮箱重复不插入
    }

    public webusers login(webusers user)
    {
        WebusersMapper userMapper = this.baseMapper;
        QueryWrapper<webusers> wrapper = new QueryWrapper<>();
        wrapper.eq("email",user.getEmail());
        webusers user2 = userMapper.selectOne(wrapper);
        if(user2!=null){
            if(verify(user.getPassword(),user2.getPassword())) {
                return user2;
            }
        }
        return null;
    }

}
