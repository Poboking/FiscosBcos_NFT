package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.knight.infrastructure.dao.domain.MemberHoldCollectionEntity;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author poboking
 * @description 针对表【member_hold_collection】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface MemberHoldCollectionRepository extends IService<MemberHoldCollectionEntity> {
    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return PageInfo<MemberHoldCollectionEntity> 分页结果
     */
    PageInfo<MemberHoldCollectionEntity> getPageList(long current, long pageSize);

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 用户id
     * @return PageInfo<MemberHoldCollectionEntity> 分页结果
     */
    PageInfo<MemberHoldCollectionEntity> getPageListByMemberId(long current, long pageSize, String memberId);

    /**
     * 分页查询
     *
     * @param current         当前页
     * @param pageSize        每页大小
     * @param holdCollections 藏品id集合
     * @param memberId        用户id
     * @return PageInfo<MemberHoldCollectionEntity> 分页结果
     */
    PageInfo<MemberHoldCollectionEntity> getPageListByIdsAndMemberId(long current, long pageSize, List<String> holdCollections, String memberId);

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param name     作品名称
     * @param memberId 用户id
     * @return PageInfo<MemberHoldCollectionEntity> 分页结果
     */
    PageInfo<MemberHoldCollectionEntity> getPageListByMemberIdAndName(long current, long pageSize, String name, String memberId);

    /**
     * @param current  当前页
     * @param pageSize 每页大小
     * @param status   藏品转售状态
     * @param memberId 用户id
     * @return PageInfo<MemberHoldCollectionEntity> 分页结果
     */
    PageInfo<MemberHoldCollectionEntity> getPageListByMemberIdAndStatus(long current, long pageSize, String status, String memberId);


    /**
     * 检查藏品是否存在
     *
     * @param holdCollectionId 持有收藏品id
     * @return MemberHoldCollectionEntity
     */
    public MemberHoldCollectionEntity checkExist(String holdCollectionId, String memberId);

    /**
     * 增加持有数量
     *
     * @param id           操作类型
     * @param collectionId 藏品id
     * @param memberId     用户id
     * @param holdTime     当前时间
     * @return Boolean
     */
    Boolean increase(String id, String collectionId, String gainWay, String issuedCollectionId, String state, String memberId, Double price, String transactionHash, Timestamp holdTime);

    /**
     * 增加持有数量 - 通过购买
     *
     * @param id           操作类型
     * @param collectionId 藏品id
     * @param memberId     用户id
     * @param holdTime     当前时间
     * @return Boolean
     */
    Boolean increaseByPurchase(String id, String collectionId, String issuedCollectionId, String memberId, Double price, String transactionHash, Timestamp holdTime);

    /**
     * 增加持有数量 - 通过赠送
     *
     * @param id           操作类型
     * @param collectionId 藏品id
     * @param memberId     用户id
     * @param holdTime     当前时间
     * @return Boolean
     */
    Boolean increaseByGive(String id, String collectionId, String issuedCollectionId, String memberId, String transactionHash, Timestamp holdTime);

    /**
     * 增加持有数量 - 通过二级市场
     *
     * @param id           操作类型
     * @param collectionId 藏品id
     * @param memberId     用户id
     * @param holdTime     当前时间
     * @return Boolean
     */
    Boolean increaseBySecondaryMarket(String id, String collectionId, String issuedCollectionId, String memberId, Double price, String transactionHash, Timestamp holdTime);

    /**
     * 增加持有数量 - 通过盲盒
     *
     * @param id           操作类型
     * @param collectionId 藏品id
     * @param memberId     用户id
     * @param holdTime     当前时间
     * @return Boolean
     */
    Boolean increaseByMysteryBox(String id, String collectionId, String issuedCollectionId, String memberId, String transactionHash, Timestamp holdTime);

    /**
     * 增加持有数量 - 通过合成
     *
     * @param id           操作类型
     * @param collectionId 藏品id
     * @param memberId     用户id
     * @param holdTime     当前时间
     * @return Boolean
     */
    Boolean increaseByCompound(String id, String collectionId, String issuedCollectionId, String memberId, String transactionHash, Timestamp holdTime);

    /**
     * 增加持有数量 - 通过空投
     *
     * @param id           操作类型
     * @param collectionId 藏品id
     * @param memberId     用户id
     * @param holdTime     当前时间
     * @return Boolean
     */
    Boolean increaseByAirDrop(String id, String collectionId, String issuedCollectionId, String memberId, String transactionHash, Timestamp holdTime);

    /**
     * 增加持有数量 - 通过兑换码
     *
     * @param id           操作类型
     * @param collectionId 藏品id
     * @param memberId     用户id
     * @param holdTime     当前时间
     * @return Boolean
     */
    Boolean increaseByRedeemCode(String id, String collectionId, String issuedCollectionId, String memberId, String transactionHash, Timestamp holdTime);


    /**
     * 获取分页列表 - 根据参数体查询
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param memberId     用户ID
     * @param collectionId 藏品ID
     * @param state        藏品状态
     * @param gainWay      获得方式
     * @return PageInfo<MemberHoldCollectionEntity> 分页结果
     */
    PageInfo<MemberHoldCollectionEntity> getPageListByParam(Long current, Long pageSize, String memberId, String collectionId, String state, String gainWay);

}
