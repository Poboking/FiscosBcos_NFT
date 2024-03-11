package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.PayOrderEntity;
import org.sziit.infrastructure.dao.mapper.PayOrderMapper;
import org.sziit.infrastructure.repository.PayOrderService;

/**
 * @author poboking
 * @description 针对表【pay_order】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrderEntity>
        implements PayOrderService {

}




