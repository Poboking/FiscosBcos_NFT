package org.knight.app.biz.task;

import lombok.extern.log4j.Log4j2;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.fisco.service.biz.ChainService;
import org.knight.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.IssuedCollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.MemberHoldCollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.MemberRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/10 15:03
 */
@Component
@Log4j2
public class BlockChainTask {

    private final ChainService chainService;

    private final MemberRepositoryImpl memberRepo;

    private final MemberHoldCollectionRepositoryImpl memberHoldCollectionRepo;

    private final CollectionRepositoryImpl collectionRepo;

    private final IssuedCollectionRepositoryImpl issuedCollectionRepo;

    @Autowired
    public BlockChainTask(ChainService chainService, MemberRepositoryImpl memberRepo, MemberHoldCollectionRepositoryImpl memberHoldCollectionRepo, CollectionRepositoryImpl collectionRepo, IssuedCollectionRepositoryImpl issuedCollectionRepo) {
        this.chainService = chainService;
        this.memberRepo = memberRepo;
        this.memberHoldCollectionRepo = memberHoldCollectionRepo;
        this.collectionRepo = collectionRepo;
        this.issuedCollectionRepo = issuedCollectionRepo;
    }

    // TODO: 2024/4/12 待修改
    @Scheduled(initialDelay = 1000 * 30, fixedDelay = Long.MAX_VALUE)
    public void initBlockChainData() {
        try {
            initBlockChain();
            log.info("["+ LocalDateTime.now().format(NftConstants.DATE_FORMAT) + "]: initBlockChainData");
            chainService.initData(memberHoldCollectionRepo, memberRepo, collectionRepo, issuedCollectionRepo);
        } catch (Exception e) {
            log.error("[定时任务执行失败]: initBlockChainData error ", e);
        }
    }


    public void initBlockChain() {
        try {
            log.info("["+ LocalDateTime.now().format(NftConstants.DATE_FORMAT) + "]: initBlockChain");
            if (chainService.initChain()) {
                log.info("["+ LocalDateTime.now().format(NftConstants.DATE_FORMAT) + "]: contract address 初始化成功");
            } else {
                log.error("["+ LocalDateTime.now().format(NftConstants.DATE_FORMAT) + "]: contract address 不存在");

            }
        } catch (Exception e) {
            log.error("[定时任务执行失败]: initBlockChain error ", e);
        }
    }
}
