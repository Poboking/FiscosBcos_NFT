package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.SmsSendRecordEntity;
import org.sziit.infrastructure.dao.mapper.SmsSendRecordMapper;
import org.sziit.infrastructure.repository.SmsSendRecordService;

/**
 * @author poboking
 * @description 针对表【sms_send_record】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class SmsSendRecordServiceImpl extends ServiceImpl<SmsSendRecordMapper, SmsSendRecordEntity>
        implements SmsSendRecordService {

}




