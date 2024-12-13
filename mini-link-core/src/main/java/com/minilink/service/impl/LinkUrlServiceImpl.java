package com.minilink.service.impl;

import com.minilink.adapter.LinkUrlAdapter;
import com.minilink.enums.BizCodeEnum;
import com.minilink.exception.BizException;
import com.minilink.pojo.dto.LinkUrlSaveDTO;
import com.minilink.pojo.po.LinkUrl;
import com.minilink.service.LinkUrlService;
import com.minilink.store.LinkUrlStore;
import com.minilink.util.HttpServletUtil;
import com.minilink.util.ShortLinkUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
    private final String SHORT_LINK_FORMAT_REGEX = "^\\d+-[a-z0-9A-Z]+$";
    @Autowired
    private LinkUrlStore urlStore;

    @Override
    public void createShortLink(LinkUrlSaveDTO saveDTO) {
        String shortLink = ShortLinkUtil.generate(saveDTO.getLongLink());
        if (!shortLink.matches(SHORT_LINK_FORMAT_REGEX)) {
            throw new BizException(BizCodeEnum.SHORT_LINK_FORMAT_ERROR);
        }
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

    @Override
    public void redirect(String shortLink) throws IOException {
        if (!shortLink.matches(SHORT_LINK_FORMAT_REGEX)) {
            throw new BizException(BizCodeEnum.SHORT_LINK_FORMAT_ERROR);
        }
//        LinkUrl linkUrlPO = urlStore.getByShortLink(shortLink);
//        if (ObjectUtils.isEmpty(linkUrlPO)) {
//            throw new BizException(BizCodeEnum.LONG_LINK_NOT_EXIST);
//        }
//        if (ObjectUtils.isNotEmpty(linkUrlPO.getExpiredTime())
//                && linkUrlPO.getExpiredTime().isBefore(LocalDateTime.now())) {
//            throw new BizException(BizCodeEnum.SHORT_LINK_EXPIRED);
//        }
        HttpServletResponse response = HttpServletUtil.getResponse();
        response.setHeader("Location", "www.baidu.com");
        response.setStatus(HttpStatus.FOUND.value());
        response.sendRedirect("www.baidu.com");
//        response.sendRedirect("www.baidu.com");
    }
}
