package com.lizhan.system.service;

import com.lizhan.core.base.BaseService;
import com.lizhan.system.iservice.IUserService;
import com.lizhan.system.model.User;
import com.lizhan.system.model.UserExample;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<String, User, UserExample> implements IUserService {

}
