package com.baomidou.springboot.mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.baomidou.springboot.SuperMapper;
import com.baomidou.springboot.entity.User;
/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends SuperMapper<User> {
    /**
     * 自定义注入方法
     * 方法名称对应xml文件的sql的id即可
     * 这里需要特别注意，当传递多个参数的时候需要加上@Param设置对应关系，不然直接定义参数名称添加会报
     * nested exception is org.apache.ibatis.binding.BindingException: 
     * Parameter 'name' not found. Available parameters are [0, 1, param1, param2]
     * 异常
     */
    int insertTestSql(@Param("name") String name,@Param("age")Integer age,@Param("password")String password);
    
}