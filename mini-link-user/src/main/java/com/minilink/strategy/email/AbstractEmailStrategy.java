package com.minilink.strategy.email;


import com.minilink.enums.EmailEnum;
import com.minilink.pojo.entity.EmailParamEntity;
import jakarta.annotation.PostConstruct;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-11-07  10:50
 * @Description: 发送邮件-策略配置
 * @Version: 1.0
 */
public abstract class AbstractEmailStrategy {
    @PostConstruct
    private void initStrategyHandler() {
        EmailStrategyFactory.register(this.getEmailEnum().getCode(), this);
    }

    /**
     * 获取当前邮件枚举
     *
     * @return 邮件枚举
     */
    protected abstract EmailEnum getEmailEnum();

    /**
     * 封装邮件发送参数
     *
     * @param emailParam 发送邮件参数
     */
    public abstract void packageEmail(EmailParamEntity emailParam);
}
