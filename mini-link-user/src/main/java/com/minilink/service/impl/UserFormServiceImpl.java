package com.minilink.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.minilink.constant.RedisConstant;
import com.minilink.enums.BizCodeEnum;
import com.minilink.exception.BizException;
import com.minilink.pojo.dto.RegisterDTO;
import com.minilink.pojo.po.MiniLinkUser;
import com.minilink.service.UserFormService;
import com.minilink.store.MiniLinkUserStore;
import com.minilink.util.Md5Util;
import com.minilink.util.RandomCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
    private RedisTemplate redisTemplate;
    @Autowired
    private MiniLinkUserStore userStore;

    @Override
    public void register(RegisterDTO registerDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // 校验两次密码输入是否一致
        if (!registerDTO.getPassword1().equals(registerDTO.getPassword2())) {
            throw new BizException(BizCodeEnum.PASSWORD_NO_EQUAL);
        }

        // 校验邮箱验证码是否正确
        String emailCodeKey = RedisConstant.EMAIL_CODE_KEY + registerDTO.getEmail();
        String emailCode = (String) redisTemplate.opsForValue().get(emailCodeKey);
        if (!registerDTO.getEmailCode().equalsIgnoreCase(emailCode)) {
            throw new BizException(BizCodeEnum.CODE_EMAIL_ERROR);
        }

        // 校验邮箱是否重复注册
        MiniLinkUser userPO = userStore.getByEmail(registerDTO.getEmail());
        if (ObjectUtils.isNotEmpty(userPO)) {
            throw new BizException(BizCodeEnum.ACCOUNT_REPEAT);
        }

        // 保存账户信息
        String salt = "$1$" + RandomCodeUtil.generate(8, 3);
        String encryptPassword = Md5Util.encrypt(registerDTO.getPassword1() + salt);
        userPO = new MiniLinkUser();
        userPO.setAvatar("Default Avatar");
        userPO.setEmail(registerDTO.getEmail());
        userPO.setSalt(salt);
        userPO.setPassword(encryptPassword);
        userStore.saveUserInfo(userPO);
        redisTemplate.delete(emailCodeKey);
    }

    @Override
    public Map<String, Object> login(RegisterDTO registerDTO) {
        return null;
    }
}
