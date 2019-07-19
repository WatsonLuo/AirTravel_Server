package com.iss.controller;

import com.iss.entity.User;
import com.iss.service.UserService;
import com.iss.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
public class UserControllerWEB {
    @Autowired
    UserService userService;

    @RequestMapping(value = "getAllUserWEB",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<User> getAllUserWEB(){return userService.getAllUser();}

    @RequestMapping(value = "loginWEB",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public int loginWEB(String login,String password){
        User user=userService.getTheUser(login);
        if(user==null)return -1;
        if(user.getIsOnline()/10==0)user.setIsOnline(user.getIsOnline()+10);
        else return -3;
        if(user.getPassword()==null||!user.getPassword().equals(password))return -2;
        if(userService.updateIsOnline(user)!=1)return -4;

        return 0;
    }


    @RequestMapping(value = "signOutWEB",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public int signOutWEB(String flag){
        User user=userService.getTheUser(flag);
        if(user==null)return -1;
        user.setIsOnline(user.getIsOnline()%10);
        if(userService.updateIsOnline(user)==1)return 0;
        return -2;
    }

    @RequestMapping(value = "getTheUserWEB",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public User getTheUserWEB(String flag){
        User user=userService.getTheUser(flag);
        user.setPassword("");
        if(user==null)return null;
        if(user.getIsOnline()>0)return user;
        user=new User();
        return user;
    }

    @RequestMapping(value = "addUserWEB",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public int addUserWEB(User user){
        if(user.getId()==null)return -1;
        if(user.getName()==null)return -2;
        if(user.getPassword()==null)return -3;
        if(user.getEmail()==null)return -4;
        if(user.getPhone()==null)return -5;

        if(!RegexUtil.isIDLegal(user.getId()))return -6;
        if(!RegexUtil.isEmailLegal(user.getEmail()))return -7;
        if(user.getSex()!=1&&user.getSex()!=0)return -8;
        if(!RegexUtil.isPhoneLegal(user.getPhone()))return -9;

        if(userService.addUser(user)==1) return 0;
        return -10;
    }

    @RequestMapping(value = "deleteUserWEB",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public int deleteUserWEB(String flag){return userService.deleteUser(flag);}

    @RequestMapping(value = "updateUserWEB",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public int updateUserWEB(User user)
    {
        User oldUser=userService.getTheUser(user.getId());
        if(oldUser==null)return -1;//用户id返回错误
        else
        {
            //将合法的更新进行更改
            if(user.getName()==null)user.setName(oldUser.getName());
            if(user.getPassword()==null)user.setPassword(oldUser.getPassword());
            if(user.getEmail()==null) user.setEmail(oldUser.getEmail());
            else{
                if (!RegexUtil.isEmailLegal(user.getEmail())) return -2;
                else {
                    if(!oldUser.getEmail().equals(user.getEmail())) {
                        //新邮箱验证
                    }
                }
            }
            if(user.getSex()!=1&&user.getSex()!=0)return -3;
            if(user.getPhone()==null) user.setPhone(oldUser.getPhone());
            else{
                if (!RegexUtil.isPhoneLegal(user.getPhone())) return -4;
            }
            user.setIsOnline(oldUser.getIsOnline());
            System.out.println(user);

            if(userService.updateUser(user)==1)return 0;
            else return -5;
        }
    }
}
