package com.minilink.store;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minilink.pojo.po.MiniLinkUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-06
 */
public interface MiniLinkUserStore extends IService<MiniLinkUser> {
    /**
     * 根据 email 查询账户信息
     *
     * @param email 邮箱
     * @return 账户信息
     */
    MiniLinkUser getByEmail(String email);

    /**
     * 保存账户信息
     *
     * @param userPO 用户信息
     * @return 执行结果
     */
    Boolean saveUser(MiniLinkUser userPO);
}
