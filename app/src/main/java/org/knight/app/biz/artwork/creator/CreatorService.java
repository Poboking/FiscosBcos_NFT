package org.knight.app.biz.artwork.creator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.knight.app.biz.artwork.dto.creator.CreatorAddOrUpdateReqDTO;
import org.knight.app.biz.artwork.dto.creator.CreatorRespDTO;
import org.knight.app.biz.convert.artwork.CreatorConvert;
import org.knight.infrastructure.common.IdUtils;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.common.PageResult;
import org.knight.infrastructure.dao.domain.CreatorEntity;
import org.knight.infrastructure.repository.impl.CreatorRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 20:00
 */
@Service
public class CreatorService {
    private final CreatorRepositoryImpl creatorRepository;

    @Autowired
    public CreatorService(CreatorRepositoryImpl creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    public PageResult<CreatorRespDTO> findCreatorByPage(long current, long pageSize, String name) {
        Page<CreatorEntity> entityPage = creatorRepository.page(new Page<>(current, pageSize), new QueryWrapper<CreatorEntity>()
                .eq(Optional.ofNullable(name).isPresent(), "name", name));
        List<CreatorRespDTO> resultList = new ArrayList<>();;
        entityPage.getRecords().forEach(creatorEntity -> {
            resultList.add(CreatorConvert.INSTANCE.convertToRespDTO(creatorEntity));
        });
        return PageResult.convertFor(entityPage, pageSize, resultList);
    }

    public Boolean addOrUpdateCreator(CreatorAddOrUpdateReqDTO reqDTo) {
        String now = LocalDateTime.now().format(NftConstants.DATE_FORMAT);
        /**
         * 当id为空时，name和avatar不能为空, 新增
         */
        if (reqDTo.getName() != null && !reqDTo.getName().isEmpty() && reqDTo.getAvatar() != null && !reqDTo.getAvatar().isEmpty() && (reqDTo.getId() == null || reqDTo.getId().isEmpty())) {
            CreatorEntity entity = CreatorEntity.builder()
                    .id(IdUtils.uuid())
                    .name(reqDTo.getName())
                    .avatar(reqDTo.getAvatar())
                    .deletedFlag(false)
                    .createTime(Timestamp.valueOf(now))
                    .lastModifyTime(Timestamp.valueOf(now))
                    .build();
            return creatorRepository.saveOrUpdate(entity);
        }
        /**
         * 当id不为空时，更新
         */
        if (reqDTo.getId() != null && !reqDTo.getId().isEmpty() && creatorRepository.getById(reqDTo.getId()) != null) {
            return creatorRepository.updateById(CreatorConvert.INSTANCE.convertToEntity(reqDTo));
        }
        return false;
    }

    public Boolean delCreator(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        if (creatorRepository.getById(id) == null) {
            return false;
        }
        return creatorRepository.removeById(id);
    }

    public List<CreatorRespDTO> findAllCreator() {
        return creatorRepository.list().stream().map(CreatorConvert.INSTANCE::convertToRespDTO).collect(Collectors.toList());
    }
}
