package com.minilink.sharding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author 徐志斌
 * @Date: 2024/12/15 18:15
 * @Version 1.0
 * @Description: 库、表位元素：用于判断分库分表权重
 */
@Getter
@Setter
@AllArgsConstructor
public class ShardingElement {
    /**
     * 库号 | 表号
     */
    private String name;

    /**
     * 权重
     */
    private Integer weight;
}
