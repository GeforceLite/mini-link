package com.minilink.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.minilink.enums.BizCodeEnum;
import com.minilink.exception.BizException;
import com.minilink.pojo.po.LinkUrlToc;
import com.minilink.service.LinkUrlTocService;
import com.minilink.store.LinkUrlTocStore;
import com.minilink.util.HttpServletUtil;
import com.minilink.util.ShortLinkUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-14
 */
@Service
public class LinkUrlTocServiceImpl implements LinkUrlTocService {
    @Autowired
    private LinkUrlTocStore tocStore;

    @Override
    public void redirect(String shortLink) {
        if (!shortLink.matches(ShortLinkUtil.SHORT_LINK_FORMAT_REGEX)) {
            throw new BizException(BizCodeEnum.SHORT_LINK_FORMAT_ERROR);
            // TODO 跳转到 404 链接失效页面
        }
        LinkUrlToc linkUrlPO = tocStore.getByShortLink(shortLink);
        if (ObjectUtils.isEmpty(linkUrlPO)) {
            // TODO 跳转到 404 链接失效页面
        }
        if (ObjectUtils.isNotEmpty(linkUrlPO.getExpiredTime())
                && linkUrlPO.getExpiredTime().isBefore(LocalDateTime.now())) {
            // TODO 跳转到 404 链接失效页面
        }

        // TODO Kafka将本次行为数据推送到大数据服务

        HttpServletResponse response = HttpServletUtil.getResponse();
        response.setHeader("Location", "https://www.baidu.com");
        response.setStatus(HttpStatus.FOUND.value());
    }
}
