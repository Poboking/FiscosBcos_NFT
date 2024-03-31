package org.knight.infrastructure.fisco.service;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 15:09
 */
@Component
public class BlockAddressService {
    private final BcosSDK bcosSDK;

    private final Client client;

    @Autowired
    public BlockAddressService(BcosSDK bcosSDK, Client client) {
        this.bcosSDK = bcosSDK;
        this.client = client;
    }

    public String getBlockAddress() {
        return client.getCryptoSuite().createKeyPair().getAddress();
    }
}
