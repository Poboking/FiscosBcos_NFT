package org.sziit.app.biz.convert.notice;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sziit.app.biz.notice.dto.NoticeAbstractRespDto;
import org.sziit.app.biz.notice.dto.NoticeDetailRespDto;
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

    NoticeAbstractRespDto convertToAbstract(NoticeEntity bean);

    NoticeDetailRespDto convertToDetail(NoticeEntity bean);
}
