package com.baomidou.springboot.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springboot.entity.User;

public interface MailService  extends IService<User>{
	public void sendSimpleMail(String to, String subject, String content);
	
	public void sendHtmlMail(String to, String subject, String content);
	
	public void sendAttachmentsMail(String to, String subject, String content, String filePath);
	
	public void sendPictrueMail(String to, String subject, String content, String rscPath, String rscId);
	
	public void sendTemplateMail(String to, String subject, String content);
}
