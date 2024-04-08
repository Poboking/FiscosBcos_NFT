package org.knight.infrastructure.fisco.service.biz;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.knight.infrastructure.fisco.service.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/2 14:10
 */
@Component
public class BcosTestService {
    private final BcosSDK bcosSDK;
    private final Client client;


    @Autowired
    public BcosTestService(BcosSDK bcosSDK, Client client) {
        this.bcosSDK = bcosSDK;
        this.client = client;
    }

    public String getPublicAndPrivateKey(){
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        return cryptoKeyPair.getHexPublicKey() + " " + cryptoKeyPair.getHexPrivateKey();
    }


    public CryptoKeyPair getRandomCreateKeyPair(){
        return client.getCryptoSuite().createKeyPair();
    }

    public CryptoKeyPair getCreateKeyPairByKey(){
        String privetKey = "1ffd8dd75f2278dfd9171ac5194720ec9a1a1f76b661d5e37ce5bcabfdf26975";
        String publicKey = "2136414ec16e49a82adf646e7b7d6a150602b0d3887aa905bdd4430fecfa71014c9f3e49548260baf2b58b1f8dea79663be416caac017983971d7d6f50705f4d";
        return client.getCryptoSuite().getKeyPairFactory().createKeyPair(privetKey);
    }

    public String deploy() throws ContractException {
        HelloWorld helloWorld = HelloWorld.deploy(client, client.getCryptoSuite().createKeyPair());
        System.err.println("Hello world deploy" + helloWorld.getContractAddress());
        return helloWorld.getContractAddress();
    }

    public String deploy(CryptoKeyPair owner) throws ContractException {
        HelloWorld helloWorld = HelloWorld.deploy(client, owner);
        System.err.println("Hello world deploy" + helloWorld.getContractAddress());
        return helloWorld.getContractAddress();
    }

    public void getByAddress(String address,  CryptoKeyPair owner) throws ContractException {
        HelloWorld helloWorld = HelloWorld.load(address, client, owner);
        System.err.println("hello get: " + helloWorld.get());
    }

    public void setByAddress(String address, String  name, CryptoKeyPair owner) throws ContractException {
        HelloWorld helloWorld = HelloWorld.load(address, client, owner);
        helloWorld.set(name);
        System.err.println("hello set: " + helloWorld.get());
    }

    public void get() throws ContractException {
        HelloWorld helloWorld = HelloWorld.deploy(client, client.getCryptoSuite().createKeyPair());
        System.err.println("hello get:" + helloWorld.get());
    }

    public static String convert(String address) throws ContractException {
        return "0x" + address;
    }

    public void getBlockNumber(){
        System.err.println(client.getBlockNumber().getBlockNumber());
    }
}
