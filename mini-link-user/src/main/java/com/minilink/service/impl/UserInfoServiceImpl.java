package com.minilink.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.minilink.adapter.UserAdapter;
import com.minilink.pojo.po.LinkUser;
import com.minilink.pojo.vo.UserVO;
import com.minilink.service.UserInfoService;
import com.minilink.store.LinkUserStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-06
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private LinkUserStore userStore;

    @Override
    public UserVO getUserInfo(Long accountId) {
        LinkUser userPO = userStore.getByAccountId(accountId);
        if (ObjectUtils.isEmpty(userPO)) {
            return null;
        }
        return UserAdapter.buildUserVO(userPO);
    }
}
