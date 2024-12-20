package com.minilink.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.minilink.pojo.po.LinkUrlToc;
import com.minilink.service.LinkUrlTocService;
import com.minilink.store.LinkUrlTocStore;
import com.minilink.util.HttpServletUtil;
import com.minilink.util.LinkUrlUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
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
    private LinkUrlTocStore urlTocStore;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void redirect(String shortLink) {
        HttpServletResponse response = HttpServletUtil.getResponse();
        if (!shortLink.matches(LinkUrlUtil.SHORT_LINK_FORMAT_REGEX)) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }

        LinkUrlToc linkUrlPO = urlTocStore.getByShortLinkCode(shortLink);
        if (ObjectUtils.isEmpty(linkUrlPO)) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }

        if (ObjectUtils.isNotEmpty(linkUrlPO.getExpiredTime())
                && linkUrlPO.getExpiredTime().isBefore(LocalDateTime.now())) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }

        // TODO Kafka 推送用户点击访问行为数据推送到 Flink 进行实时计算
        kafkaTemplate.send("test-topic", "测试消息");

        // 重定向跳转到目标链接
        response.setHeader("Location", linkUrlPO.getLongLink());
        response.setStatus(HttpStatus.FOUND.value());
    }
}
