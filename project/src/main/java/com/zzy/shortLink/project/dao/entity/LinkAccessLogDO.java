package com.zzy.shortLink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zzy.shortLink.project.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 访问日志实体
 */
@TableName("t_link_access_logs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LinkAccessLogDO extends BaseDO {
    /**
     * id
     */
    private Long id;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 用户信息
     */
    private String user;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * ip
     */
    private String ip;
}