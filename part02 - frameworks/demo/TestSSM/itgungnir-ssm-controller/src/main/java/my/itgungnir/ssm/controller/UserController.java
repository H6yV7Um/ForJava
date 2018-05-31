package my.itgungnir.ssm.controller;

import my.itgungnir.ssm.entity.User;
import my.itgungnir.ssm.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public String getUserById(int id) {
        return userService.getUserById(id).toString();
    }
}