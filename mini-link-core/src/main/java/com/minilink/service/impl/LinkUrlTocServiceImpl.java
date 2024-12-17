package com.minilink.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.minilink.pojo.po.LinkUrlToc;
import com.minilink.service.LinkUrlTocService;
import com.minilink.store.LinkUrlTocStore;
import com.minilink.util.HttpServletUtil;
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
        HttpServletResponse response = HttpServletUtil.getResponse();
        LinkUrlToc linkUrlPO = tocStore.getByShortLink(shortLink);
        if (ObjectUtils.isEmpty(linkUrlPO)) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        if (ObjectUtils.isNotEmpty(linkUrlPO.getExpiredTime())
                && linkUrlPO.getExpiredTime().isBefore(LocalDateTime.now())) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } else {
            // TODO Kafka将本次行为数据推送到大数据服务
            response.setHeader("Location", linkUrlPO.getLongLink());
            response.setStatus(HttpStatus.FOUND.value());
        }
    }
}
