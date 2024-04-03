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
    private String deployPublicKey;
    private String deployPrivateKey;

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public String getDeployPublicKey() {
        return deployPublicKey;
    }

    public void setDeployPublicKey(String deployPublicKey) {
        this.deployPublicKey = deployPublicKey;
    }

    public String getDeployPrivateKey() {
        return deployPrivateKey;
    }

    public void setDeployPrivateKey(String deployPrivateKey) {
        this.deployPrivateKey = deployPrivateKey;
    }
}
