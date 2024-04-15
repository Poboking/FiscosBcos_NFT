package org.knight.infrastructure.fisco.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/29 11:33
 */
@Component("bcosProperties")
@ConfigurationProperties(prefix = "fisco")
public class BcosProperties {

    @Value("${fisco.cert-path}")
    private String certPath;
    @Value("${fisco.deploy-private-key}")
    private String deployPrivateKey;
    @Value("${fisco.deploy-address}")
    private String deployAddress;
    @Value("${fisco.bcos-user-contract-address}")
    private String bcosUserContractAddress;
    @Value("${fisco.bcos-nft-contract-address}")
    private String bcosNFTContractAddress;


    public String getCertPath() {
        return this.certPath;
    }


    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public void setDeployAddress(String deployAddress) {
        this.deployAddress = deployAddress;
    }

    public String getDeployAddress() {
        return this.deployAddress;
    }


    public String getDeployPrivateKey() {
        return this.deployPrivateKey;
    }


    public void setDeployPrivateKey(String deployPrivateKey) {
        this.deployPrivateKey = deployPrivateKey;
    }


    public String getBcosUserContractAddress() {
        return this.bcosUserContractAddress;
    }


    public void setBcosUserContractAddress(String bcosUserContractAddress) {
        this.bcosUserContractAddress = bcosUserContractAddress;
    }


    public String getBcosNFTContractAddress() {
        return this.bcosNFTContractAddress;
    }


    public void setBcosNFTContractAddress(String bcosNFTContractAddress) {
        this.bcosNFTContractAddress = bcosNFTContractAddress;
    }
}
