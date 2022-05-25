package net.mypaper.www.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mypaper.www.mapper.WebusersMapper;
import net.mypaper.www.pojo.webusers;
import net.mypaper.www.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.mypaper.www.md5Utils.PasswordUtil.generate;


@Service
public class AdminServiceImpl extends ServiceImpl<WebusersMapper, webusers> implements AdminService {


    public void giveAccess(String uid)
    {
        WebusersMapper userMapper = this.baseMapper;
        QueryWrapper<webusers> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        webusers user = userMapper.selectOne(wrapper);
        if (user==null) return;
        user.setAccess(true);
        userMapper.updateById(user);
    }

    public void takeAccess(String uid)
    {
        WebusersMapper userMapper = this.baseMapper;
        QueryWrapper<webusers> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        webusers user = userMapper.selectOne(wrapper);
        if (user==null) return;
        user.setAccess(false);
        userMapper.updateById(user);
    }

    public void deleteOneUser(String uid)
    {
        WebusersMapper userMapper = this.baseMapper;
        QueryWrapper<webusers> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        webusers user1 = userMapper.selectOne(wrapper);
        if (user1 ==null) return;
        user1.setDeleted(1);
        updateById(user1);
    }

    public List<webusers> getAllUsers()
    {
        WebusersMapper userMapper = this.baseMapper;
        return userMapper.selectList(null);
    }

    public boolean isAdmin(String uid){
        WebusersMapper userMapper = this.baseMapper;
        QueryWrapper<webusers> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        webusers user = userMapper.selectOne(wrapper);
        return user.isAccess();
    }

}
