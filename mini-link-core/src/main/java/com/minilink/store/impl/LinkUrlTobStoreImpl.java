package com.minilink.store.impl;

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
}
