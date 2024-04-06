package org.knight.app.biz.convert.artwork;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.knight.app.biz.artwork.dto.holdcollection.MyHoldCollectionRespDTO;
import org.knight.infrastructure.dao.connector.MyHoldCollectionRespDTOParent;
import org.knight.infrastructure.dao.domain.MemberHoldCollectionEntity;
import org.knight.infrastructure.dao.domain.MemberResaleCollectionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 17:52
 */
public class MyHoldCollectionConvert {

    public static MyHoldCollectionRespDTO convertToRespDTO(MemberHoldCollectionEntity mo) {
        if (mo == null) {
            return null;
        }
        MyHoldCollectionRespDTO vo = new MyHoldCollectionRespDTO();
        vo.setId(mo.getId());
        vo.setHoldTime(Optional.ofNullable(DateUtil.format(mo.getHoldTime(), DatePattern.NORM_DATE_PATTERN))
                .orElse(mo.getHoldTime().toString()));
        vo.setName(mo.getName());
        vo.setCover(mo.getCover());
        return vo;
    }

    public static MyHoldCollectionRespDTO convertToRespDTO(MemberResaleCollectionEntity mo) {
        if (mo == null) {
            return null;
        }
        MyHoldCollectionRespDTO vo = new MyHoldCollectionRespDTO();
        vo.setName(mo.getName());
        vo.setCover(mo.getCover());
        return vo;
    }

    public static MyHoldCollectionRespDTO convertToRespDTO(MyHoldCollectionRespDTOParent mo) {
        if (mo == null) {
            return null;
        }
        MyHoldCollectionRespDTO vo = new MyHoldCollectionRespDTO();
        vo.setName(mo.getName());
        vo.setCover(mo.getCover());
        return vo;
    }


    public static <T extends MyHoldCollectionRespDTOParent> List<MyHoldCollectionRespDTO> convertToRespDTO(List<T> mo) {
        if (mo == null) {
            return null;
        }
        List<MyHoldCollectionRespDTO> vo = new ArrayList<>();
        for (T entity : mo) {
            vo.add(convertToRespDTO(entity));
        }
        return vo;
    }

    public static List<MyHoldCollectionRespDTO> resaleConvertToRespDTOs(List<MemberResaleCollectionEntity> mo) {
        if (mo == null) {
            return null;
        }
        List<MyHoldCollectionRespDTO> vo = new ArrayList<>();
        for (MemberResaleCollectionEntity entity : mo) {
            vo.add(convertToRespDTO(entity));
        }
        return vo;
    }

    public static List<MyHoldCollectionRespDTO> holdConvertToRespDTOs(List<MemberHoldCollectionEntity> mo) {
        if (mo == null) {
            return null;
        }
        List<MyHoldCollectionRespDTO> vo = new ArrayList<>();
        for (MemberHoldCollectionEntity entity : mo) {
            vo.add(convertToRespDTO(entity));
        }
        return vo;
    }
}
