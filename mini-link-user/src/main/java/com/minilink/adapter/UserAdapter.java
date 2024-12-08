package com.minilink.adapter;

import com.minilink.pojo.po.MiniLinkUser;

/**
 * @Author 徐志斌
 * @Date: 2024/12/8 15:08
 * @Version 1.0
 * @Description: 账户相关-适配器
 */
public class UserAdapter {
    public static MiniLinkUser buildUserPO(String email, String password, String nickName, String salt, String avatar) {
        MiniLinkUser userPO = new MiniLinkUser();
//        userPO.setNickName();
        userPO.setEmail(email);
        userPO.setPassword(password);
        return userPO;
    }
}
