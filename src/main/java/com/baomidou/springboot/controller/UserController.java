package com.baomidou.springboot.controller;
import com.baomidou.springboot.entity.User;
import com.baomidou.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	/**
	 * 插入数据
	 */
	@GetMapping("/add")
	public void add() {
		User user = new User("zhangSan", 25, "123456",new Date());
		//第一种插入办法
		user.insert();
		//第二种插入办法
		userService.insert(user);
		//批量插入
		//userService.insertBatch(new ArrayList<User>());
		System.out.println(user.getId());
	}
	/**
	 * 基于原生sql的xml插入
	 */
	@GetMapping("/sqlAdd")
	public void selectBySQL(){
		userService.insertTest("usesdf",28,"555dfsd55");
	}
}
