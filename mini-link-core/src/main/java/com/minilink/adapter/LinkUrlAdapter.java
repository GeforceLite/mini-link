package com.minilink.adapter;

import com.minilink.pojo.po.LinkUrlTob;
import com.minilink.pojo.po.LinkUrlToc;

import java.time.LocalDateTime;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-13  13:21
 * @Description: 链接-适配器
 * @Version: 1.0
 */
public class LinkUrlAdapter {
    public static LinkUrlTob buildLinkUrlTobPO(Long accountId, Long groupId, String title, String icon,
                                               String domain, String shortLinkCode, String shortLink,
                                               String longLink, LocalDateTime expiredTime) {
        LinkUrlTob linkUrl = new LinkUrlTob();
        linkUrl.setAccountId(accountId);
        linkUrl.setGroupId(groupId);
        linkUrl.setTitle(title);
        linkUrl.setIcon(icon);
        linkUrl.setDomain(domain);
        linkUrl.setShortLinkCode(shortLinkCode);
        linkUrl.setShortLink(shortLink);
        linkUrl.setLongLink(longLink);
        linkUrl.setExpiredTime(expiredTime);
        return linkUrl;
    }

    public static LinkUrlToc buildLinkUrlTocPO(String shortLinkCode, String shortLink, String longLink, LocalDateTime expiredTime) {
        LinkUrlToc linkUrl = new LinkUrlToc();
        linkUrl.setShortLinkCode(shortLinkCode);
        linkUrl.setShortLink(shortLink);
        linkUrl.setLongLink(longLink);
        linkUrl.setExpiredTime(expiredTime);
        return linkUrl;
    }
}
