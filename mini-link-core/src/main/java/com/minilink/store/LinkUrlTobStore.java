package com.minilink.store;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.minilink.pojo.po.LinkUrlTob;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-09
 */
public interface LinkUrlTobStore extends IService<LinkUrlTob> {
    /**
     * 保存链接记录
     *
     * @param linkUrl 链接信息
     * @return 执行结果
     */
    Boolean saveLink(LinkUrlTob linkUrl);

    /**
     * 分页查询短链接列表
     *
     * @param accountId 账号
     * @param groupId   分组id
     * @param current   当前页
     * @param size      每页条数
     * @return 分页结果
     */
    Page<LinkUrlTob> getPage(Long accountId, Long groupId, Integer current, Integer size);
}
