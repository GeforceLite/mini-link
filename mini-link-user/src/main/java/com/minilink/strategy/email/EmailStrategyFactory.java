package com.minilink.strategy.email;

import com.minilink.enums.BusinessCodeEnum;
import com.minilink.exception.BusinessException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-14  14:32
 * @Description: 发送邮件-策略工厂
 * @Version: 1.0
 */
public class EmailStrategyFactory {
    private static final Map<Integer, AbstractEmailStrategy> STRATEGY_MAP = new HashMap<>();

    public static void register(Integer code, AbstractEmailStrategy emailStrategy) {
        STRATEGY_MAP.put(code, emailStrategy);
    }

    public static AbstractEmailStrategy getStrategyHandler(Integer code) {
        AbstractEmailStrategy strategyHandler = STRATEGY_MAP.get(code);
        if (ObjectUtils.isEmpty(strategyHandler)) {
            throw new BusinessException(BusinessCodeEnum.FAIL);
        }
        return strategyHandler;
    }
}
