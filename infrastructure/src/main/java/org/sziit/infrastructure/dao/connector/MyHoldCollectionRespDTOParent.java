package org.sziit.infrastructure.dao.connector;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/11 17:00
 */

/**
 * 用于标记MemberHoldCollectionEntity 和 MemberResaleCollectionEntity 的父类
 */
public interface MyHoldCollectionRespDTOParent {
    String getName();

    void setName(String name);

    String getCover();

    void setCover(String cover);
}
