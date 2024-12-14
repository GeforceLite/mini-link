package com.minilink.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.minilink.adapter.LinkUrlAdapter;
import com.minilink.enums.BizCodeEnum;
import com.minilink.exception.BizException;
import com.minilink.pojo.dto.LinkUrlSaveDTO;
import com.minilink.pojo.po.LinkUrlTob;
import com.minilink.service.LinkUrlTobService;
import com.minilink.store.LinkUrlTobStore;
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
public class LinkUrlTobServiceImpl implements LinkUrlTobService {
    private final String SHORT_LINK_FORMAT_REGEX = "^\\d+-[a-z0-9A-Z]+$";
    @Autowired
    private LinkUrlTobStore urlStore;

    @Override
    public void createShortLink(LinkUrlSaveDTO saveDTO) {
        String shortLink = ShortLinkUtil.generate(saveDTO.getLongLink());
        if (!shortLink.matches(SHORT_LINK_FORMAT_REGEX)) {
            throw new BizException(BizCodeEnum.SHORT_LINK_FORMAT_ERROR);
        }
        // TODO 校验缓存中是否存在
        LinkUrlTob linkUrlPO = LinkUrlAdapter.buildLinkUrlPO(
                IdWorker.getId(),
                saveDTO.getGroupId(),
                saveDTO.getTitle(),
                shortLink,
                saveDTO.getLongLink(),
                saveDTO.getExpiredTime()
        );
        urlStore.saveLink(linkUrlPO);
    }
}
