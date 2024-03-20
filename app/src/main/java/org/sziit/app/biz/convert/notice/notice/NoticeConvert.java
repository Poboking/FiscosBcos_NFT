package org.sziit.app.biz.convert.notice.notice;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sziit.app.biz.notice.dto.notice.NoticeAbstractRespDTO;
import org.sziit.app.biz.notice.dto.notice.NoticeDetailRespDTO;
import org.sziit.infrastructure.dao.domain.NoticeEntity;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 16:37
 */
@Mapper
@SuppressWarnings("UnmappedTargetProperties")
public interface NoticeConvert {
    NoticeConvert INSTANCE = Mappers.getMapper(NoticeConvert.class);

    NoticeAbstractRespDTO convertToAbstract(NoticeEntity bean);

    NoticeDetailRespDTO convertToDetail(NoticeEntity bean);

}
