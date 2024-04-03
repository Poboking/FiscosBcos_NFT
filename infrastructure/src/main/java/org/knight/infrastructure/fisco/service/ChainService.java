package org.knight.infrastructure.fisco.service;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 22:38
 */
@Component
public class ChainService {
    private final BcosSDK bcosSDK;

    private final Client client;

    @Autowired
    public ChainService(BcosSDK bcosSDK, Client client) {
        this.bcosSDK = bcosSDK;
        this.client = client;
    }

    // TODO: 2024/3/31 22:33 待实现
    public String generateBlockTransaction() {
        return client.getCryptoSuite().createKeyPair().getAddress();
    }

    public String getBlockRandomAddress() {
        return client.getCryptoSuite().createKeyPair().getAddress();
    }

    public String getRandomPrivateKey(){
        return client.getCryptoSuite().createKeyPair().getHexPrivateKey();
    }
}
