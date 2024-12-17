package com.minilink.service;

import com.minilink.pojo.vo.UserVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-06
 */
public interface UserInfoService {
    /**
     * 根据 account_id 查询用户信息
     *
     * @param accountId 账号
     * @return 用户信息
     */
    UserVO getUserInfo(Long accountId);
}
