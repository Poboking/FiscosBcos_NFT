package org.knight.app.biz.convert.notice.notice;

import org.knight.app.biz.notice.dto.notice.NoticeAbstractRespDTO;
import org.knight.app.biz.notice.dto.notice.NoticeDetailRespDTO;
import org.knight.infrastructure.dao.domain.NoticeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
