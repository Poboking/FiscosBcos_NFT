package org.knight.app.biz.convert.artwork;

import org.knight.app.biz.artwork.dto.collection.CollectionStoryDTO;
import org.knight.infrastructure.dao.domain.CollectionStoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/14 19:19
 */
@Mapper
public interface CollectionStoryConvert {

    CollectionStoryConvert INSTANCE = Mappers.getMapper(CollectionStoryConvert.class);

    CollectionStoryDTO convertToRespDTO(CollectionStoryEntity bean);

    List<CollectionStoryDTO> convertToRespDTOList(List<CollectionStoryEntity> bean);

}
