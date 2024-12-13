package com.minilink.adapter;

import com.minilink.pojo.po.LinkUser;
import com.minilink.pojo.vo.UserVO;

/**
 * @Author 徐志斌
 * @Date: 2024/12/8 15:08
 * @Version 1.0
 * @Description: 账户相关-适配器
 */
public class UserAdapter {
    public static LinkUser buildUserPO(Long accountId, String nickName, String avatar,
                                       String email, String password, String salt) {
        LinkUser userPO = new LinkUser();
        userPO.setAccountId(accountId);
        userPO.setNickName(nickName);
        userPO.setAvatar(avatar);
        userPO.setEmail(email);
        userPO.setPassword(password);
        userPO.setSalt(salt);
        return userPO;
    }

    public static LinkUser buildUserPO(Long accountId, String nickName, String avatar, String email) {
        LinkUser userPO = new LinkUser();
        userPO.setAccountId(accountId);
        userPO.setNickName(nickName);
        userPO.setAvatar(avatar);
        userPO.setEmail(email);
        return userPO;
    }

    public static UserVO buildUserVO(LinkUser userPO) {
        UserVO userVO = new UserVO();
        userVO.setEmail(userPO.getEmail());
        userVO.setNickName(userPO.getNickName());
        userVO.setAvatar(userPO.getAvatar());
        userVO.setCreateTime(userPO.getCreateTime());
        return userVO;
    }
}
