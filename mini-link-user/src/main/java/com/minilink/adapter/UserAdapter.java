package com.minilink.adapter;

import com.minilink.pojo.po.MiniLinkUser;
import com.minilink.pojo.vo.UserVO;

/**
 * @Author 徐志斌
 * @Date: 2024/12/8 15:08
 * @Version 1.0
 * @Description: 账户相关-适配器
 */
public class UserAdapter {
    public static MiniLinkUser buildUserPO(String nickName, String avatar, String email, String password, String salt) {
        MiniLinkUser userPO = new MiniLinkUser();
        userPO.setNickName(nickName);
        userPO.setAvatar(avatar);
        userPO.setEmail(email);
        userPO.setPassword(password);
        userPO.setSalt(salt);
        return userPO;
    }

    public static UserVO buildUserVO(MiniLinkUser userPO) {
        UserVO userVO = new UserVO();
        userVO.setEmail(userPO.getEmail());
        userVO.setNickName(userPO.getNickName());
        userVO.setAvatar(userPO.getAvatar());
        userVO.setCreateTime(userPO.getCreateTime());
        return userVO;
    }
}
