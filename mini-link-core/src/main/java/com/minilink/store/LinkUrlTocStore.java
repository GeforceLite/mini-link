package com.minilink.store;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minilink.pojo.po.LinkUrlToc;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-14
 */
public interface LinkUrlTocStore extends IService<LinkUrlToc> {
    /**
     * 保存链接记录
     *
     * @param linkUrl 链接信息
     * @return 执行结果
     */
    Boolean saveLink(LinkUrlToc linkUrl);

    /**
     * 根据短链接查询记录
     *
     * @param shortLink 短链接
     * @return 链接记录
     */
    LinkUrlToc getByShortLink(String shortLink);
}
