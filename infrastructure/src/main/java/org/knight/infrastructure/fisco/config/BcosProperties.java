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
    @Value("${fisco.bcos-user-contract-address}")
    private String bcosUserContractAddress;
    @Value("${fisco.bcos-nft-contract-address}")
    private String bcosNFTContractAddress;

    /**
     * get field
     *
     * @return certPath
     */
    public String getCertPath() {
        return this.certPath;
    }

    /**
     * set field
     *
     * @param certPath
     */
    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    /**
     * get field
     *
     * @return deployPrivateKey
     */
    public String getDeployPrivateKey() {
        return this.deployPrivateKey;
    }

    /**
     * set field
     *
     * @param deployPrivateKey
     */
    public void setDeployPrivateKey(String deployPrivateKey) {
        this.deployPrivateKey = deployPrivateKey;
    }


    /**
     * get field
     *
     * @return bcosUserContractAddress
     */
    public String getBcosUserContractAddress() {
        return this.bcosUserContractAddress;
    }

    /**
     * set field
     *
     * @param bcosUserContractAddress
     */
    public void setBcosUserContractAddress(String bcosUserContractAddress) {
        this.bcosUserContractAddress = bcosUserContractAddress;
    }

    /**
     * get field
     *
     * @return bcosNFTContractAddress
     */
    public String getBcosNFTContractAddress() {
        return this.bcosNFTContractAddress;
    }

    /**
     * set field
     *
     * @param bcosNFTContractAddress
     */
    public void setBcosNFTContractAddress(String bcosNFTContractAddress) {
        this.bcosNFTContractAddress = bcosNFTContractAddress;
    }
}
