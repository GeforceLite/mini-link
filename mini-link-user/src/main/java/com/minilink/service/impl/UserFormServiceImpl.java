package com.minilink.service.impl;

import com.minilink.pojo.dto.RegisterDTO;
import com.minilink.service.UserFormService;
import com.minilink.util.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

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
public class UserFormServiceImpl implements UserFormService {
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void register(RegisterDTO registerDTO) {
    }

    @Override
    public Map<String, Object> login(RegisterDTO registerDTO) {
        return null;
    }
}
