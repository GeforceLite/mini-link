<div align="center">
    <a href="https://github.com/Binx98/mini-link"><img src="https://img.shields.io/badge/后端-项目地址-yellow.svg?style=plasticr"></a>
    <a href="" target="_blank">
    <br>
    <h3>麻烦您帮忙点个Star⭐</h3>
</div>

# ✨系统介绍

短链接系统用于将长链接压缩为短链接，访问用户可以通过短链接快速分享、访问源地址。支持多种使用场景，包括社交媒体分享、营销活动跟踪、数据分析等。
<br>
系统基于SpringBoot 3.2 + SpringCloud Alibaba 2023等技术实现的高并发、高性能、海量数据短链接平台
<br>
通过Flink实现海量PV UV等运营数据实时计算、清洗、聚合、存储到OLAP数据库，提供多维度数据统计面板功能
<br>

短链接系统主要使用场景：

1. 在线广告
2. 社交媒体
3. 电商平台
4. ......

![img.png](docs/images/短信.png)

<br>

短链接系统商业用途：

1. 缩短长字符链接，简洁美观
2. 降低短信字数，降低短信费用成本
3. 降低图形二维码生成复杂度
4. 隐藏链接参数，提高系统安全性
5. 方便统计流量、点击率等运营数据
6. ......

# 🚀项目架构

## 模块划分

```
mini-link
├── mini-link-common   ---   公共通用
├── mini-link-core     ---   短链接模块
├── mini-link-data     ---   大数据统计看板
├── mini-link-flink    ---   大数据实时计算
├── mini-link-gateway  ---   API网关
└── mini-link-user     ---   用户模块
```

## 服务端

| 技术                   | 说明        | 官网                                                                           |
|----------------------|-----------|------------------------------------------------------------------------------|
| JDK17                | Java开发工具  | https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html |
| Spring Boot          | Spring脚手架 | https://spring.io/projects/spring-boot                                       |
| Spring Cloud Alibaba | 微服务框架     | https://github.com/alibaba/spring-cloud-alibaba                              |
| MySQL                | 关系型数据库    | https://github.com/mysqljs/mysql                                             |
| Redis                | KV数据库     | https://github.com/redis/redis                                               |
| MyBatis-Plus         | ORM框架     | https://baomidou.com/                                                        |
| XXL-JOB              | 定时调度      | https://github.com/xuxueli/xxl-job                                           |
| Kafka                | 消息队列      | https://github.com/apache/kafka                                              |
| MinIO                | 文件存储      | https://github.com/minio/minio                                               |
| ShardingSphere       | 分库分表      | https://github.com/apache/shardingsphere                                     |
| Lombok               | JavaBean库 | https://github.com/projectlombok/lombok                                      |
| Flink                | 开源流处理框架   | https://github.com/apache/flink                                              |
| ClickHouse           | OLAP数据仓库  | https://github.com/ClickHouse/ClickHouse                                     |

## 前端

| 技术          | 说明       | 官网                              |
|-------------|----------|---------------------------------|
| Vue         | 前端框架     | https://vuejs.org/              |
| Vue-router  | 路由框架     | https://router.vuejs.org/       |
| Vuex        | 全局状态管理框架 | https://vuex.vuejs.org/         |
| ElementPlus | 前端UI框架   | https://element-plus.org/       |
| TypeScript  | 微软开源语言   | https://www.typescriptlang.org/ |
| Axios       | HTTP请求库  | https://github.com/axios/axios  |
| Echarts     | 数据图表     | https://v-charts.js.org/        |

## 运维

| 技术            | 说明     | 官网                                          |
|---------------|--------|---------------------------------------------|
| Docker        | 镜像容器   | https://www.docker.com/                     |
| Kubernetes    | 容器编排工具 | https://kubernetes.io/                      |
| Ingress-Nginx | 负载均衡器  | https://github.com/kubernetes/ingress-nginx |
| Gitlab        | 代码仓库   | https://github.com/gitlabhq/gitlabhq        |
| Harbor        | 镜像仓库   | https://github.com/axios/axios              |
| Jenkins       | 自动化发布  | https://github.com/jenkinsci/jenkins        |
| Prometheus    | 数据采集   | https://github.com/prometheus/prometheus    |
| Grafana       | 数据展示   | https://github.com/grafana/grafana          |

# 🎉贡献名单

<a href="https://github.com/Binx98/QuickChat/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Binx98/mini-link" />
</a>

| 姓名  |            Github             |          公司          |
|:---:|:-----------------------------:|:--------------------:|
| 徐志斌 |   https://github.com/Binx98   | PARAVERSE TECHNOLOGY |
| Joy | https://github.com/Joydevelop |          保密          |
| 刘贤壮 |      https://github.com/      |        江苏中车数字        |
