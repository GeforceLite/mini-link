package com.minilink.service.impl;

import com.minilink.adapter.LinkUrlAdapter;
import com.minilink.pojo.dto.LinkUrlSaveDTO;
import com.minilink.pojo.po.LinkUrl;
import com.minilink.service.LinkUrlService;
import com.minilink.store.LinkUrlStore;
import com.minilink.util.ShortLinkUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LinkUrlServiceImpl implements LinkUrlService {
    @Autowired
    private LinkUrlStore urlStore;

    @Override
    public void createShortLink(LinkUrlSaveDTO saveDTO) {
        String shortLink = ShortLinkUtil.generate(saveDTO.getLongLink());
        // TODO 校验生成短链接格式是否正确
        // TODO 校验缓存中是否存在
        LinkUrl linkUrlPO = LinkUrlAdapter.buildLinkUrlPO(
                saveDTO.getGroupId(),
                saveDTO.getTitle(),
                shortLink,
                saveDTO.getLongLink(),
                saveDTO.getExpiredTime()
        );
        urlStore.saveLink(linkUrlPO);
    }
}
