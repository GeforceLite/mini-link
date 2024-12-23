package com.minilink.strategy.email.handler;

import com.minilink.constant.RedisConstant;
import com.minilink.enums.EmailEnum;
import com.minilink.pojo.entity.EmailParamEntity;
import com.minilink.strategy.email.AbstractEmailStrategy;
import com.minilink.util.EmailUtil;
import com.minilink.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-23  10:16
 * @Description: 注册邮件
 * @Version: 1.0
 */
@Component
public class RegisterEmailHandler extends AbstractEmailStrategy {
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected EmailEnum getEmailEnum() {
        return EmailEnum.REGISTER;
    }

    @Override
    public void packageEmail(EmailParamEntity emailParam) {
        String email = emailParam.getEmail();
        String emailCode = RandomUtil.generate(4, 1);
        String subject = "Mini Link注册账号";
        String content = "您正在使用 Mini Link 注册账号功能，您的邮件验证码为：%s（有效期3分钟），请妥善保管，不要泄露给他人！";

        String emailKey = RedisConstant.EMAIL_CODE_KEY + email;
        redisTemplate.opsForValue().set(emailKey, emailCode, 3, TimeUnit.MINUTES);
        emailUtil.sendTextMail(email, subject, String.format(content, emailCode));
        String emailCheckKey = RedisConstant.EMAIL_CHECK_KEY + email;
        redisTemplate.opsForValue().set(emailCheckKey, "Email Repeat Send Check", 60, TimeUnit.SECONDS);
    }
}
