package com.minilink.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minilink.adapter.LinkUrlAdapter;
import com.minilink.constant.LinkUrlConstant;
import com.minilink.enums.BizCodeEnum;
import com.minilink.exception.BizException;
import com.minilink.pojo.dto.LinkUrlSaveDTO;
import com.minilink.pojo.po.LinkUrlTob;
import com.minilink.pojo.po.LinkUrlToc;
import com.minilink.service.LinkUrlTobService;
import com.minilink.store.LinkUrlTobStore;
import com.minilink.store.LinkUrlTocStore;
import com.minilink.util.LinkUrlUtil;
import com.minilink.util.SnowFlakeUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
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

    @Value("${mini-link.domain}")
    private String miniLinkDomain;
    @Value("${mini-link.group-id}")
    private Long miniLinkGroupId;

    @Override
    public void createShortLink(LinkUrlSaveDTO saveDTO) {
        String shortLinkCode = LinkUrlUtil.generate(saveDTO.getLongLink());
        if (!shortLinkCode.matches(LinkUrlConstant.SHORT_LINK_FORMAT_REGEX)) {
            throw new BizException(BizCodeEnum.SHORT_LINK_FORMAT_ERROR);
        }

        LinkUrlToc shortLinkPO = urlTocStore.getByShortLinkCode(shortLinkCode);
        if (ObjectUtils.isNotEmpty(shortLinkPO)) {
            throw new BizException(BizCodeEnum.SHORT_LINK_REPEAT);
        }

        Long groupId = ObjectUtils.isNotEmpty(saveDTO.getGroupId()) ? saveDTO.getGroupId() : miniLinkGroupId;
        LinkUrlTob tobLinkPO = LinkUrlAdapter.buildLinkUrlTobPO(
                8557967973694861312L,
                groupId,
                saveDTO.getTitle(),
                saveDTO.getIcon(),
                miniLinkDomain,
                shortLinkCode,
                miniLinkDomain + shortLinkCode,
                saveDTO.getLongLink(),
                saveDTO.getExpiredTime()
        );
        urlTobStore.saveLink(tobLinkPO);
        LinkUrlToc tocLinkPO = LinkUrlAdapter.buildLinkUrlTocPO(
                SnowFlakeUtil.nextId(),
                shortLinkCode,
                miniLinkDomain + shortLinkCode,
                saveDTO.getLongLink(),
                saveDTO.getExpiredTime()
        );
        urlTocStore.saveLink(tocLinkPO);
    }

    @Override
    public Map<String, Object> parseLink(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        String title = doc.title();
        Element descriptionTag = doc.select("meta[name=description]").first();
        String description = descriptionTag != null ? descriptionTag.attr("content") : "";
        Element iconTag = doc.select("link[rel=icon], link[rel=shortcut icon]").first();
        String iconLink = iconTag != null ? iconTag.attr("href") : "";
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("description", description);
        map.put("iconLink", iconLink);
        return map;
    }

    @Override
    public Map<String, Object> getPageList(Long groupId, Integer current, Integer size) {
        Long accountId = 8557967973694861312L;
        Page<LinkUrlTob> page = urlTobStore.getPage(accountId, groupId, current, size);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", page.getRecords());
        resultMap.put("total", page.getTotal());
        return resultMap;
    }
}
