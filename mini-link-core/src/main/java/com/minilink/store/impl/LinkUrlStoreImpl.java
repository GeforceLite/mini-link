package com.minilink.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minilink.mapper.LinkUrlMapper;
import com.minilink.pojo.po.LinkUrl;
import com.minilink.store.LinkUrlStore;
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
public class LinkUrlStoreImpl extends ServiceImpl<LinkUrlMapper, LinkUrl> implements LinkUrlStore {
    @Override
    public Boolean saveLink(LinkUrl linkUrl) {
        return this.save(linkUrl);
    }
}
