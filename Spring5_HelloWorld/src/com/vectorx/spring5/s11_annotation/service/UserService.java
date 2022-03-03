package com.vectorx.spring5.s11_annotation.service;

import com.vectorx.spring5.s11_annotation.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * value可省略，默认值为类名首字母小写
 */
//@Component(value = "userService")
@Service
public class UserService {

    //@Autowired
    //@Qualifier(value = "userDaoImpl")
    @Resource(name = "userDaoImpl")
    private UserDao userDao;

    public void add() {
        System.out.println("UserService add...");
        userDao.add();
    }
}
