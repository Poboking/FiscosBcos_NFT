package org.sziit.app.biz.vo.collection;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sziit.infrastructure.dao.connector.MyHoldCollectionVoParent;
import org.sziit.infrastructure.dao.domain.CollectionEntity;
import org.sziit.infrastructure.dao.domain.MemberHoldCollectionEntity;
import org.sziit.infrastructure.dao.domain.MemberResaleCollectionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/11 15:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyHoldCollectionVo implements MyHoldCollectionVoParent {

    private String id;

    private String name;

    private String cover;

    private String holdDate;

    public static MyHoldCollectionVo convertFor(MemberHoldCollectionEntity mo) {
        if (mo == null) {
            return null;
        }
        MyHoldCollectionVo vo = new MyHoldCollectionVo();
        vo.setId(mo.getId());
        vo.setHoldDate(DateUtil.format(mo.getHoldTime(), DatePattern.NORM_DATE_PATTERN));
        vo.setName(mo.getName());
        vo.setCover(mo.getCover());
        return vo;
    }

    public static MyHoldCollectionVo convertFor(MemberResaleCollectionEntity mo) {
        if (mo == null) {
            return null;
        }
        MyHoldCollectionVo vo = new MyHoldCollectionVo();
        vo.setName(mo.getName());
        vo.setCover(mo.getCover());
        return vo;
    }

    public static MyHoldCollectionVo convertFor(MyHoldCollectionVoParent mo) {
        if (mo == null) {
            return null;
        }
        MyHoldCollectionVo vo = new MyHoldCollectionVo();
        vo.setName(mo.getName());
        vo.setCover(mo.getCover());
        return vo;
    }

    public static <T extends MyHoldCollectionVoParent> List<MyHoldCollectionVo> convertFor(List<T> mos) {
        if (CollectionUtil.isEmpty(mos)) {
            return new ArrayList<>();
        }
        List<MyHoldCollectionVo> vos = new ArrayList<>();
        for (T po : mos) {
            vos.add(convertFor(po));
        }
        return vos;
    }

}
