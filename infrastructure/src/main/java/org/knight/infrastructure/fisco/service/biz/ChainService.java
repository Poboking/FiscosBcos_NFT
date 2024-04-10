package org.knight.infrastructure.fisco.service.biz;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.knight.infrastructure.common.IdUtils;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.common.SolidityAddressGenerator;
import org.knight.infrastructure.dao.domain.CollectionEntity;
import org.knight.infrastructure.dao.domain.IssuedCollectionEntity;
import org.knight.infrastructure.dao.domain.MemberEntity;
import org.knight.infrastructure.dao.domain.MemberHoldCollectionEntity;
import org.knight.infrastructure.exception.BcosInitializationUserException;
import org.knight.infrastructure.exception.BcosIssuedCollectionException;
import org.knight.infrastructure.exception.BcosTransactionException;
import org.knight.infrastructure.exception.initialize.BlockChainInitDataFailedException;
import org.knight.infrastructure.exception.initialize.IssuedCollectionDataInitializeException;
import org.knight.infrastructure.exception.initialize.UserDataInitializeException;
import org.knight.infrastructure.fisco.config.ContractAddressContext;
import org.knight.infrastructure.fisco.module.BlockChainNFT;
import org.knight.infrastructure.fisco.service.BcosNFT;
import org.knight.infrastructure.fisco.service.BcosUser;
import org.knight.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.IssuedCollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.MemberHoldCollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.MemberRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 22:38
 */
@Component
@SuppressWarnings("all")
public class ChainService {
    private final BcosSDK bcosSDK;

    private final Client client;

    private final DeployService deployService;

    private final CryptoKeyPair deployCryptoKeyPair;

    @Autowired
    public ChainService(BcosSDK bcosSDK, Client client, DeployService deployService, @Qualifier("deployCryptoKeyPair") CryptoKeyPair deployCryptoKeyPair) {
        this.bcosSDK = bcosSDK;
        this.client = client;
        this.deployService = deployService;
        this.deployCryptoKeyPair = deployCryptoKeyPair;
    }

    // TODO: 2024/3/31 22:33 待实现
    public String generateBlockTransaction() {
        return client.getCryptoSuite().createKeyPair().getAddress();
    }

    public String getBlockRandomAddress() {
        return client.getCryptoSuite().createKeyPair().getAddress();
    }

    public String getRandomPrivateKey() {
        return client.getCryptoSuite().createKeyPair().getHexPrivateKey();
    }

    public String generateBlockAccount() {
        return client.getCryptoSuite().createKeyPair().getAddress();
    }

    public static String getNFTHash(String nftId) {
        return UUID.randomUUID().toString();
    }

    public static String getNFTUniqueId(String nftId) {
        return UUID.randomUUID().toString();
    }

    public Map<String, String> initDeploy() throws ContractException {
        String bcosUserAddress = deployService.deployBcosUserContract();
        ContractAddressContext.setBcosUserAddress(bcosUserAddress);
        String bcosNFTAddress = deployService.deployBcosNFTContract();
        ContractAddressContext.setBcosNFTAddress(bcosNFTAddress);
        return Map.of("bcosUserAddress", bcosUserAddress, "bcosNFTAddress", bcosNFTAddress);
    }


    /**
     * 初始化链上数据 - 用户/藏品/发行藏品
     *
     * @param holdCollectionRepo
     * @param memberRepo
     * @param collectionRepo
     * @param issuedCollectionRepo
     * @return boolean 是否初始化成功
     */
    // TODO: 2024/4/4  待实现
    public Boolean initData(MemberHoldCollectionRepositoryImpl holdCollectionRepo,
                            MemberRepositoryImpl memberRepo,
                            CollectionRepositoryImpl collectionRepo,
                            IssuedCollectionRepositoryImpl issuedCollectionRepo) {
        try {
            initIssuedCollectionData(issuedCollectionRepo, collectionRepo);
            initUserData(memberRepo, holdCollectionRepo, issuedCollectionRepo, collectionRepo);
        } catch (Exception e) {
            throw new BlockChainInitDataFailedException("block chain server init data error");
        }
        return true;
    }


