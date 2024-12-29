package com.minilink.service;

import com.minilink.pojo.dto.LoginDTO;
import com.minilink.pojo.dto.RegisterDTO;
import com.minilink.util.resp.R;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-06
 */
public interface UserFormService {
    /**
     * 注册账号
     *
     * @param registerDTO 注册表单参数
     */
    void register(RegisterDTO registerDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 登录系统
     *
     * @param loginDTO 登录表单参数
     * @return 登录账户相关信息
     */
    Map<String, Object> login(LoginDTO loginDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException;


    /**
     * 密码更新
     *
     * @param accountId 账号
     * @param oldPwd 新密码
     * @param newPwd 旧密码
     * @return 更新结果
     */
    R updateUserPwd(Long accountId, String oldPwd, String newPwd) throws Exception;

}
