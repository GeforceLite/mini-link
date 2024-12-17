package com.minilink.store;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minilink.pojo.po.LinkUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-06
 */
public interface LinkUserStore extends IService<LinkUser> {
    /**
     * 根据 email 查询账户信息
     *
     * @param email 邮箱
     * @return 账户信息
     */
    LinkUser getByEmail(String email);

    /**
     * 保存账户信息
     *
     * @param userPO 用户信息
     * @return 执行结果
     */
    Boolean saveUser(LinkUser userPO);

    /**
     * 根据 account_id 查询用户信息
     *
     * @param accountId 账号
     * @return 用户信息
     */
    LinkUser getByAccountId(Long accountId);
}