    /**
     * 发行数据初始化 - 藏品数据
     *
     * @param collectionRepo
     * @param issuedCollectionRepo
     * @return boolean 是否初始化成功
     */
    public Boolean initCollectionData(CollectionRepositoryImpl collectionRepo,
                                      IssuedCollectionRepositoryImpl issuedCollectionRepo) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT));
        List<CollectionEntity> collectionEntities = collectionRepo.list(new QueryWrapper<CollectionEntity>()
                .or(e -> e.eq("deleted_flag", false).or().isNull("deleted_flag"))
                .isNull("sync_chain_time"));
        List<String> collectionIds = collectionEntities.stream().map(CollectionEntity::getId).collect(Collectors.toList());

        try {
            IntStream.range(0, collectionIds.size()).forEach(i -> {
                String collectionId = collectionIds.get(i);
                CollectionEntity collectionEntity = collectionRepo.getById(collectionId);
                Long realityQuantity = issuedCollectionRepo.checkIssuedCollectionCount(collectionId);
                List<Integer> realitySerialNumbers = issuedCollectionRepo.getRealitySerialNumber(collectionId);
                if (realityQuantity == null) {
                    realityQuantity = 0L;
                }
                if (Objects.isNull(realitySerialNumbers)) {
                    realitySerialNumbers = new ArrayList<>();
                }
                if (realityQuantity < collectionEntity.getQuantity()) {
                    List<Integer> finalRealitySerialNumbers = realitySerialNumbers;
                    IntStream.range(1, collectionEntity.getQuantity() + 1).mapToObj(serialNumber -> {
                        if (!finalRealitySerialNumbers.contains(serialNumber)) {
                            String issuedCollectionId = IdUtils.snowFlakeId();
                            BlockChainNFT nft = null;
                            try {
                                nft = issuedCollection(collectionEntity.getName(),
                                        collectionEntity.getCreatorId(),
                                        issuedCollectionId,
                                        serialNumber,
                                        collectionEntity.getQuantity());
                            } catch (Exception e) {
                                 return null;
                            }
                            return IssuedCollectionEntity.builder()
                                    .id(issuedCollectionId)
                                    .collectionId(collectionId)
                                    .deletedFlag(false)
                                    .collectionSerialNumber(serialNumber)
                                    .uniqueId(nft.getUniqueId())
                                    .issueTime(now)
                                    .syncChainTime(nft.getSyncChainTime())
                                    .build();
                        }
                        return null;
                    }).filter(Objects::nonNull).forEach(issuedCollectionRepo::save);
                }
            });
        } catch (Exception e) {
            throw new BlockChainInitDataFailedException("block chain server init data error: " + e.getMessage());
        }
        return true;
    }


    /**
     * 发行数据链上初始化 - 发行藏品数据
     *
     * @param issuedCollectionRepo
     * @return boolean 是否初始化成功
     */
    public Boolean initIssuedCollectionData(IssuedCollectionRepositoryImpl issuedCollectionRepo,
                                            CollectionRepositoryImpl collectionRepo) {
        List<IssuedCollectionEntity> issuedCollectionEntities = issuedCollectionRepo.list(new QueryWrapper<IssuedCollectionEntity>()
                .or(e -> e.eq("deleted_flag", false).or().isNull("deleted_flag"))
                .isNull("unique_id")
                .isNull("sync_chain_time"));
        List<String> issuedCollectionIds = issuedCollectionEntities.stream().map(IssuedCollectionEntity::getId).collect(Collectors.toList());
        try {
            IntStream.range(0, issuedCollectionIds.size()).forEach(i -> {
                String issuedCollectionId = issuedCollectionIds.get(i);
                IssuedCollectionEntity issuedCollectionEntity = issuedCollectionRepo.getById(issuedCollectionId);
                CollectionEntity collection = collectionRepo.getById(issuedCollectionEntity.getCollectionId());
                if (issuedCollectionEntity.getCollectionSerialNumber() == null || collection == null || collection.getQuantity() == null) {
                    return;
                }
                BlockChainNFT result = issuedCollection(collection.getName(),
                        collection.getCreatorId(),
                        issuedCollectionId,
                        issuedCollectionEntity.getCollectionSerialNumber(),
                        collection.getQuantity());
                if (result == null) {
                    return;
                }
                issuedCollectionEntity.setUniqueId(result.getUniqueId());
                issuedCollectionEntity.setSyncChainTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)));
                issuedCollectionRepo.updateById(issuedCollectionEntity);
            });
        } catch (Exception e) {
            throw new IssuedCollectionDataInitializeException("block chain server init issued collection data error" + e.getMessage());
        }
        return true;
    }

    /**
     * 用户数据链上初始化
     *
     * @param memberRepo
     * @return boolean 是否初始化成功
     */
    public Boolean initUserData(MemberRepositoryImpl memberRepo,
                                MemberHoldCollectionRepositoryImpl holdCollectionRepo,
                                IssuedCollectionRepositoryImpl issuedCollectionRepo,
                                CollectionRepositoryImpl collectionRepo) {
        List<String> memberIds = memberRepo.list(new QueryWrapper<MemberEntity>()
                .or(e -> e.eq("deleted_flag", false).or().isNull("deleted_flag"))
                .isNull("block_chain_addr")
                .isNotNull("real_name")
                .isNotNull("identity_card")).stream().map(MemberEntity::getId).collect(Collectors.toList());
        List<String> issuedCollectionIds = issuedCollectionRepo.list(new QueryWrapper<IssuedCollectionEntity>()
                .or(e -> e.eq("deleted_flag", false).or().isNull("deleted_flag"))
                .isNotNull("unique_id")
                .isNotNull("sync_chain_time")).stream().map(IssuedCollectionEntity::getId).collect(Collectors.toList());
        List<String> memberHoldCollectionButNotIssuedCollectionIds = holdCollectionRepo.list(new QueryWrapper<MemberHoldCollectionEntity>()
                .isNull("lose_time")
                .isNotNull("member_id")
                .isNotNull("issued_collection_id")
                .notIn("issued_collection_id", issuedCollectionIds)).stream().map(MemberHoldCollectionEntity::getMemberId).collect(Collectors.toList());
        try {
            // 处理已实名但未上链用户
            Map<String, String> memberIdToBlockChainAddrMap = IntStream.range(0, memberIds.size())
                    .boxed()
                    .collect(Collectors.toMap(
                            i -> memberIds.get(i),
                            i -> {
                                String blockChainAddr = initRealNameUser();
                                memberRepo.updateBlockChainAddr(blockChainAddr, memberIds.get(i));
                                return blockChainAddr;
                            }
                    ));

            // 处理用户持有但未上链藏品 - 在issuedCollection表中存在
            IntStream.range(0, memberIds.size()).mapToObj(e -> {
                return holdCollectionRepo.list(new QueryWrapper<MemberHoldCollectionEntity>()
                        .eq("member_id", memberIds.get(e))
                        .in("issued_collection_id", issuedCollectionIds)).stream().map(MemberHoldCollectionEntity::getIssuedCollectionId).collect(Collectors.toList());
            }).collect(Collectors.toList()).forEach(e -> {
                e.forEach(issuedCollectionId -> {
                    initHoldCollection(memberIdToBlockChainAddrMap.get(memberIds.get(e.indexOf(issuedCollectionId))), issuedCollectionId);
                });
            });

            // 处理用户持有但未上链藏品 - 在issuedCollection表中不存在
            IntStream.range(0, memberHoldCollectionButNotIssuedCollectionIds.size()).forEach(holdCollectionId -> {
                Random random = new Random();
                MemberHoldCollectionEntity holdCollection = holdCollectionRepo.getById(holdCollectionId);
                CollectionEntity collection = collectionRepo.getById(holdCollection.getCollectionId());
                Integer quantity = collection.getQuantity();
                Integer serialNumber = 1;
                do {
                    serialNumber = random.nextInt(quantity) + 1;
                } while (issuedCollectionRepo.isExistSerialNumber(holdCollection.getCollectionId(), serialNumber));
                BlockChainNFT result = issuedCollection(collection.getName(),
                        collection.getCreatorId(),
                        holdCollection.getIssuedCollectionId(),
                        serialNumber,
                        quantity);
                Integer finalSerialNumber = serialNumber;
                issuedCollectionRepo.saveOrUpdate(IssuedCollectionEntity.builder()
                        .id(holdCollection.getIssuedCollectionId())
                        .collectionId(holdCollection.getCollectionId())
                        .collectionSerialNumber(finalSerialNumber)
                        .uniqueId(result.getUniqueId())
                        .issueTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)))
                        .syncChainTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)))
                        .build());
            });
            return true;
        } catch (Exception e) {
            throw new UserDataInitializeException("block chain server init user data error" + e.getMessage());
        }
    }

    /*
     * 初始化实名用户
     */
    public String initRealNameUser() {
        BcosUser bcosUser = BcosUser.load(ContractAddressContext.getBcosUserAddress(), client, deployCryptoKeyPair);
        String address;
        try {
            address = generateBlockAccount();
            bcosUser.createUser(address);
        } catch (Exception e) {
            throw new BcosInitializationUserException("block chain server initialization user error");
        }
        return address;
    }

    /**
     * 初始化用户所持有藏品
     *
     * @param address            用户区块链地址
     * @param issuedCollectionId 藏品发行ID
     * @return blockChainNFT
     */
    public BlockChainNFT initHoldCollection(String address, String issuedCollectionId) {
        BcosUser bcosUser = BcosUser.load(ContractAddressContext.getBcosUserAddress(), client, deployCryptoKeyPair);
        BcosNFT bcosNFT = BcosNFT.load(ContractAddressContext.getBcosNFTAddress(), client, deployCryptoKeyPair);
        BlockChainNFT entity = new BlockChainNFT();
        try {
            BigInteger tokenId = bcosNFT.getTokenIdByTokenURI(issuedCollectionId);
            bcosNFT.getTokenIdByTokenURI(issuedCollectionId);
            TransactionReceipt receipt = bcosUser.initToken(address, tokenId);
            String transactionHash = receipt.getTransactionHash();
            entity.setUniqueId(String.valueOf(tokenId));
            entity.setTransactionHash(transactionHash);
            entity.setIssuedCollectionId(issuedCollectionId);
            entity.setSyncChainTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)));
        } catch (Exception e) {
            throw new BcosInitializationUserException("block chain server initialization user error" + e.getMessage());
        }
        return entity;
    }


    /**
     * 链上初始化
     *
     * @param memberRepo
     * @return boolean 是否初始化成功
     */
    public Boolean initChain(MemberHoldCollectionRepositoryImpl holdCollectionRepo,
                             MemberRepositoryImpl memberRepo,
                             CollectionRepositoryImpl collectionRepo) throws ContractException {
        if (ContractAddressContext.getBcosNFTAddress() == null || ContractAddressContext.getBcosUserAddress() == null) {
            initDeploy();
        }
        return true;
    }

    /**
     * 发行指定数量的藏品NFT
     *
     * @param collectionName     藏品名称
     * @param createId           创作者ID
     * @param issuedCollectionId 藏品ID
     * @param issuedAmount       发行数量
     */
    //这里原本需要传入创作者的区块链地址,  此处暂改为传入创作者ID
    public List<BlockChainNFT> issuedCollection(String collectionName, String createId, String
            issuedCollectionId, Integer issuedAmount) {
        BcosNFT bcosNFT = BcosNFT.load(ContractAddressContext.getBcosNFTAddress(), client, deployCryptoKeyPair);
        List<BlockChainNFT> results = new ArrayList<>();
        IntStream.range(1, issuedAmount + 1).forEach(i -> {
            try {
                TransactionReceipt receipt = bcosNFT.createNFT(SolidityAddressGenerator.generateAddress(createId), collectionName, issuedCollectionId, BigInteger.valueOf(i), BigInteger.valueOf(issuedAmount));
                if (!receipt.isStatusOK()){
                    throw new BcosIssuedCollectionException("block chain server issued collection error");
                }
                BlockChainNFT nft = new BlockChainNFT();
                String tokenId = bcosNFT.getTokenIdByTokenURI(issuedCollectionId).toString();
                nft.setUniqueId(tokenId);
                nft.setIssuedCollectionId(issuedCollectionId);
                nft.setTransactionHash(receipt.getTransactionHash());
                nft.setSyncChainTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)));
                results.add(nft);
            } catch (Exception e) {
                throw new BcosIssuedCollectionException("block chain server issued collection error:" + e.getMessage());
            }
        });
        return results;
    }

    /**
     * 发行指定序列号的藏品NFT
     *
     * @param collectionName     藏品名称
     * @param createId           创作者ID
     * @param issuedCollectionId 发行藏品ID
     * @param serialNumber       序列号
     * @param issuedAmount       发行总数
     */
    public BlockChainNFT issuedCollection(String collectionName, String createId, String issuedCollectionId, Integer serialNumber, Integer issuedAmount) {
        BcosNFT bcosNFT = BcosNFT.load(ContractAddressContext.getBcosNFTAddress(), client, deployCryptoKeyPair);
        BlockChainNFT result = new BlockChainNFT();
        try {
            TransactionReceipt receipt = bcosNFT.createNFT(SolidityAddressGenerator.generateAddress(createId), issuedCollectionId, collectionName, BigInteger.valueOf(serialNumber), BigInteger.valueOf(issuedAmount));
            System.err.println(receipt.getMessage());
            System.err.println(receipt.getOutput());
            System.err.println(receipt.getBlockHash());
            System.err.println(receipt.getTransactionHash());
            System.err.println(receipt.getContractAddress());
            System.err.println(receipt);
            if (!receipt.isStatusOK()){
                throw new BcosIssuedCollectionException("block chain server issued collection error:" + receipt.getStatus());
            }
            String tokenId = bcosNFT.getTokenIdByNameAndSerialNumber(collectionName, BigInteger.valueOf(serialNumber)).toString();
            result.setUniqueId(tokenId);
            result.setIssuedCollectionId(issuedCollectionId);
            result.setTransactionHash(Optional.ofNullable(receipt.getTransactionHash()).orElse(receipt.getBlockHash()).toString());
            result.setSyncChainTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)));
        } catch (Exception e) {
            throw new BcosIssuedCollectionException("block chain server issued collection error:" + e.getMessage());
        }
        return result;
    }


    /**
     * 检查藏品是否发行 - 核验藏品区块链信息
     *
     * @param issuedCollectionId 藏品发行ID
     * @return blockChainNFT
     */
    public BlockChainNFT checkIssuedCollection(String issuedCollectionId) {
        BcosNFT bcosNFT = BcosNFT.load(ContractAddressContext.getBcosNFTAddress(), client, deployCryptoKeyPair);
        BlockChainNFT entity = new BlockChainNFT();
        try {
            BigInteger tokenId = bcosNFT.getTokenIdByTokenURI(issuedCollectionId);
            entity.setUniqueId(String.valueOf(tokenId));
            entity.setIssuedCollectionId(issuedCollectionId);
        } catch (Exception e) {
            throw new BcosIssuedCollectionException("block chain server issued collection error:" + e.getMessage());
        }
        return entity;
    }

    /**
     * 发行藏品交易
     *
     * @param fromAddress        售出藏品地址
     * @param toAddress          购入藏品地址
     * @param issuedCollectionId 发行藏品ID
     * @return string            区块链交易Hash
     */
    public String transaction(String fromAddress, String toAddress, String issuedCollectionId) {
        BcosUser bcosUser = BcosUser.load(ContractAddressContext.getBcosUserAddress(), client, deployCryptoKeyPair);
        BcosNFT bcosNFT = BcosNFT.load(ContractAddressContext.getBcosNFTAddress(), client, deployCryptoKeyPair);
        try {
            BigInteger tokenId = bcosNFT.getTokenIdByTokenURI(issuedCollectionId);
            TransactionReceipt receipt = bcosUser.transferToken(fromAddress, toAddress, tokenId);
            bcosNFT.transferNFT(tokenId, toAddress);
            return receipt.getTransactionHash();
        } catch (Exception e) {
            throw new BcosTransactionException("block chain server transaction error:" + e.getMessage());
        }
    }

    /**
     * 获取平台区块链账号
     *
     * @return
     */
    public String getDeployAddress() {
        return deployCryptoKeyPair.getAddress();
    }

    public String getNewDeployAccount() {
        CryptoKeyPair keyPair = client.getCryptoSuite().createKeyPair();
        return keyPair.getHexPrivateKey() + "|||" + keyPair.getAddress();
    }

}
