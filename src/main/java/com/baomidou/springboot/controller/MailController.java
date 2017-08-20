package com.baomidou.springboot.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.baomidou.springboot.service.MailService;
/**
 * 以下所有功能是基于springboot实现的，如果没用次框架，实现办法是仅供参考
 * 1.需要注意的是邮件内容，邮件标题中请勿出现中文“测试”，“test”，等字眼，不然邮箱服务器会当成垃圾拦截处理
 * 2.发送前需设置发送方的smtp协议跟pop3协议处于开启状态，并且记住授权码设置到配置文件的password项，邮件接收方可以不开启
 * 3.application.properties文件是springboot的默认配置文件，邮件的基本配置设置好后自动读取
 * 4.如果您没有使用springboot框架，那么请参照spring的实现：http://www.111cn.net/jsp/Java/117435.htm
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private MailService mailService;
	/**
	 * springboot提供的模板引擎
	 */
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	 /**
     * 由一个邮箱发送给另一个邮箱的简单例子
     * 这里以：wuyawei7733@163.com 发送给1102605953@qq.com 为例
     * 
     */
	@GetMapping("/test")
	public String sendMail(){
		try{
			mailService.sendSimpleMail("1102605953@qq.com","第三份邮件了","好无语啊");
			return "success";
		}catch (Exception e) {
			return "fail";
		}
	}
	/**
	 * 发送一个html邮件
	 * @return
	 */
	@GetMapping("/html")
	public String sendHtmlMail(){
		String content = "<html>\n" +
	            "<body>\n" +
	            "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
	            "</body>\n" +
	            "</html>";
		try{
			mailService.sendHtmlMail("1102605953@qq.com","这是个html格式邮件",content);
			return "success";
		}catch (Exception e) {
			return "fail";
		}
	}
	/**
	 * 发送带附件的邮件
	 * @return
	 */
	@GetMapping("/attachMents")
	public String sendAttachMentsMail(){
		try{
			String filePath = "E://java/lib.zip";
		    mailService.sendAttachmentsMail("1102605953@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
			return "success";
		}catch (Exception e) {
			return "fail";
		}
		
	}
	
	/**
	 * 发送带图片的邮件
	 * 添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和 helper.addInline(rscId, res) 来实现
	 * @return
	 */
	@GetMapping("/pictrue")
	public String sendPictrueMail(){
		try{
			String rscId = "pictrue";
		    String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
		    String imgPath = "E://testPictrue/qqSnapShot.png";
		    mailService.sendPictrueMail("1102605953@qq.com","主题：这是有图片的邮件", content, imgPath, rscId);
			return "success";
		}catch (Exception e) {
			return "fail";
		}
		
	}
	
	/**
	 * 发送模板邮件
	 * 1.配置文件需要添加内容：
	 * 这里的这个name参数没传进去，，暂时不知道为啥····
	 * @return
	 */
	@GetMapping("/template")
	public String sendTemplateMail(){
		try{
			Context context = new Context();
		    context.setVariable("name", "wuyawei");
		    String emailContent = templateEngine.process("mail_template01", context);
		    mailService.sendTemplateMail("1102605953@qq.com","主题：这是模板邮件",emailContent);
		    return "success";
		}catch (Exception e) {
			return "fail";
		}
		
		
	}

}
