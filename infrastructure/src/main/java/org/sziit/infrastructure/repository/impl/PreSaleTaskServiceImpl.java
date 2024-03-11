package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.PreSaleTaskEntity;
import org.sziit.infrastructure.dao.mapper.PreSaleTaskMapper;
import org.sziit.infrastructure.repository.PreSaleTaskService;

/**
 * @author poboking
 * @description 针对表【pre_sale_task】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class PreSaleTaskServiceImpl extends ServiceImpl<PreSaleTaskMapper, PreSaleTaskEntity>
        implements PreSaleTaskService {

}




