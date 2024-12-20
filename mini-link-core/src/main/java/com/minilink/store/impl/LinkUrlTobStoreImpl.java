package com.minilink.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minilink.mapper.LinkUrlTobMapper;
import com.minilink.pojo.po.LinkUrlTob;
import com.minilink.store.LinkUrlTobStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-09
 */
@Service
public class LinkUrlTobStoreImpl extends ServiceImpl<LinkUrlTobMapper, LinkUrlTob> implements LinkUrlTobStore {
    @Override
    public Boolean saveLink(LinkUrlTob linkUrl) {
        return this.save(linkUrl);
    }

    @Override
    public Page<LinkUrlTob> getPage(Long accountId, Long groupId, Integer current, Integer size) {
        LambdaQueryWrapper<LinkUrlTob> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LinkUrlTob::getAccountId, accountId);
        wrapper.eq(LinkUrlTob::getGroupId, groupId);
        return this.page(new Page<>(current, size), wrapper);
    }
}
