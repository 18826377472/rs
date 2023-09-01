package com.cy.rs.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @description: excel导出的路径位置
 * @author: xz
 */
@Component
@ConfigurationProperties(prefix="export")
public class Excel {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

