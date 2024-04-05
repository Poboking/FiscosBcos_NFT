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
    private String ownableContractAddress;

    private String utilsContractAddress;

    private String bcosUserContractAddress;

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
     * @return deployPublicKey
     */
    public String getDeployPublicKey() {
        return this.deployPublicKey;
    }

    /**
     * set field
     *
     * @param deployPublicKey
     */
    public void setDeployPublicKey(String deployPublicKey) {
        this.deployPublicKey = deployPublicKey;
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
     * @return ownableContractAddress
     */
    public String getOwnableContractAddress() {
        return this.ownableContractAddress;
    }

    /**
     * set field
     *
     * @param ownableContractAddress
     */
    public void setOwnableContractAddress(String ownableContractAddress) {
        this.ownableContractAddress = ownableContractAddress;
    }

    /**
     * get field
     *
     * @return utilsContractAddress
     */
    public String getUtilsContractAddress() {
        return this.utilsContractAddress;
    }

    /**
     * set field
     *
     * @param utilsContractAddress
     */
    public void setUtilsContractAddress(String utilsContractAddress) {
        this.utilsContractAddress = utilsContractAddress;
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
