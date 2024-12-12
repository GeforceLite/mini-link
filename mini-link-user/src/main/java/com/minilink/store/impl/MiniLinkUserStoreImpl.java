package com.minilink.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minilink.constant.RedisConstant;
import com.minilink.mapper.MiniLinkUserMapper;
import com.minilink.pojo.po.MiniLinkUser;
import com.minilink.store.MiniLinkUserStore;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MiniLinkUserStoreImpl extends ServiceImpl<MiniLinkUserMapper, MiniLinkUser> implements MiniLinkUserStore {
    @Override
    @Cacheable(value = RedisConstant.MINI_LINK_USER_KEY, key = "#p0", unless = "#result == null")
    public MiniLinkUser getByEmail(String email) {
        return this.lambdaQuery()
                .eq(MiniLinkUser::getEmail, email)
                .one();
    }

    @Override
    public Boolean saveUser(MiniLinkUser userPO) {
        return this.save(userPO);
    }
}
