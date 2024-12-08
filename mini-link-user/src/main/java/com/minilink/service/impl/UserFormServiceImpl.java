package com.minilink.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.minilink.adapter.UserAdapter;
import com.minilink.constant.RedisConstant;
import com.minilink.enums.BizCodeEnum;
import com.minilink.exception.BizException;
import com.minilink.pojo.dto.LoginDTO;
import com.minilink.pojo.dto.RegisterDTO;
import com.minilink.pojo.po.MiniLinkUser;
import com.minilink.service.UserAssistService;
import com.minilink.service.UserFormService;
import com.minilink.store.MiniLinkUserStore;
import com.minilink.util.JwtUtil;
import com.minilink.util.Md5Util;
import com.minilink.util.RandomCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
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
    @Autowired
    private UserAssistService assistService;

    @Override
    public void register(RegisterDTO registerDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String email = registerDTO.getEmail();
        if (!registerDTO.getPassword1().equals(registerDTO.getPassword2())) {
            throw new BizException(BizCodeEnum.PASSWORD_NO_EQUAL);
        }
        String emailCodeKey = RedisConstant.EMAIL_CODE_KEY + email;
        String emailCode = (String) redisTemplate.opsForValue().get(emailCodeKey);
        if (!registerDTO.getEmailCode().equalsIgnoreCase(emailCode)) {
            throw new BizException(BizCodeEnum.CODE_EMAIL_ERROR);
        }
        MiniLinkUser userPO = userStore.getByEmail(email);
        if (ObjectUtils.isNotEmpty(userPO)) {
            throw new BizException(BizCodeEnum.ACCOUNT_REPEAT);
        }
        String salt = "$1$" + RandomCodeUtil.generate(8, 3);
        String password = Md5Util.encrypt(registerDTO.getPassword1() + salt);
        userPO = UserAdapter.buildUserPO(
                "用户" + RandomCodeUtil.generate(12, 1),
                "",
                email,
                password,
                salt
        );
        userStore.saveUser(userPO);
    }

    @Override
    public Map<String, Object> login(LoginDTO loginDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String captchaKey = assistService.getCaptchaKey();
        String captchaCode = (String) redisTemplate.opsForValue().get(captchaKey);
        if (!loginDTO.getCaptchaCode().equalsIgnoreCase(captchaCode)) {
            throw new BizException(BizCodeEnum.CODE_CAPTCHA_ERROR);
        }
        MiniLinkUser userPO = userStore.getByEmail(loginDTO.getEmail());
        if (ObjectUtils.isEmpty(userPO)) {
            throw new BizException(BizCodeEnum.ACCOUNT_UNREGISTER);
        }
        userPO.setPassword(null);
        userPO.setSalt(null);
        String token = JwtUtil.generate(userPO.getEmail(), userPO.getNickName(), userPO.getAvatar());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        resultMap.put("user", userPO);
        return resultMap;
    }
}
