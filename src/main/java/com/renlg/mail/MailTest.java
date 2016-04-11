package com.renlg.mail;

/**
 * Desc : mail 使用例子,邮件发送测试类 Created by yqwang on 14-2-19.
 */
public class MailTest {
	public static void main(String[] args) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.126.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("testwang2014@126.com");
		mailInfo.setPassword("998877!!!");// 您的邮箱密码
		mailInfo.setFromAddress("testwang2014@126.com");
		mailInfo.setToAddress(new String[] { "771623370@qq.com"});
		// 填写附件绝对路径，如无则为null
		mailInfo.setAttachFileNames(new String[] { "" });
		mailInfo.setSubject("sumpaytest");
		mailInfo.setContent("<font style='BACKGROUND-COLOR: #666699' color='#ff0000' size='5'>"
				+ "sumpay test20140219222222" + "</font>");
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式;
		sms.sendHtmlMail(mailInfo);
		// sms.sendHtmlMail(mailInfo);// 发送html格式

		// // 这个类主要是设置邮件
		// MailSenderInfo mailInfo = new MailSenderInfo();
		// mailInfo.setMailServerHost("smtp.qq.com");
		// mailInfo.setMailServerPort("465");
		// mailInfo.setValidate(true);
		// mailInfo.setUserName("1187900875@qq.com");
		// mailInfo.setPassword("489237588");// 您的邮箱密码
		// mailInfo.setFromAddress("1187900875@qq.com");
		// mailInfo.setToAddress(new String[] { "771623370@qq.com"});
		// //填写附件绝对路径，如无则为null
		// mailInfo.setAttachFileNames(new String[] { ""});
		// mailInfo.setSubject("sumpaytest");
		// mailInfo.setContent("<font style='BACKGROUND-COLOR: #666699' color='#ff0000' size='5'>"
		// +
		// "sumpay test20140219222222"+"</font>");
		// // 这个类主要来发送邮件
		// SimpleMailSender sms = new SimpleMailSender();
		// sms.sendTextMail(mailInfo);//发送文体格式
		// //sms.sendHtmlMail(mailInfo);// 发送html格式
	}

}
