package com.minilink.store.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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
    @Cacheable(value = RedisConstant.CACHE_LINK_USER, key = "#p0", unless = "#result == null")
    public LinkUser getByEmail(String email) {
        return this.lambdaQuery()
                .eq(LinkUser::getEmail, email)
                .one();
    }

    @Override
    public Boolean saveUser(LinkUser userPO) {
        return this.save(userPO);
    }

    @Override
    @Cacheable(value = RedisConstant.CACHE_LINK_USER, key = "#p0", unless = "#result == null")
    public LinkUser getByAccountId(Long accountId) {
        return this.lambdaQuery()
                .eq(LinkUser::getAccountId, accountId)
                .one();
    }

    @Override
    public boolean updatePwd(Long accountId, String encryptNewPwdWithSalt) {
        LambdaUpdateWrapper<LinkUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(LinkUser::getPassword,encryptNewPwdWithSalt);
        updateWrapper.eq(LinkUser::getAccountId, accountId);
        return this.update(updateWrapper);
    }

}
