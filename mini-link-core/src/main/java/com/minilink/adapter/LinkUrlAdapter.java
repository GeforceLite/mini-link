package com.minilink.adapter;

import com.minilink.pojo.po.LinkUrlTob;

import java.time.LocalDateTime;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-13  13:21
 * @Description: 链接-适配器
 * @Version: 1.0
 */
public class LinkUrlAdapter {
    public static LinkUrlTob buildLinkUrlPO(Long groupId, String title, String shortLink,
                                            String longLink, LocalDateTime expiredTime) {
        LinkUrlTob linkUrl = new LinkUrlTob();
        linkUrl.setGroupId(groupId);
        linkUrl.setTitle(title);
        linkUrl.setShortLink(shortLink);
        linkUrl.setLongLink(longLink);
        linkUrl.setExpiredTime(expiredTime);
        return linkUrl;
    }
}
