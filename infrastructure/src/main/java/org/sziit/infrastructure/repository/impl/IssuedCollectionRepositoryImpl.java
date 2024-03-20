package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.IssuedCollectionEntity;
import org.sziit.infrastructure.dao.mapper.IssuedCollectionMapper;
import org.sziit.infrastructure.repository.IssuedCollectionRepository;

/**
 * @author poboking
 * @description 针对表【issued_collection】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class IssuedCollectionRepositoryImpl extends ServiceImpl<IssuedCollectionMapper, IssuedCollectionEntity>
        implements IssuedCollectionRepository {

}




