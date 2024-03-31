package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.PreSaleTaskEntity;
import org.knight.infrastructure.dao.mapper.PreSaleTaskMapper;
import org.knight.infrastructure.repository.PreSaleTaskRepository;
import org.springframework.stereotype.Service;

/**
 * @author poboking
 * @description 针对表【pre_sale_task】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class PreSaleTaskRepositoryImpl extends ServiceImpl<PreSaleTaskMapper, PreSaleTaskEntity>
        implements PreSaleTaskRepository {

}




