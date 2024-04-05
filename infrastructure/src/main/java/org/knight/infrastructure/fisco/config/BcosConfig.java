package org.knight.infrastructure.fisco.config;


import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.config.ConfigOption;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.config.model.ConfigProperty;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/28 9:48
 */
@Configuration
@EnableConfigurationProperties(BcosProperties.class)
public class BcosConfig {
    private final BcosProperties bcosProperties;

    @Autowired
    public BcosConfig(BcosProperties bcosProperties) {
        this.bcosProperties = bcosProperties;
    }

    @Bean
    public ConfigProperty configProperty() {
        ConfigProperty configProperty = new ConfigProperty();
        // 节点
        Map<String, Object> network = new HashMap<>();
        List<String> peersList = List.of("43.143.86.190:20200","43.143.86.190:20201");
        Map<String, Object> cryptoMaterial = new HashMap<>();
        network.put("peers", peersList);
        // 证书路径 Windows 转 Linux 系统下, 需要修改为"/", 或绝对路径"/home/ubuntu/conf"
        if (!bcosProperties.getCertPath().isEmpty() && bcosProperties.getCertPath()!=null){
            cryptoMaterial.put("certPath", bcosProperties.getCertPath());
        }else {
            // 默认路径
            cryptoMaterial.put("certPath", "conf");
        }
        // 最大阻塞队列大小
        cryptoMaterial.put("maxBlockingQueueSize","10100");
        configProperty.setNetwork(network);
        configProperty.setCryptoMaterial(cryptoMaterial);
        return configProperty;
    }

    @Bean
    public ConfigOption configOption(ConfigProperty configProperty) throws ConfigException {
        return new ConfigOption(configProperty);
    }

    @Bean
    public BcosSDK bcosSDK(ConfigOption configOption) throws ConfigException {
        return new BcosSDK(configOption);
    }

    @Bean
    public Client client(BcosSDK bcosSDK) throws ConfigException {
        return bcosSDK.getClient(Integer.valueOf(1));
    }

    @Bean(name = "deployCryptoKeyPair")
    public CryptoKeyPair cryptoKeyPair(Client client) {
        String defaultPrivateKey = "1ffd8dd75f2278dfd9171ac5194720ec9a1a1f76b661d5e37ce5bcabfdf26975";
        if (!bcosProperties.getDeployPrivateKey().isEmpty() && bcosProperties.getDeployPrivateKey()!=null){
            //配置部署账号
            return client.getCryptoSuite().getKeyPairFactory().createKeyPair(bcosProperties.getDeployPrivateKey());
        }else {
            //默认部署账号
            return client.getCryptoSuite().getCryptoKeyPair().createKeyPair(defaultPrivateKey);
        }
    }

    @PostConstruct
    public void contractAddressMap() {
        // 部署合约地址 - 初始化
        ContractAddressContext.setOwnableAddress(bcosProperties.getOwnableContractAddress());
        ContractAddressContext.setUtilsAddress(bcosProperties.getUtilsContractAddress());
        ContractAddressContext.setBcosUserAddress(bcosProperties.getBcosUserContractAddress());
        ContractAddressContext.setBcosNFTAddress(bcosProperties.getBcosNFTContractAddress());
    }
}
