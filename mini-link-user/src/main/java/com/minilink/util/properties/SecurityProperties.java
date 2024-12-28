package com.minilink.util.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 密码配置类
 *
 * @author : Barry.chen
 * @date : 2024-12-28
 **/
@Component
@ConfigurationProperties(prefix = "security-code")
@Data
public class SecurityProperties {

    private AesProperties aes;

    private PasswordProperties password;

}
