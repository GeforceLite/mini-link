package com.minilink.service.impl;

import com.minilink.enums.BizCodeEnum;
import com.minilink.exception.BizException;
import com.minilink.service.LinkUrlTocService;
import com.minilink.util.HttpServletUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
    private final String SHORT_LINK_FORMAT_REGEX = "^\\d+-\\d+-[a-z0-9A-Z]+$";

    @Override
    public void redirect(String shortLink) throws IOException {
        if (!shortLink.matches(SHORT_LINK_FORMAT_REGEX)) {
            throw new BizException(BizCodeEnum.SHORT_LINK_FORMAT_ERROR);
        }
//        LinkUrlTob linkUrlPO = urlStore.getByShortLink(shortLink);
//        if (ObjectUtils.isEmpty(linkUrlPO)) {
//            throw new BizException(BizCodeEnum.LONG_LINK_NOT_EXIST);
//        }
//        if (ObjectUtils.isNotEmpty(linkUrlPO.getExpiredTime())
//                && linkUrlPO.getExpiredTime().isBefore(LocalDateTime.now())) {
//            throw new BizException(BizCodeEnum.SHORT_LINK_EXPIRED);
//        }
        HttpServletResponse response = HttpServletUtil.getResponse();
        response.setHeader("Location", "https://www.baidu.com");
        response.setStatus(HttpStatus.FOUND.value());
//        response.sendRedirect("www.baidu.com");
//        response.sendRedirect("www.baidu.com");
    }
}
