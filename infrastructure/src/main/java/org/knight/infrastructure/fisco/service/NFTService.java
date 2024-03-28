package org.knight.infrastructure.fisco.service;

import cn.hutool.core.lang.UUID;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/25 23:23
 */
@Service
public class NFTService {

    private final BcosSDK bcosSDK;

    private final Client client;

    @Autowired
    public NFTService(BcosSDK bcosSDK, Client client) {
        this.bcosSDK = bcosSDK;
        this.client = client;
    }

    public String getNFTHash() {
        CryptoSuite cryptoSuite = client.getCryptoSuite();
        CryptoKeyPair keyPair = cryptoSuite.createKeyPair();
        return keyPair.getAddress();
    }

    public static String getNFTHash(String nftId) {
        return UUID.randomUUID().toString();
    }

    public static String getNFTUniqueId(String nftId) {
        return UUID.randomUUID().toString();
    }
}
