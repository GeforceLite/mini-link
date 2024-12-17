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

import java.util.Map;

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
    @Autowired
    private LinkUrlTocStore urlTocStore;
    @Autowired
    private LinkUrlTobStore urlTobStore;

    @Override
    public void createShortLink(LinkUrlSaveDTO saveDTO) {
        String shortLink = ShortLinkUtil.generate(saveDTO.getLongLink());
        if (!shortLink.matches(ShortLinkUtil.SHORT_LINK_FORMAT_REGEX)) {
            throw new BizException(BizCodeEnum.SHORT_LINK_FORMAT_ERROR);
        }
        LinkUrlToc shortLinkPO = urlTocStore.getByShortLink(shortLink);
        if (ObjectUtils.isNotEmpty(shortLinkPO)) {
            throw new BizException(BizCodeEnum.SHORT_LINK_REPEAT);
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
        urlTobStore.saveLink(tobLinkPO);
        LinkUrlToc tocLinkPO = LinkUrlAdapter.buildLinkUrlTocPO(
                shortLink,
                saveDTO.getLongLink(),
                saveDTO.getExpiredTime()
        );
        urlTocStore.saveLink(tocLinkPO);
    }

    @Override
    public Map<String, Object> parseLink(String link) {
        return null;
    }
}
