package com.baomidou.springboot.service.impl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.springboot.entity.User;
import com.baomidou.springboot.mapper.UserMapper;
import com.baomidou.springboot.service.IUserService;

/**
 * User 表数据服务层接口实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public boolean insertTest(String name, Integer age, String password) {
	  //返回影响记录数
	  int i = baseMapper.insertTestSql(name,age,password);
	  if (1 == i){
		  return true;
	  }
	  return false;
	}
	
	
}