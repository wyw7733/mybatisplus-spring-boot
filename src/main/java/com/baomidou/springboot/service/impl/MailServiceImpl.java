package com.baomidou.springboot.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.springboot.entity.User;
import com.baomidou.springboot.mapper.UserMapper;
import com.baomidou.springboot.service.MailService;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class MailServiceImpl extends ServiceImpl<UserMapper, User> implements MailService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;
    @Value("${mail.fromMail.addr}")
    private String from;
    
   /**
    * 发送单个简单邮件
    */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }
    
    /**
     * 发送html邮件
     */
     public void sendHtmlMail(String to, String subject, String content) {
    	 MimeMessage message = mailSender.createMimeMessage();

    	    try {
    	        //true表示需要创建一个multipart message
    	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
    	        helper.setFrom(from);
    	        helper.setTo(to);
    	        helper.setSubject(subject);
    	        helper.setText(content, true);

    	        mailSender.send(message);
    	        logger.info("html邮件发送成功");
    	    } catch (MessagingException e) {
    	        logger.error("发送html邮件时发生异常！", e);
    	    }
     }
     
     /**
      * 发送带附件的邮件
      * 这里需要截取附件文件名称加后缀
      */
     public void sendAttachmentsMail(String to, String subject, String content, String filePath){
	    MimeMessage message = mailSender.createMimeMessage();
	    try {
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom(from);
	        helper.setTo(to);
	        helper.setSubject(subject);
	        helper.setText(content, true);
	        FileSystemResource file = new FileSystemResource(new File(filePath));
	        //String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
	        helper.addAttachment("lib.zip", file);
	        mailSender.send(message);
	        logger.info("带附件的邮件已经发送。");
	    } catch (MessagingException e) {
	        logger.error("发送带附件的邮件时发生异常！", e);
	    }
	}
     /**
      * 发送带有图片的邮件
      */
     public void sendPictrueMail(String to, String subject, String content, String rscPath, String rscId){
    	    MimeMessage message = mailSender.createMimeMessage();
    	    try {
    	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
    	        helper.setFrom(from);
    	        helper.setTo(to);
    	        helper.setSubject(subject);
    	        helper.setText(content, true);
    	        FileSystemResource res = new FileSystemResource(new File(rscPath));
    	        helper.addInline(rscId, res);
    	        mailSender.send(message);
    	        logger.info("嵌入静态资源的邮件已经发送。");
    	    } catch (MessagingException e) {
    	        logger.error("发送嵌入静态资源的邮件时发生异常！", e);
    	    }
    	}

	@Override
	public void sendTemplateMail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
	    try {
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom(from);
	        helper.setTo(to);
	        helper.setSubject(subject);
	        helper.setText(content, true);
	        mailSender.send(message);
	        logger.info("模板邮件已经发送。");
	    } catch (MessagingException e) {
	        logger.error("发送带模板的邮件时发生异常！", e);
	    }
	}
		
}