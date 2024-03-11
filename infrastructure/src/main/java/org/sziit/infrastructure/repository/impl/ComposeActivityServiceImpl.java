package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.ComposeActivityEntity;
import org.sziit.infrastructure.dao.mapper.ComposeActivityMapper;
import org.sziit.infrastructure.repository.ComposeActivityService;

/**
 * @author poboking
 * @description 针对表【compose_activity】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class ComposeActivityServiceImpl extends ServiceImpl<ComposeActivityMapper, ComposeActivityEntity>
        implements ComposeActivityService {

}




