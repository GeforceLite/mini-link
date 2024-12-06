package com.minilink.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minilink.mapper.MiniLinkUserMapper;
import com.minilink.pojo.po.MiniLinkUser;
import com.minilink.store.MiniLinkUserStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-06
 */
@Service
public class MiniLinkUserStoreImpl extends ServiceImpl<MiniLinkUserMapper, MiniLinkUser> implements MiniLinkUserStore {

}
