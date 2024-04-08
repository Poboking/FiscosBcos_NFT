package org.knight.infrastructure.fisco.service.biz;

import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.knight.infrastructure.fisco.service.BcosNFT;
import org.knight.infrastructure.fisco.service.BcosUser;
import org.knight.infrastructure.fisco.service.Ownable;
import org.knight.infrastructure.fisco.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/4 15:57
 */
@Component
public class DeployService {



    private final Client client;

    private final CryptoKeyPair keyPair;

    @Autowired
    public DeployService( Client client,@Qualifier("deployCryptoKeyPair") CryptoKeyPair keyPair) {
        this.client = client;
        this.keyPair = keyPair;
    }


    /**
     * Deploy BcosNFT contract
     * @return contract address
     */
    public String deployBcosNFTContract() throws ContractException {
        return BcosNFT.deploy(client, keyPair).getContractAddress();
    }

    /**
     * Deploy BcosNFT contract
     * @param owner contract owner
     * @return contract address
     */
    public String deployBcosNFTContract(CryptoKeyPair owner) throws ContractException {
        return BcosNFT.deploy(client, owner).getContractAddress();
    }

    /**
     * Deploy BcosUser contract
     * @return contract address
     */
    public String deployBcosUserContract() throws ContractException {
        return BcosUser.deploy(client, keyPair).getContractAddress();
    }

    /**
     * Deploy BcosUser contract
     * @param owner contract owner
     * @return contract address
     */
    public String deployBcosUserContract(CryptoKeyPair owner) throws ContractException {
        return BcosUser.deploy(client,owner).getContractAddress();
    }

    /**
     * Deploy Utils contract
     * @return contract address
     */
    public String deployUtils() throws ContractException {
        return Utils.deploy(client,keyPair).getContractAddress();
    }

    /**
     * Deploy Utils contract
     * @param owner contract owner
     * @return contract address
     */
    public String deployUtils(CryptoKeyPair owner) throws ContractException {
        return Utils.deploy(client,owner).getContractAddress();
    }

    /**
     * Deploy Ownable contract
     * @return contract address
     */
    public String deployOwnable() throws ContractException {
        return Ownable.deploy(client, keyPair, keyPair.getAddress()).getContractAddress();
    }

    /**
     * Deploy Ownable contract
     * @param owner contract owner
     * @return contract address
     */
    public String deployOwnable(CryptoKeyPair owner) throws ContractException {
        return Ownable.deploy(client, owner, owner.getAddress()).getContractAddress();
    }

    /**
     * Deploy all contracts
     */
    public void deployAll() {
        try {
            deployOwnable();
            deployUtils();
            deployBcosUserContract();
            deployBcosNFTContract();
        } catch (ContractException e) {
            e.printStackTrace();
        }
    }
}
