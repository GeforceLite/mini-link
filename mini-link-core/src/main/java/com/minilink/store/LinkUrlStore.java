package com.minilink.store;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minilink.pojo.po.LinkUrl;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-09
 */
public interface LinkUrlStore extends IService<LinkUrl> {
    /**
     * 保存链接记录
     *
     * @param linkUrl 链接信息
     * @return 执行结果
     */
    Boolean saveLink(LinkUrl linkUrl);

    /**
     * 根据短链接查询信息
     *
     * @param shortLink 短链接
     * @return 连接信息
     */
    LinkUrl getByShortLink(String shortLink);
}
