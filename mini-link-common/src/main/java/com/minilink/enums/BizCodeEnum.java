package com.minilink.enums;

import lombok.Getter;

/**
 * @Author 徐志斌
 * @Date: 2024/12/4 10:53
 * @Version 1.0
 * @Description: 状态码枚举
 */
public enum BizCodeEnum {
    /**
     * 通用操作码
     */
    OPS_REPEAT(110001, "重复操作"),
    OPS_NETWORK_ADDRESS_ERROR(110002, "⽹络地址错误"),

    /**
     * 短链分组
     */
    GROUP_REPEAT(23001, "分组名重复"),
    GROUP_OPERATION_FAIL(23503, "分组名操作失败"),
    GROUP_NOT_EXIST(23404, "分组不存在"),

    /**
     * 验证码
     */
    CODE_TO_ERROR(240001, "接收号码不合规"),
    CODE_LIMITED(240002, "验证码发送过快"),
    CODE_ERROR(240003, "验证码错误"),
    CODE_CAPTCHA_ERROR(240101, "图形验证码错误"),

    /**
     * 账号
     */
    ACCOUNT_REPEAT(250001, "账号已经存在"),
    ACCOUNT_UNREGISTER(250002, "账号不存在"),
    ACCOUNT_PWD_ERROR(250003, "账号或密码错误"),
    ACCOUNT_NO_LOGIN(250004, "账号未登录"),

    /**
     * 短链
     */
    SHORT_LINK_NOT_EXIST(260404, "短链不存在"),

    /**
     * 支付
     */
    PAY_ORDER_FAIL(300001, "创建⽀付订单失败"),
    PAY_ORDER_CALLBACK_SIGN_FAIL(300002, "⽀付订单回调验 证签失败"),
    PAY_ORDER_CALLBACK_NOT_SUCCESS(300003, "⽀付宝回调 更新订单失败"),
    PAY_ORDER_NOT_EXIST(300005, "订单不存在"),
    PAY_ORDER_STATE_ERROR(300006, "订单状态不正常"),
    PAY_ORDER_PAY_TIMEOUT(300007, "订单⽀付超时"),

    /**
     * 流控操作
     */
    CONTROL_FLOW(500101, "限流控制"),
    CONTROL_DEGRADE(500201, "降级控制"),
    CONTROL_AUTH(500301, "认证控制"),

    /**
     * 文件相关
     */
    FILE_UPLOAD_USER_IMG_FAIL(700101, "⽤户头像⽂件上传失败");


    @Getter
    private String msg;
    @Getter
    private int code;

    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}