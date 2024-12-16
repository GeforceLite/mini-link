package com.minilink.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minilink.mapper.LinkUrlTocMapper;
import com.minilink.pojo.po.LinkUrlToc;
import com.minilink.store.LinkUrlTocStore;
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
public class LinkUrlTocStoreImpl extends ServiceImpl<LinkUrlTocMapper, LinkUrlToc> implements LinkUrlTocStore {
    @Override
    public Boolean saveLink(LinkUrlToc linkUrl) {
        return this.save(linkUrl);
    }
}
