package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.knight.infrastructure.dao.domain.PreSaleConditionEntity;
import org.knight.infrastructure.dao.mapper.PreSaleConditionMapper;
import org.knight.infrastructure.repository.PreSaleConditionRepository;

/**
 * @author poboking
 * @description 针对表【pre_sale_condition】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:42
 */
@Service
public class PreSaleConditionRepositoryImpl extends ServiceImpl<PreSaleConditionMapper, PreSaleConditionEntity>
        implements PreSaleConditionRepository {

}




