package com.minilink.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.minilink.adapter.LinkUrlAdapter;
import com.minilink.enums.BizCodeEnum;
import com.minilink.exception.BizException;
import com.minilink.pojo.dto.LinkUrlSaveDTO;
import com.minilink.pojo.po.LinkUrlTob;
import com.minilink.pojo.po.LinkUrlToc;
import com.minilink.service.LinkUrlTobService;
import com.minilink.store.LinkUrlTobStore;
import com.minilink.store.LinkUrlTocStore;
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
    private final String SHORT_LINK_FORMAT_REGEX = "^\\d+-\\d+-[a-z0-9A-Z]+$";
    @Autowired
    private LinkUrlTocStore tocStore;
    @Autowired
    private LinkUrlTobStore tobStore;

    @Override
    public void createShortLink(LinkUrlSaveDTO saveDTO) {
        String shortLink = ShortLinkUtil.generate(saveDTO.getLongLink());
        if (!shortLink.matches(SHORT_LINK_FORMAT_REGEX)) {
            throw new BizException(BizCodeEnum.SHORT_LINK_FORMAT_ERROR);
        }

        LinkUrlToc shortLinkPO = tocStore.getByShortLink(shortLink);
        if (ObjectUtils.isNotEmpty(shortLinkPO)) {
            throw new BizException(BizCodeEnum.FAIL);
        }

        // 保存短链接（两个维度）：TODO 后续推送到MQ，实现冗余双写
        LinkUrlTob tobLinkPO = LinkUrlAdapter.buildLinkUrlTobPO(
                IdWorker.getId(),
                saveDTO.getGroupId(),
                saveDTO.getTitle(),
                shortLink,
                saveDTO.getLongLink(),
                saveDTO.getExpiredTime()
        );
        tobStore.saveLink(tobLinkPO);
        LinkUrlToc tocLinkPO = LinkUrlAdapter.buildLinkUrlTocPO(
                shortLink,
                saveDTO.getLongLink(),
                saveDTO.getExpiredTime()
        );
        tocStore.saveLink(tocLinkPO);
    }
}
