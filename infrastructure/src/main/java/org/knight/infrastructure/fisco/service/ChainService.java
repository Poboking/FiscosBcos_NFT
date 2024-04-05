package org.knight.infrastructure.fisco.service;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.knight.infrastructure.dao.domain.CollectionEntity;
import org.knight.infrastructure.dao.domain.MemberEntity;
import org.knight.infrastructure.dao.domain.MemberHoldCollectionEntity;
import org.knight.infrastructure.exception.BlockChainException;
import org.knight.infrastructure.fisco.config.ContractAddressContext;
import org.knight.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.MemberHoldCollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.MemberRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 22:38
 */
@Component
public class ChainService {
    private final BcosSDK bcosSDK;

    private final Client client;

    private final DeployService deployService;

    @Autowired
    public ChainService(BcosSDK bcosSDK, Client client, DeployService deployService) {
        this.bcosSDK = bcosSDK;
        this.client = client;
        this.deployService = deployService;
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

    public Map<String , String > initDeploy() throws ContractException {
        String ownableAddress = deployService.deployOwnable();
        ContractAddressContext.setOwnableAddress(ownableAddress);
        String utilsAddress = deployService.deployUtils();
        ContractAddressContext.setUtilsAddress(utilsAddress);
        String bcosUserAddress = deployService.deployBcosUserContract();
        ContractAddressContext.setBcosUserAddress(bcosUserAddress);
        String bcosNFTAddress = deployService.deployBcosNFTContract();
        ContractAddressContext.setBcosNFTAddress(bcosNFTAddress);
        return Map.of("ownableAddress",ownableAddress,"utilsAddress",utilsAddress,"bcosUserAddress",bcosUserAddress,"bcosNFTAddress",bcosNFTAddress);
    }


    // TODO: 2024/4/4  待实现
    public Boolean initData(MemberHoldCollectionRepositoryImpl holdCollectionRepo, MemberRepositoryImpl memberRepo, CollectionRepositoryImpl collectionRepo) {
        List<MemberHoldCollectionEntity> holdCollectionEntitys = holdCollectionRepo.list();
        List<MemberEntity> memberEntitys = memberRepo.list();
        List<CollectionEntity> collectionEntities = collectionRepo.list();
        try{


        }catch (Exception e){
            throw new BlockChainException("block chain server init data error");
        }
        return true;
    }

    public Boolean initChain(MemberHoldCollectionRepositoryImpl holdCollectionRepo,
                             MemberRepositoryImpl memberRepo,
                             CollectionRepositoryImpl collectionRepo) throws ContractException {
        if (ContractAddressContext.getOwnableAddress() == null)
            initDeploy();
        return true;
    }
    
    
}
