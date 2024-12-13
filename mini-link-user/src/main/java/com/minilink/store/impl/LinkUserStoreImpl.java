package com.minilink.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minilink.constant.RedisConstant;
import com.minilink.mapper.LinkUserMapper;
import com.minilink.pojo.po.LinkUser;
import com.minilink.store.LinkUserStore;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-06
 */
@Service
public class LinkUserStoreImpl extends ServiceImpl<LinkUserMapper, LinkUser> implements LinkUserStore {
    @Override
    @Cacheable(value = RedisConstant.LINK_USER_KEY, key = "#p0", unless = "#result == null")
    public LinkUser getByEmail(String email) {
        return this.lambdaQuery()
                .eq(LinkUser::getEmail, email)
                .one();
    }

    @Override
    public Boolean saveUser(LinkUser userPO) {
        return this.save(userPO);
    }
}
