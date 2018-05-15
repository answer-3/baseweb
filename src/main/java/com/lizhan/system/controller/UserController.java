package com.lizhan.system.controller;

import com.lizhan.core.base.BaseController;
import com.lizhan.system.model.User;
import com.lizhan.system.model.UserExample;
import org.springframework.stereotype.Controller;

@Controller("User")
public class UserController extends BaseController<String, User, UserExample> {

}
