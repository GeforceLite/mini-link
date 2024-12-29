package com.minilink.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.minilink.adapter.UserAdapter;
import com.minilink.constant.RedisConstant;
import com.minilink.enums.BusinessCodeEnum;
import com.minilink.exception.BusinessException;
import com.minilink.pojo.dto.LoginDTO;
import com.minilink.pojo.dto.RegisterDTO;
import com.minilink.pojo.po.LinkUser;
import com.minilink.service.UserAssistService;
import com.minilink.service.UserFormService;
import com.minilink.store.LinkUserStore;
import com.minilink.util.*;
import com.minilink.util.properties.PasswordProperties;
import com.minilink.util.resp.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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
    private LinkUserStore userStore;

    @Autowired
    private UserAssistService assistService;

    private static String salt;


    /**
     * 读取配置密码盐
     *
     * @param applicationContext
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        PasswordProperties properties = applicationContext.getBean(PasswordProperties.class);
        salt = properties.getSalt();
    }


    @Override
    public void register(RegisterDTO registerDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String email = registerDTO.getEmail();
        if (!registerDTO.getPassword1().equals(registerDTO.getPassword2())) {
            throw new BusinessException(BusinessCodeEnum.PASSWORD_NO_EQUAL);
        }
        String emailCodeKey = RedisConstant.EMAIL_CODE_KEY + email;
        String emailCode = (String) redisTemplate.opsForValue().get(emailCodeKey);
        if (!registerDTO.getEmailCode().equalsIgnoreCase(emailCode)) {
            throw new BusinessException(BusinessCodeEnum.CODE_EMAIL_ERROR);
        }
        LinkUser userPO = userStore.getByEmail(email);
        if (ObjectUtils.isNotEmpty(userPO)) {
            throw new BusinessException(BusinessCodeEnum.ACCOUNT_REPEAT);
        }
        String salt = "$1$" + RandomUtil.generate(8, 3);
        String password = EncryptUtil.md5(registerDTO.getPassword1() + salt);
        long snowFlakeId = SnowFlakeUtil.nextId();
        userPO = UserAdapter.buildUserPO(snowFlakeId, "用户" + snowFlakeId, "", email, password, salt);
        userStore.saveUser(userPO);
    }

    @Override
    public Map<String, Object> login(LoginDTO loginDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String captchaKey = assistService.getCaptchaKey();
        String captchaCode = (String) redisTemplate.opsForValue().get(captchaKey);
        if (!loginDTO.getCaptchaCode().equalsIgnoreCase(captchaCode)) {
            throw new BusinessException(BusinessCodeEnum.CODE_CAPTCHA_ERROR);
        }
        LinkUser userPO = userStore.getByEmail(loginDTO.getEmail());
        if (ObjectUtils.isEmpty(userPO)) {
            throw new BusinessException(BusinessCodeEnum.ACCOUNT_UNREGISTER);
        }
        String token = JwtUtil.generate(userPO.getAccountId(), userPO.getEmail(), userPO.getNickName(), userPO.getAvatar());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        resultMap.put("user", UserAdapter.buildUserVO(userPO));
        return resultMap;
    }

    @Override
    public R updateUserPwd(Long accountId, String oldPwd, String newPwd) throws Exception {

        // 新旧密码一致
        if (oldPwd.equals(newPwd)) {
            return R.out(BusinessCodeEnum.PASSWORD_NO_EQUAL);
        }

        // 新旧密码加盐
        String oldPwdWithSalt = oldPwd + salt;
        String newPwdWithSalt = newPwd + salt;
        String encryptOldPwdWithSalt = AESCipherUtil.encrypt(oldPwdWithSalt);
        String encryptNewPwdWithSalt = AESCipherUtil.encrypt(newPwdWithSalt);

        LinkUser userPO = userStore.getByAccountId(accountId);

        // 新密码与DB旧密码一致
        if (encryptOldPwdWithSalt.equals(userPO.getPassword())) {
            return R.out(BusinessCodeEnum.PASSWORD_NO_EQUAL);
        }

        // 更新
        boolean result = userStore.updatePwd(accountId, encryptNewPwdWithSalt);
        // 更新成功
        if (result) {
            return R.out(BusinessCodeEnum.SUCCESS);
        } else {
            // 更新失败，日志输出
            log.error("Password update error , Account Id is : {}", accountId);
            return R.out(BusinessCodeEnum.FAIL);
        }
    }


}
