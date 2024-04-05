package org.knight.infrastructure.fisco.service;

import cn.hutool.core.lang.UUID;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.knight.infrastructure.fisco.config.ContractAddressContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/25 23:23
 */
@Component
public class NFTService {

    private final BcosSDK bcosSDK;

    private final Client client;

    private final CryptoKeyPair deployKeyPair;

    @Autowired
    public NFTService(BcosSDK bcosSDK, Client client,@Qualifier("deployCryptoKeyPair") CryptoKeyPair keyPair) {
        this.bcosSDK = bcosSDK;
        this.client = client;
        this.deployKeyPair = keyPair;
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

    public String deployNFTContract() {
        return null;
    }

    // TODO: 2024/4/5 待实现
    public String createNFT(String nftId) {
        BcosNFT bcosNFT = BcosNFT.load(ContractAddressContext.getBcosNFTAddress(), client, deployKeyPair);
//        bcosNFT.createNFT()
        return null;
    }
}
