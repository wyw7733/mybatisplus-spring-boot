package com.baomidou.springboot.entity;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
@SuppressWarnings("serial")
public class User extends SuperEntity<User> {
    private String name;
    private Integer age;
    private String password;
    //定义数据库字段别名
    @TableField("createDate")
    private Date testDate;
    
    public User() {
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public User(String name, Integer age, String password, Date testDate) {
		super();
		this.name = name;
		this.age = age;
		this.password = password;
		this.testDate = testDate;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", password=" + password + ", testDate=" + testDate + "]";
	}

}
