package com.minilink.util.properties;

import lombok.Data;

/**
 * AES加密配置类
 *
 * @author : Barry.chen
 * @date : 2024-12-28
 **/
@Data
public class AesProperties {

    private String key;

    private String iv;

}
