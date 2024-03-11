package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.ComposeMaterialEntity;
import org.sziit.infrastructure.dao.mapper.ComposeMaterialMapper;
import org.sziit.infrastructure.repository.ComposeMaterialService;

/**
 * @author poboking
 * @description 针对表【compose_material】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class ComposeMaterialServiceImpl extends ServiceImpl<ComposeMaterialMapper, ComposeMaterialEntity>
        implements ComposeMaterialService {

}




