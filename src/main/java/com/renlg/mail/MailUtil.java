package com.renlg.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Desc 邮件发送工具类
 * Created by yunqingwang on 14-2-19.
 */
public class MailUtil {

//    private static final Logger logger =
//            LoggerFactory.getLogger(MailUtil.class);

    /**
     * 邮件发送
     * @param mailInfo  邮件属性对象
     */
    public void sendMail(MailSenderInfo mailInfo){
//        logger.debug("Start send E-mail..." + mailInfo.getSubject());
        SimpleMailSender sms = new SimpleMailSender();
        sms.sendTextMail(mailInfo);
    }

}
