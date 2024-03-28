package org.knight.infrastructure.fisco.config;



import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.config.ConfigOption;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.config.model.ConfigProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/28 9:48
 */
@Configuration
public class BcosConfig {

    @Bean
    public ConfigProperty configProperty() {
        ConfigProperty configProperty = new ConfigProperty();
        Map<String, Object> network = new HashMap<>();
        List<String> peersList = List.of("43.143.86.190:20200", "43.143.86.190:20201");
        Map<String, Object> cryptoMaterial = new HashMap<>();
        network.put("peers", peersList);
        cryptoMaterial.put("certPath", "conf");
        cryptoMaterial.put("maxBlockingQueueSize","102400");
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
}
