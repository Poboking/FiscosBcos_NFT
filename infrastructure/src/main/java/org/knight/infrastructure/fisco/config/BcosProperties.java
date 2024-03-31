package org.knight.infrastructure.fisco.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/29 11:33
 */
@Component
@ConfigurationProperties(prefix = "fisco")
public class BcosProperties {
    private String certPath;

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }
}
