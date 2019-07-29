package com.zb.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;

import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@Api(value = "这是一个测试类",description = "测试类")
public class Vmcontroller {


    @Autowired
    KafkaTemplate kafkaTemplate;


  private   static List<User>users=new ArrayList<>();
    static {
        users.add( new User(1,"zahngsan"));
        users.add( new User(1,"zahngsan"));
        users.add( new User(1,"zahngsan"));
    }


    @ApiOperation(value = "用户查询方法",notes = "查询用户方法")
    @RequestMapping(value = "show",method = RequestMethod.GET)
    public  Dto  quer(){
        return  DtoUtil.returnSuccess("ok",users);
    }


    @ApiOperation(value = "删除用户方法",notes = "删除用户方法")
    @ApiImplicitParam(value = "用户id",required = true,name = "id",dataType = "int",paramType = "path")
    @RequestMapping(value = "/remove/{id}",method = RequestMethod.GET)
    public Dto  remove(@PathVariable("id") int id){
        for(User u:users){
            if(u.getId()==id){
                users.remove(u);
                return  DtoUtil.returnSuccess("ok");
            }

        }
        return  DtoUtil.returnSuccess("success");
    }


    @ApiOperation(value = "添加用户方法",notes = "添加用户方法")
    @ApiImplicitParam(value = "用户",name = "user1",dataType = "User")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Dto  insert( User user1) {
        users.add(user1);
        return  DtoUtil.returnSuccess("ok");
    }

    @ApiOperation(value = "修改用户方法",notes = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户编号",required = true,name = "id",dataType = "int",paramType = "path"),
            @ApiImplicitParam(value = "用户对象",required = true,name = "user",dataType = "User")
    })
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public Dto update(@PathVariable("id") int id , @RequestBody User user1){
        for (int i = 0;i<users.size();i++){
            User u = users.get(i);
            if(u.getId()==id){
                users.set(i,user1);
                return DtoUtil.returnSuccess("ok");
            }
        }
        return DtoUtil.returnSuccess("input");
    }




    @GetMapping(value = "/add")
    public Dto show(){
    for(int i=0;i<10;i++){
            kafkaTemplate.send("dmservice","dm","实际传输的值"+i);
    }
    return DtoUtil.returnSuccess("ok");
    }
}
