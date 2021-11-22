package com.fsdcyr.sky.example.controller;

import com.fsdcyr.sky.authorization.UserContext;
import com.fsdcyr.sky.authorization.UserContextFactory;
import com.fsdcyr.sky.authorization.UserContextManager;
import com.fsdcyr.sky.authorization.annotation.LoginUser;
import com.fsdcyr.sky.common.annotation.ApiVersion;
import com.fsdcyr.sky.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 5:50 下午
 */
@ApiVersion
@RestController
@RequestMapping("/api/{version}/user")
public class UserController {

    @Autowired
    private UserContextFactory userContextFactory;
    @Autowired
    private UserContextManager userContextManager;

    @GetMapping
    public String get(@LoginUser UserContext userContext) {
        Integer userId = userContext.getUserId();
        return userId + "";
    }

    /**
     * TODO 重复login
     * @param phone
     * @param password
     * @return
     */
    @ApiVersion("1.2")
    @GetMapping("/login")
    public UserContext login(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        // userService.valid
        User user = new User();
        user.setId(1);
        user.setNickName("小明");
        user.setPhone(phone);
        UserContext userContext = userContextFactory.make(user);
        return userContext;
    }

    @PostMapping("/logout")
    public void logout(@LoginUser UserContext userContext) {
        userContextManager.remove(userContext);
    }

}
