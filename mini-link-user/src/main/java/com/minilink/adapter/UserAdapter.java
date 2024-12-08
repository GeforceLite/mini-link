package com.minilink.adapter;

import com.minilink.pojo.dto.RegisterDTO;
import com.minilink.pojo.po.MiniLinkUser;

/**
 * @Author 徐志斌
 * @Date: 2024/12/8 15:08
 * @Version 1.0
 * @Description: 账户相关-适配器
 */
public class UserAdapter {
    public static MiniLinkUser buildUserPO(RegisterDTO registerDTO){
        MiniLinkUser userPO = new MiniLinkUser();
//        userPO.setNickName();
        userPO.setEmail(registerDTO.getEmail());
        return userPO;
    }
}
